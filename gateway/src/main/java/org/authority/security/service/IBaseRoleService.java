package org.authority.security.service;

import java.util.List;

import org.authority.domain.Role;
import org.authority.dto.RoleCodeDto;
import org.authority.security.service.impl.BaseRoleServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback =BaseRoleServiceImpl.class)
public interface IBaseRoleService {

	@RequestMapping(value = "/BaseRole/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseRole/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Role record);

	@RequestMapping(value = "/BaseRole/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseRole/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Role record);

	@RequestMapping(value = "/BaseRole/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Role record,@RequestParam("page") int page,@RequestParam("size")int size);

	@RequestMapping(value = "/BaseRole/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);
	
	@RequestMapping(value = "/BaseRole/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);

	@RequestMapping(value = "/BaseRole/GetRolesByUserId", method = RequestMethod.POST)
	R getRolesByUserId(@RequestParam("userId")String userId);
}
