package com.crp.qa.qaAuthorization.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaAuthorization.domain.dto.QaPagedDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupSimpleDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.service.inte.QaGroupService;
import com.crp.qa.qaAuthorization.util.exception.QaGroupException;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;
import com.crp.qa.qaAuthorization.util.transfer.QaBaseTransfer;
import com.crp.qa.qaAuthorization.util.transfer.QaPagedTransfer;

@RestController
@RequestMapping(path="/group")
public class QaGroupController extends QaBaseController{

	@Resource(name="qaGroupService")
	private QaGroupService qaGroupService;
	
	@GetMapping(path="/findAll")
	public QaBaseTransfer findAll() {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			List<QaSysGroupSimpleDto> dlist= qaGroupService.findAll();
			dto.setContent(dlist);
		}catch(QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	@GetMapping(path="/findById/{groupId}")
	public QaBaseTransfer findById(@PathVariable(value="groupId")Integer groupId) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			QaSysGroupDto d= qaGroupService.findById(groupId);
			dto.setContent(d);
		}catch(QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	@GetMapping(path="/findByGroupName")
	public QaBaseTransfer findByGroupName(@RequestParam(value="groupName") String groupName) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			QaSysGroupDto d= qaGroupService.findByGroupName(groupName);
			dto.setContent(d);
		}catch(QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	@GetMapping(path="/findByGroupNameLike")
	public QaPagedTransfer findByGroupNameLike(
			@RequestParam(value="groupName") String groupName,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size) {
		//创建返回对象
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功！");
		try {
			QaPagedDto<QaSysGroupSimpleDto> d= qaGroupService.findByGroupNameLike(groupName,page,size);
			dto.setContent(d.getList());
			dto.setTotalElements(d.getTotalElements());
			dto.setTotalPages(d.getTotalPages());
		}catch(QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	@PostMapping(path="/save")
	public QaBaseTransfer save(@RequestParam(value="group") String group) {
		QaBaseTransfer dto = new QaBaseTransfer("success","保存成功！");		
		QaSysGroupDto d = JSONObject.parseObject(group, QaSysGroupDto.class);
		try {
			QaSysGroupDto userDto = qaGroupService.save(d);
			dto.setContent(userDto);
		}catch (QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	
	@DeleteMapping(path="/delete")
	public QaBaseTransfer delete(HttpEntity<String> req) {
		JSONObject json = JSONObject.parseObject(req.getBody());
		String id = json.get("id").toString();
		QaBaseTransfer dto = new QaBaseTransfer("success","删除成功！");
		try {
			qaGroupService.delete(Integer.parseInt(id));
		}catch ( NumberFormatException | QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 更新传入的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:53:54
	 * @param user
	 * @return
	 */
	@PutMapping(path="/update")
	public QaBaseTransfer update(HttpEntity<String> req){
		JSONObject json = JSONObject.parseObject(req.getBody());
		String group = json.get("group").toString();
		QaBaseTransfer dto = new QaBaseTransfer("success","更新成功！");		
		QaSysGroupDto d = JSONObject.parseObject(group, QaSysGroupDto.class);
		try {
			QaSysGroupDto userDto = qaGroupService.update(d);
			dto.setContent(userDto);
		}catch (QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
}
