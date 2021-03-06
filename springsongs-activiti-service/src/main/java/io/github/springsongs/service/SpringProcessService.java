package io.github.springsongs.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringProcessDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.util.ActivitiUtils;


@Service
public class SpringProcessService {

	static Logger logger = LoggerFactory.getLogger(SpringProcessService.class);
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	public PageInfo<SpringProcessDTO> getAllRecordByPage(String category,int page,int size) {

		List<SpringProcessDTO> SpringProcessDTOList = new ArrayList<>();
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().latestVersion();
		if (StringUtils.isNotBlank(category)) {
			query.processDefinitionCategory(category);
		}
		query.listPage(page, size).stream().forEach(processDefinition -> {
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(processDefinition.getDeploymentId()).singleResult();
			SpringProcessDTOList.add(ActivitiUtils.toProcessDTO(processDefinition, deployment));
		});
		
		PageInfo SpringProcessPageInfo= new PageInfo<>(SpringProcessDTOList);
		SpringProcessPageInfo.setList(SpringProcessDTOList);
		return SpringProcessPageInfo;
	}

	public String updateState(String state, String procDefId) {
		if (state.equals("active")) {
			repositoryService.activateProcessDefinitionById(procDefId, true, null);
		} else if (state.equals("suspend")) {
			repositoryService.suspendProcessDefinitionById(procDefId, true, null);
		}
		return state;
	}

	public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception {

		if (StringUtils.isBlank(procDefId)){
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
			procDefId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();

		String resourceName = "";
		if (resType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}

		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	@Transactional(readOnly = false)
	public Model convertToModel(String procDefId) {
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
            InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                    processDefinition.getResourceName());
            XMLInputFactory xif = XMLInputFactory.newInstance();
            InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
            XMLStreamReader xtr = xif.createXMLStreamReader(in);
            BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

            BpmnJsonConverter converter = new BpmnJsonConverter();
            ObjectNode modelNode = converter.convertToJson(bpmnModel);
			Model model = repositoryService.newModel();
            model.setKey(processDefinition.getKey());
            model.setName(processDefinition.getResourceName());
            model.setCategory(processDefinition.getCategory());
            model.setDeploymentId(processDefinition.getDeploymentId());
            model.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count()+1)));

            ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, model.getVersion());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
            model.setMetaInfo(modelObjectNode.toString());

            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), modelNode.toString().getBytes("utf-8"));
			return model;
        } catch (Exception e) {
        	logger.error(e.getMessage());
            throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
        }
	}
	
	
	public TaskDefinition getNextTaskInfo(String taskId) throws Exception {  

        ProcessDefinitionEntity processDefinitionEntity = null;  

        String id = null;  

        TaskDefinition task = null;  

        //??????????????????Id??????   
        
        Task userTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        
        String processInstanceId = userTask.getProcessInstanceId();  

        //??????????????????Id??????   
        String definitionId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();  

        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
                .getDeployedProcessDefinition(definitionId);  

        ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();  

        //??????????????????Id??????   
        String activitiId = execution.getActivityId();    

        //??????????????????????????????   
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();   

        //????????????????????????   
        for(ActivityImpl activityImpl : activitiList){      
            id = activityImpl.getId();     
            if (activitiId.equals(id)) {
                //???????????????????????????   
                task = nextTaskDefinition(activityImpl, activityImpl.getId(), null, processInstanceId); 
                break;
            }
        }  
        return task;  
    }  

	private TaskDefinition nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString, String processInstanceId){   

        PvmActivity ac = null;

        Object s = null;

        // ?????????????????????????????????????????????????????????????????????
        if ("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())) {
            // ????????????????????????????????????
            TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior())
                    .getTaskDefinition();
            return taskDefinition;
        } else {
            // ????????????????????????????????????
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            List<PvmTransition> outTransitionsTemp = null;
            for (PvmTransition tr : outTransitions) {
                ac = tr.getDestination(); // ???????????????????????????
                // ?????????????????????????????????
                if ("exclusiveGateway".equals(ac.getProperty("type"))) {
                    outTransitionsTemp = ac.getOutgoingTransitions();

                    // ??????????????????????????????????????????
                    if (StringUtils.isEmpty(elString)) {
                        // ??????????????????????????????????????????????????????
                        elString = getGatewayCondition(ac.getId(), processInstanceId);
                    }

                    // ??????????????????????????????????????????
                    if (outTransitionsTemp.size() == 1) {
                        return nextTaskDefinition((ActivityImpl) outTransitionsTemp.get(0).getDestination(), activityId,
                                elString, processInstanceId);
                    } else if (outTransitionsTemp.size() > 1) { // ???????????????????????????????????????
                        for (PvmTransition tr1 : outTransitionsTemp) {
                            s = tr1.getProperty("conditionText"); // ??????????????????????????????????????????
                            // ??????el?????????????????????
                            if (isCondition(ac.getId(), StringUtils.trim(s.toString()), elString)) {
                                return nextTaskDefinition((ActivityImpl) tr1.getDestination(), activityId, elString,
                                        processInstanceId);
                            }
                        }
                    }
                } else if ("userTask".equals(ac.getProperty("type"))) {
                    return ((UserTaskActivityBehavior) ((ActivityImpl) ac).getActivityBehavior()).getTaskDefinition();
                } else {
                }
            }
            return null;
        }
    }  
	

    public String getGatewayCondition(String gatewayId, String processInstanceId) {  
        Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
        Object object= runtimeService.getVariable(execution.getId(), gatewayId);
        return object==null? "":object.toString();  
    }  


    public boolean isCondition(String key, String el, String value) {  
        ExpressionFactory factory = new ExpressionFactoryImpl();    
        SimpleContext context = new SimpleContext();    
        context.setVariable(key, factory.createValueExpression(value, String.class));    
        ValueExpression e = factory.createValueExpression(context, el, boolean.class);    
        return (Boolean) e.getValue(context);  
    }  
    
    public  String getNextNode(String procInstanceId){
        // 1????????????????????????ID?????????????????????
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstanceId).list();
        String nextId = "";
        for (Task task : tasks) {
            RepositoryService rs = repositoryService;
            // 2???????????????????????????????????????????????????????????????????????????????????????????????????????????????
            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) rs)
                    .getDeployedProcessDefinition(task.getProcessDefinitionId());
            List<ActivityImpl> activitiList = def.getActivities(); // rs??????RepositoryService?????????
            // 3???????????????????????????????????????ID??????????????????????????????????????????ID???
            String excId = task.getExecutionId();
            ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId)
                    .singleResult();
            String activitiId = execution.getActivityId();
            // 4???????????????activitiList
            // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            for (ActivityImpl activityImpl : activitiList) {
                String id = activityImpl.getId();
                if (activitiId.equals(id)) {
                    logger.debug("???????????????" + activityImpl.getProperty("name")); // ?????????????????????????????????
                    List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();// ??????????????????????????????????????????
                    for (PvmTransition tr : outTransitions) {
                        PvmActivity ac = tr.getDestination(); // ???????????????????????????
                        logger.debug("????????????????????????" + ac.getProperty("name"));
                        nextId = ac.getId();
                    }
                    break;
                }
            }
        }
        return nextId;
    }
}
