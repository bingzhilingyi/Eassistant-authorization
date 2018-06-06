package com.crp.qa.qaAuthorization;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.service.inte.QaLoginService;
import com.crp.qa.qaAuthorization.service.inte.QaUserService;
import com.crp.qa.qaAuthorization.util.exception.QaLoginException;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaAuthorizationApplicationTests {
	
	@Resource(name="qaSysUserService")
	private QaUserService qaSysUserService;
	@Resource(name="qaLoginService")
	private QaLoginService qaLoginService;
	

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		String d;
		try {
			d = qaLoginService.login("huangyue43","Crp000");
			System.out.println(d);
		} catch (QaLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
