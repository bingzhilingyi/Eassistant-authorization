package com.crp.qa.qaAuthorization.service.impl;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserGroupDto;
import com.crp.qa.qaAuthorization.service.inte.QaLoginService;
import com.crp.qa.qaAuthorization.util.exception.QaLoginException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaLoginServiceImplTest {
	
	private final String ACCOUNT = "huangyue43";
	private final String PASSWORD = "Crp000";
	
	private final String WRONG_ACCOUNT = "huangyue4";
	private final String WRONG_PASSWORD = "Crp00";
	
	@Resource(name="qaLoginService")
	QaLoginService qaLoginService;

	//测试正常登录
	@Test
	public void login(){
		System.out.println("------------login-------------");
		try {
			QaSysUserDto user = qaLoginService.login(ACCOUNT, PASSWORD);
			assertTrue(user.getUserId()==1);
		} catch (QaLoginException e) {
			e.printStackTrace();
		}
	}
	
	//测试账号密码错误
	@Test
	public void login2(){
		System.out.println("------------login2-------------");
		try {
			//账号错误
			QaSysUserDto user = qaLoginService.login(WRONG_ACCOUNT, PASSWORD);
			assertTrue(user.getUserId()==null);
			//密码错误
			user = qaLoginService.login(ACCOUNT, WRONG_PASSWORD);
			assertTrue(user.getUserId()==null);
		} catch (QaLoginException e) {
			e.printStackTrace();
		}
	}
	
	//测试登录无账号或密码时是否会抛出异常
	@Test(expected=QaLoginException.class)
	public void loginException() throws QaLoginException{
		System.out.println("------------loginException-------------");
		qaLoginService.login(ACCOUNT, "");
	}
	@Test(expected=QaLoginException.class)
	public void loginException2() throws QaLoginException{
		System.out.println("------------loginException2-------------");
		qaLoginService.login("", PASSWORD);
	}

}
