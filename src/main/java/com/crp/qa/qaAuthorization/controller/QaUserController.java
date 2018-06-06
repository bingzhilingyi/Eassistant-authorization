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

import com.crp.qa.qaAuthorization.domain.dto.QaPagedDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaAuthorization.service.inte.QaUserService;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;
import com.crp.qa.qaAuthorization.util.transfer.QaBaseTransfer;
import com.crp.qa.qaAuthorization.util.transfer.QaPagedTransfer;

/**
 * 用户管理
 * @author huangyue
 * @date 2018年5月31日 下午6:09:19
 * @ClassName QaUserController
 */
@RestController
@RequestMapping(path="/user")
public class QaUserController extends QaBaseController{
		
	@Resource(name="qaSysUserService")
	private QaUserService qaSysUserService;
		
	/**
	 * 获取所有用户,不分页
	 * @param username
	 * @return
	 */
	@GetMapping(path="/findAll")
	public QaBaseTransfer findAllUser() {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			List<QaSysUserDto> userList = qaSysUserService.findAll();
			dto.setContent(userList);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}

	/**
	 * 获取所有用户,分页
	 * @param username
	 * @return
	 */
	@GetMapping(path="/findPagedAll")
	public QaPagedTransfer findPagedAll(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size) {
		//创建返回对象
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功！");
		try {
			QaPagedDto<QaSysUserDto> userPage = qaSysUserService.findPagedAll(page, size);
			dto.setTotalElements(userPage.getTotalElements());
			dto.setTotalPages(userPage.getTotalPages());
			dto.setContent(userPage.getList());
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据id查找用户
	 * @author huangyue
	 * @date 2018年5月10日 上午10:17:14
	 * @param id
	 * @return
	 */
	@GetMapping(path="/get/{id}")
	public QaBaseTransfer get(@PathVariable(value="id") Integer id) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			QaSysUserDto user =  qaSysUserService.findById(id);
			dto.setContent(user);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据账户或姓名查找用户,不分页
	 * @author huangyue
	 * @date 2018年5月15日 下午1:08:30
	 * @param account 账户或姓名
	 * @return
	 */
	@GetMapping(path="/findByAccountOrName")
	public QaBaseTransfer findByAccountOrName(@RequestParam(value="account") String account) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			List<QaSysUserDto> userList =  qaSysUserService.findByAccountOrName(account);
			dto.setContent(userList);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据账户或姓名查找用户,分页
	 * @author huangyue
	 * @date 2018年5月15日 下午2:58:11
	 * @param account 账户或姓名
	 * @param page 当前页
	 * @param size 每页条目数
	 * @param isSlice 返回值是否是slice
	 * 			false (default):  返回page，包含总页数及总条目数，效率低。
	 * 			true: 返回slice，只有数据，没有总页数和总条目数，效率高。
	 * @return
	 */
	@GetMapping(path="/findPagedByAccountOrName")
	public QaPagedTransfer findPagedByAccountOrName(
			@RequestParam(value="account") String account,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size,
			@RequestParam(value="isSlice",defaultValue="false") Boolean isSlice){
		//创建返回对象
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功！");
		try {
			QaPagedDto<QaSysUserDto> userPage = qaSysUserService.findPagedByAccountOrName(account, page, size, isSlice);
			dto.setTotalElements(userPage.getTotalElements());
			dto.setTotalPages(userPage.getTotalPages());
			dto.setContent(userPage.getList());
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 保存传入的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:53:40
	 * @param user
	 * @return
	 */
	@PostMapping(path="/save")
	public QaBaseTransfer save(@RequestParam(value="user") String user){
		QaBaseTransfer dto = new QaBaseTransfer("success","保存成功！");		
		QaSysUserDto d = JSONObject.parseObject(user, QaSysUserDto.class);
		try {
			QaSysUserDto userDto = qaSysUserService.save(d);
			dto.setContent(userDto);
		}catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据id删除对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:53:47
	 * @param id
	 * @return
	 */
	@DeleteMapping(path="/delete")
	public QaBaseTransfer delete(HttpEntity<String> req){
		JSONObject json = JSONObject.parseObject(req.getBody());
		String id = json.get("id").toString();
		QaBaseTransfer dto = new QaBaseTransfer("success","删除成功！");
		try {
			qaSysUserService.deleteById(Integer.parseInt(id));
		}catch (QaUserException | NumberFormatException e) {
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
		String user = json.get("user").toString();
		QaBaseTransfer dto = new QaBaseTransfer("success","更新成功！");		
		QaSysUserDto d = JSONObject.parseObject(user, QaSysUserDto.class);
		try {
			QaSysUserDto userDto = qaSysUserService.update(d);
			dto.setContent(userDto);
		}catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
}
