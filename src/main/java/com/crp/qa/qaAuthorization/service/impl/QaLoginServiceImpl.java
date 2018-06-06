/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaAuthorization.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.service.inte.QaLoginService;
import com.crp.qa.qaAuthorization.service.inte.QaUserService;
import com.crp.qa.qaAuthorization.util.exception.QaLoginException;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;
import com.mysql.jdbc.StringUtils;

/**
 * @author huangyue
 * @date 2018年5月31日 下午6:37:47
 * @ClassName QaLoginServiceImpl
 */
@Service(value="qaLoginService")
@Transactional
public class QaLoginServiceImpl implements QaLoginService {
	
	@Resource(name="qaSysUserService")
	private QaUserService qaSysUserService;
	@Resource(name="qaTokenService")
	private QaTokenServiceImpl qaTokenServiceImpl;


	@Override
	public String login(String account, String password) throws QaLoginException {
		if(StringUtils.isNullOrEmpty(account)||StringUtils.isNullOrEmpty(password)) {
			throw new QaLoginException("用户名或密码为空！");
		}
		try {
			//find user by account
			QaSysUserDto user =  qaSysUserService.findByAccountEqual(account);
			//验证密码,如果验证通过存储token
			if(user!=null&&password.equals(user.getUserPassword())) {
				//生成一个token
				String token = qaTokenServiceImpl.generateToken();
				//以token为key保存用户信息
				qaTokenServiceImpl.setToken(token, user);
				//返回token
				return token;
			}else {
				//如果登录失败，抛出错误
				throw new QaLoginException("账号或密码错误");
			}
		} catch (QaUserException e) {
			throw new QaLoginException("登录发生未知错误",e);
		}
	}


	@Override
	public boolean isLogin(String token) throws QaLoginException {
		if(StringUtils.isNullOrEmpty(token)) {
			throw new QaLoginException("token为空！");
		}
		return qaTokenServiceImpl.isExists(token);
	}

}
