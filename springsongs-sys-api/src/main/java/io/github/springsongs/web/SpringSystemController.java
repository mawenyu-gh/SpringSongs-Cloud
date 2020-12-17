package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringSystemDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "子系统管理")
@RestController
@RequestMapping(value = "/SpringSystem")
public class SpringSystemController{

	private static final Logger logger = LoggerFactory.getLogger(SpringSystemController.class);

	@Autowired
	private ISpringSystemService springSystemService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringSystemDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringSystemDTO> listByPage(@RequestBody SpringSystemDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<SpringSystemDTO> reponseResultPageDTO =springSystemService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取子系统", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringSystemDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringSystemDTO> responseDTO = springSystemService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建子系统", notes = "根据SpringSystemDTO子系统", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringSystemDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody SpringSystemDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springSystemService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改子系统", notes = "根据SpringSystemDTO修改子系统", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringSystemDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody SpringSystemDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springSystemService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除子系统", notes = "根据List<String>对象删除子系统", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "子系统编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return ResponseDTO.successed(null, ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		ResponseDTO<String> responseDTO = springSystemService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "查询全部子系统", notes = "查询全部子系统", response = ResponseDTO.class)
	@GetMapping(value = "/ListAll")
	public ResponseDTO<List<SpringSystemDTO>> listAll() {
		ResponseDTO<List<SpringSystemDTO>> responseDTO = springSystemService.listAll();
		return responseDTO;
	}
}
