/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaAuthorization.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupRightsDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserGroupDto;
import com.crp.qa.qaAuthorization.service.inte.QaGroupService;
import com.crp.qa.qaAuthorization.service.inte.QaLoginService;
import com.crp.qa.qaAuthorization.service.inte.QaUserService;
import com.crp.qa.qaAuthorization.util.exception.QaGroupException;
import com.crp.qa.qaAuthorization.util.exception.QaLoginException;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;

/**
 * @author huangyue
 * @date 2018年5月31日 下午6:37:47
 * @ClassName QaLoginServiceImpl
 */
@Service(value="qaLoginService")
@Transactional
public class QaLoginServiceImpl extends BaseServiceImpl implements QaLoginService {
	
	@Resource(name="qaSysUserService")
	private QaUserService qaSysUserService;
	@Resource(name="qaGroupService")
	private QaGroupService qaGroupService;
	@Resource(name="qaTokenService")
	private QaTokenServiceImpl qaTokenServiceImpl;


	@Override
	public QaSysUserDto login(String account, String password) throws QaLoginException {
		
		if(StringUtils.isEmpty(account)||StringUtils.isEmpty(password)) {
			throw new QaLoginException("用户名或密码为空！");
		}
		try {
			//find user by account
			QaSysUserDto user =  qaSysUserService.findByAccountEqual(account);
			
			//验证密码,如果验证通过存储token
			if(user!=null&&password.equals(user.getUserPassword())) {
				//根据用户的角色找出所有权限
				user.setRights(getRightsByGroups(user.getQaSysUserGroup()));			
				//生成一个token
				String token = qaTokenServiceImpl.generateToken();
				//把token放入user里
				user.setToken(token);
				//以token为key保存用户信息
				qaTokenServiceImpl.setToken(token, user);
				//返回user
				return user;
			}else {
				//如果登录失败，日志记录一下
				LOGGER.warn("账号或密码错误");
				return new QaSysUserDto();
			}
		} catch (QaUserException e ) {
			throw new QaLoginException("查找用户信息报错了",e);
		} catch (QaGroupException e){
			throw new QaLoginException("查找角色或权限信息报错了",e);
		}
	}


	@Override
	public boolean isLogin(String token) throws QaLoginException {
		if(StringUtils.isEmpty(token)) {
			throw new QaLoginException("token为空！");
		}
		return qaTokenServiceImpl.isExists(token);
	}


	@Override
	public QaSysUserDto findByToken(String token) throws QaLoginException {
		if(StringUtils.isEmpty(token)) {
			throw new QaLoginException("token为空！");
		}else if(!qaTokenServiceImpl.isExists(token)) {
			throw new QaLoginException("token为错误或不存在！");
		}
		String user = qaTokenServiceImpl.getByToken(token);
		QaSysUserDto userdto = JSONObject.parseObject(user, QaSysUserDto.class);
		//清除密码信息
		userdto.setUserPassword("就不告诉你");
		return userdto;
	}

	private Set<QaSysUserDto.UserRights> getRightsByGroups(Set<QaSysUserGroupDto> qaSysUserGroup) throws QaGroupException{
		//定义权限集合
		Set<QaSysUserDto.UserRights> rights = new HashSet<QaSysUserDto.UserRights>();
		
		//遍历角色，把拥有的权限集中起来
		for(QaSysUserGroupDto userGroup:qaSysUserGroup) {
			
			//查出角色具体信息
			QaSysGroupDto group;
			group = qaGroupService.findById(userGroup.getGroupId());
		
			Set<QaSysGroupRightsDto> qaSysGroupRights = group.getQaSysGroupRights();
		
			//遍历该角色的权限
			for(QaSysGroupRightsDto right:qaSysGroupRights) {
				//判断该权限在已有权限中是否存在，默认不存在
				boolean isExist = false; 
				//遍历已有权限
				for(QaSysUserDto.UserRights nowRight:rights) {
					//如果该权限已存在，更新一下权限，并把isExist设为true，并跳出循环
					if(nowRight.getRightsCode().equals(right.getRightsCode())) {
						//如果当前权限的允许新建，更新已有权限
						if(right.getRightsCreate().equals("Y")) {
							nowRight.setRightsCreate("Y");
						}
						//如果当前权限的允许更新，更新已有权限
						if(right.getRightsUpdate().equals("Y")) {
							nowRight.setRightsUpdate("Y");
						}
						//如果当前权限的允许查询，更新已有权限
						if(right.getRightsSearch().equals("Y")) {
							nowRight.setRightsSearch("Y");
						}
						//如果当前权限的允许删除，更新已有权限
						if(right.getRightsDelete().equals("Y")) {
							nowRight.setRightsDelete("Y");
						}
						//把权限是否存在设置为true
						isExist = true;
						break;
					}
				}
				//遍历完已有权限，如果仍然是不存在，就往已有权限里加条新数据
				if(!isExist) {
					QaSysUserDto.UserRights userRights = new QaSysUserDto.UserRights();
					mapper.map(right, userRights);
					rights.add(userRights);
				}
				
			}
		}	
		
		return rights;
	}
}
