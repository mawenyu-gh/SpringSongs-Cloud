package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringActUseTask;
import io.github.springsongs.dto.SpringActUseTaskDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringActUseTaskMapper;
import io.github.springsongs.service.ISpringActUseTaskService;
import io.github.springsongs.util.AuthenUtil;
@Service
public class SpringActUseTaskServiceImpl implements ISpringActUseTaskService {

	static Logger logger = LoggerFactory.getLogger(SpringActUseTaskServiceImpl.class);

	@Autowired
	private SpringActUseTaskMapper springActUseTaskMapper;

	@Autowired
	protected RepositoryService repositoryService;
	
	@Autowired
	private AuthenUtil authenUtil;

	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springActUseTaskMapper.deleteByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void insert(SpringActUseTaskDTO springActUseTaskDTO) {
		SpringActUseTask springActUseTask = new SpringActUseTask();
		springActUseTaskDTO.setId(UUID.randomUUID().toString());
		springActUseTaskDTO.setCreatedUserId(authenUtil.getUser().getId());
		springActUseTaskDTO.setCreatedBy(authenUtil.getUser().getUserName());
		springActUseTaskDTO.setCreatedIp(authenUtil.getUser().getIp());
		springActUseTaskDTO.setCreatedOn(new Date());
		BeanUtils.copyProperties(springActUseTaskDTO, springActUseTask);
		try {
			
			springActUseTaskMapper.insert(springActUseTask);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void insert(List<SpringActUseTaskDTO> springActUseTaskDTOList) {
		List<SpringActUseTask> springActUseTaskList = new ArrayList<>();
		springActUseTaskDTOList.stream().forEach(springActUseTaskDTO -> {
			SpringActUseTask springActUseTask = new SpringActUseTask();
			springActUseTaskDTO.setId(UUID.randomUUID().toString());
			springActUseTaskDTO.setCreatedUserId(authenUtil.getUser().getId());
			springActUseTaskDTO.setCreatedBy(authenUtil.getUser().getUserName());
			springActUseTaskDTO.setCreatedIp(authenUtil.getUser().getIp());
			springActUseTaskDTO.setCreatedOn(new Date());
			BeanUtils.copyProperties(springActUseTaskDTO, springActUseTask);
			try {
				springActUseTaskMapper.insert(springActUseTask);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		});
		
	}

	@Override
	public SpringActUseTaskDTO selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateByPrimaryKey(SpringActUseTaskDTO record) {
		// TODO Auto-generated method stub

	}

	@Override
	public PageInfo<SpringActUseTaskDTO> getAllRecordByPage(SpringActUseTaskDTO record,int page,int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDeleted(List<String> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SpringActUseTaskDTO> listUserTaskByProcDefKey(String procDefKey) {
		List<SpringActUseTask> springActUseTaskList = springActUseTaskMapper.listUserTaskByProcDefKey(procDefKey);
		List<SpringActUseTaskDTO> springActUseTaskDTOList = new ArrayList<>();
		springActUseTaskList.stream().forEach(springActUseTask -> {
			SpringActUseTaskDTO springActUseTaskDTO = new SpringActUseTaskDTO();
			BeanUtils.copyProperties(springActUseTask, springActUseTaskDTO);
			springActUseTaskDTOList.add(springActUseTaskDTO);
		});
		return springActUseTaskDTOList;
	}

	@Transactional
	@Override
	public void initSingleDefinition(String processDefinitionId, String procDefKey) {
		this.springActUseTaskMapper.delete(procDefKey);
		ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
		if (processDefinition == null) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		try {
			setSingleActivitiInfo(processDefinition);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	private void setSingleActivitiInfo(ProcessDefinition processDefinition) throws Exception {
		String proDefKey = processDefinition.getKey();
		List<SpringActUseTask> list = this.springActUseTaskMapper.listUserTaskByProcDefKey(proDefKey);
		ProcessDefinitionEntity processDef = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinition.getId());
		List<ActivityImpl> activitiList = processDef.getActivities();// ?????????????????????????????????
		for (ActivityImpl activity : activitiList) {
			ActivityBehavior activityBehavior = activity.getActivityBehavior();
			boolean isFound = false;
			// ?????????????????????
			if (activityBehavior instanceof UserTaskActivityBehavior) {
				UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
				TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
				// ??????????????????
				String taskDefKey = taskDefinition.getKey();
				Expression taskName = taskDefinition.getNameExpression();

				// ?????????????????????????????????
				if (list.size() != 0) {
					for (SpringActUseTask userTask : list) {
						if (taskDefKey.equals(userTask.getTaskDefKey())) {
							userTask.setProcDefKey(processDefinition.getKey());
							userTask.setProcDefName(processDefinition.getKey());
							userTask.setTaskDefKey(taskDefKey);
							userTask.setTaskName(taskName.toString());
							userTask.setUpdatedUserId(authenUtil.getUser().getId());
							userTask.setUpdatedBy(authenUtil.getUser().getUserName());
							userTask.setUpdatedIp(authenUtil.getUser().getIp());
							userTask.setUpdatedOn(new Date());
							this.springActUseTaskMapper.updateByPrimaryKey(userTask);
							isFound = true;
							break;
						}
					}

				}
				if (!isFound) {
					SpringActUseTask userTask = new SpringActUseTask();
					userTask.setProcDefKey(processDefinition.getKey());
					userTask.setProcDefName(processDefinition.getKey());
					userTask.setTaskDefKey(taskDefKey);
					userTask.setTaskName(taskName.toString());
					userTask.setId(UUID.randomUUID().toString());
					userTask.setCreatedUserId(authenUtil.getUser().getId());
					userTask.setCreatedBy(authenUtil.getUser().getUserName());
					userTask.setCreatedIp(authenUtil.getUser().getIp());
					userTask.setCreatedOn(new Date());
					this.springActUseTaskMapper.insert(userTask);
				}
			}
		}
	}

	@Transactional
	@Override
	public void initAllDefinition() {
		this.springActUseTaskMapper.deleteAll();
		ProcessDefinitionQuery proDefQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId()
				.desc();
		List<ProcessDefinition> processDefinitionList = proDefQuery.list();
		for (ProcessDefinition processDefinition : processDefinitionList) {
			try {
				setSingleActivitiInfo(processDefinition);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	@Transactional
	@Override
	public void setUserToTask(String procDefKey, HttpServletRequest request) {
		List<SpringActUseTask> springActUseTaskList = springActUseTaskMapper.listUserTaskByProcDefKey(procDefKey);
		for (SpringActUseTask userTask : springActUseTaskList) {
			String taskDefKey = userTask.getTaskDefKey();
			String ids = request.getParameter(taskDefKey + "_id");
			String names = request.getParameter(taskDefKey + "_name");
			String taskType = request.getParameter(taskDefKey + "_taskType");
			userTask.setId(UUID.randomUUID().toString());
			userTask.setCreatedUserId(authenUtil.getUser().getId());
			userTask.setCreatedBy(authenUtil.getUser().getUserName());
			userTask.setCreatedIp(authenUtil.getUser().getIp());
			userTask.setCreatedOn(new Date());
			userTask.setTaskType(taskType);
			userTask.setCandidateName(names);
			userTask.setCandidateIds(ids);
			this.springActUseTaskMapper.insert(userTask);
		}
	}

}
