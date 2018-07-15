package com.crp.qa.qaAuthorization;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaAuthorization.domain.dto.QaPagedDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupRightsDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupSimpleDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto.UserRights;
import com.crp.qa.qaAuthorization.domain.pojo.QaSysUser;
import com.crp.qa.qaAuthorization.repository.QaSysUserRepository;
import com.crp.qa.qaAuthorization.service.inte.QaGroupService;
import com.crp.qa.qaAuthorization.service.inte.QaLoginService;
import com.crp.qa.qaAuthorization.service.inte.QaUserService;
import com.crp.qa.qaAuthorization.util.exception.QaGroupException;
import com.crp.qa.qaAuthorization.util.exception.QaLoginException;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaAuthorizationApplicationTests {
	
	@Resource(name="qaSysUserService")
	private QaUserService qaSysUserService;
	@Resource(name="qaLoginService")
	private QaLoginService qaLoginService;
	@Resource(name="qaGroupService")
	private QaGroupService qaGroupService;
	@Autowired
	public QaSysUserRepository qaSysUserRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		System.out.println(null instanceof QaSysUserDto.UserRights);
	}
}
