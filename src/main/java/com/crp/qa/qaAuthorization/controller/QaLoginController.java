/**
 * huangyue
 * 2018年5月14日
 */
package com.crp.qa.qaAuthorization.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.service.inte.QaLoginService;
import com.crp.qa.qaAuthorization.util.exception.QaLoginException;
import com.crp.qa.qaAuthorization.util.transfer.QaBaseTransfer;

/**
 * 登录管理
 * @author huangyue
 * @date 2018年5月14日 下午7:05:35
 * @ClassName QaLoginController
 */
@RestController
@RequestMapping(path="/Login")
public class QaLoginController extends QaBaseController{
	
	@Resource(name="qaLoginService")
	private QaLoginService qaLoginService;

	/**
	 * 登录方法
	 * @author huangyue
	 * @date 2018年5月29日 上午10:26:40
	 * @param req
	 * @param res
	 * @param account
	 * @param password
	 * @return
	 */
	@PostMapping(path="/login")
	public QaBaseTransfer Login(
			@RequestParam(value="account") String account,
			@RequestParam(value="password") String password ) {
		QaBaseTransfer dto = new QaBaseTransfer("success","登录成功!");		
		try {
			//调用登录方法去获取token,如果登录不上就会抛出异常
			QaSysUserDto user = qaLoginService.login(account, password);
			dto.setToken(user.getToken());
			dto.setContent(user);
			if(user.getToken()==null||user.getToken()=="") {
				dto.setMessage("账号或密码不对！");
			}
		} catch (QaLoginException e) {
			this.returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 通过token判断是否登录
	 * @author huangyue
	 * @date 2018年5月29日 上午10:26:47
	 * @return
	 */
	@GetMapping(path="/isLogin")
	public QaBaseTransfer isLogin(@RequestParam(value="logingToken") String logingToken) {
		QaBaseTransfer dto = new QaBaseTransfer("success","已登录");
		try {
			boolean isLogin = qaLoginService.isLogin(logingToken);
			if(!isLogin) {
				dto.setStatus("failed");
				dto.setMessage("token错误或已过期,请重新登录");
			}
		} catch (QaLoginException e) {
			this.returnError(e, dto);
		}
		return dto;
	}

	
	@GetMapping(path="/findByToken")
	public QaBaseTransfer findByToken(@RequestParam(value="logingToken") String logingToken) {
		QaBaseTransfer dto = new QaBaseTransfer("success","已登录");
		try {
			QaSysUserDto user = qaLoginService.findByToken(logingToken);
			dto.setContent(user);
		} catch (QaLoginException e) {
			this.returnError(e, dto);
		}
		return dto;
	}
}
