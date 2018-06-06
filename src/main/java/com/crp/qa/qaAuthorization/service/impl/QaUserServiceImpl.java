package com.crp.qa.qaAuthorization.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.crp.qa.qaAuthorization.domain.dto.QaPagedDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.domain.pojo.QaSysUser;
import com.crp.qa.qaAuthorization.repository.QaSysUserRepository;
import com.crp.qa.qaAuthorization.service.inte.QaUserService;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;


/**
 * 用户管理服务
 * @author huangyue
 * @date 2018年5月10日 下午5:06:03
 * @ClassName QaSysUserServiceImpl
 */
@Service(value="qaSysUserService")
@Transactional
public class QaUserServiceImpl extends BaseServiceImpl<QaSysUser> implements QaUserService{
		
	@Autowired
	public QaSysUserRepository qaSysUserRepository;
	
	@Override
	public List<QaSysUserDto> findAll() throws QaUserException{	
		//查询所有
		List<QaSysUser> tList = qaSysUserRepository.findAll();
		try {
			return pojoToDto(QaSysUserDto.class,tList);
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaUserException("pojo转dto失败",e);
		}
	};
	
	@Override
	public QaPagedDto<QaSysUserDto> findPagedAll(Integer page,Integer size) throws QaUserException{
		//初始化参数
		page = page==null?1:page;
		size = size==null?20:size;
		//设置分页信息
		Pageable pageable = PageRequest.of(page,size);
		//查询分页信息
		Page<QaSysUser> pagedUser = qaSysUserRepository.findAll(pageable);		
		try {
			List<QaSysUserDto> dList = pojoToDto(QaSysUserDto.class,pagedUser.getContent());
			//返回信息
			QaPagedDto<QaSysUserDto> returnDto = new QaPagedDto<QaSysUserDto>();
			returnDto.setList(dList); //数据
			returnDto.setTotalElements(pagedUser.getTotalElements()); //总条目
			returnDto.setTotalPages(pagedUser.getTotalPages()); //总页数
			return returnDto;
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaUserException("pojo转dto失败",e);
		}		
	}
	
	@Override
	public QaSysUserDto findById(Integer id) throws QaUserException{
		//如果id为null，抛出错误
		if(id==null) {
			throw new QaUserException("传入的用户id为空!");
		}
		//查询数据
		Optional<QaSysUser> t = qaSysUserRepository.findById(id);
		
		//如果不存在，返回null，否则返回dto
		if(t.isPresent()) {
			try {
				//定义返回对象
				QaSysUserDto d = new QaSysUserDto();
				PropertyUtils.copyProperties(d,t.get());
				return d;
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new QaUserException("pojo转dto失败",e);
			}
		}
		return null;
	}
	
	@Override
	public QaSysUserDto findByAccountEqual(String account) throws QaUserException{
		//如果账户不存在，报错
		if(account==null||account.equals("")) {
			throw new QaUserException("用户账户为空");
		}
		//查询数据
		QaSysUser t = qaSysUserRepository.findByUserAccount(account);
		
		//如果不存在，返回null，否则返回dto
		if(t!=null) {
			try {
				//定义返回对象
				QaSysUserDto d = new QaSysUserDto();
				PropertyUtils.copyProperties(d,t);
				return d;
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new QaUserException("pojo转dto失败",e);
			}
		}
		return null;
	}
	
	@Override
	public List<QaSysUserDto> findByAccountOrName(String account) throws QaUserException{
		//如果传入account为空，直接查全部		
		if(account==null||account.equals("")) {
			return this.findAll();
		}
		//查询数据
		List<QaSysUser> tList = qaSysUserRepository.findAllByUserAccountContainingOrUserNameContaining(account,account);
		try {
			return pojoToDto(QaSysUserDto.class,tList);
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaUserException("pojo转dto失败",e);
		}
	}
	
	@Override
	public QaPagedDto<QaSysUserDto> findPagedByAccountOrName(String account,Integer page,Integer size,boolean isSlice) throws QaUserException{
		//如果传入account为空，直接查全部		
		if(account==null||account.equals("")) {
			return this.findPagedAll(page, size);
		}
		
		//初始化参数
		page = page==null?1:page;
		size = size==null?20:size;
		long totalElements = 0;
		int totalPages = 0;
		
		//定义查询结果
		List<QaSysUser> tList = new ArrayList<QaSysUser>();
		
		//设置分页信息
		Pageable pageable = PageRequest.of(page,size);

		//查询数据
		if(isSlice) {
			Slice<QaSysUser> userSlice = qaSysUserRepository.getByUserAccountContainingOrUserNameContaining(account, account, pageable);
			tList = userSlice.getContent();
		}else {
			Page<QaSysUser> userPage = qaSysUserRepository.findByUserAccountContainingOrUserNameContaining(account, account, pageable);
			tList = userPage.getContent();
			totalElements = userPage.getTotalElements();
			totalPages = userPage.getTotalPages();
		}
		
		//定义返回值
		QaPagedDto<QaSysUserDto> returnDto = new QaPagedDto<QaSysUserDto>();
		try {
			 List<QaSysUserDto> dList = pojoToDto(QaSysUserDto.class,tList);
			 returnDto.setList(dList);
			 returnDto.setTotalElements(totalElements);
			 returnDto.setTotalPages(totalPages);
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaUserException("pojo转dto失败",e);
		} 
		return returnDto;
	}
	
	@Override
	public QaSysUserDto save(QaSysUserDto dto) throws QaUserException{
		if(dto==null) {
			throw new QaUserException("传入对象为空，保存失败！");
		}else if(dto.getUserId()!=null) {
			throw new QaUserException("传入对象已有主键，保存失败！");
		}
		try {
			//dto 转 pojo
			QaSysUser t = new QaSysUser();
			PropertyUtils.copyProperties(t,dto);
			//设置日期
			t.setCreationDate(new Date());
			t.setLastUpdateDate(new Date());
			//保存
			t = qaSysUserRepository.save(t);
			//pojo 转 dto
			PropertyUtils.copyProperties(dto,t);
			return dto;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new QaUserException("pojo转dto失败",e);
		}
	}
	
	@Override
	public void deleteById(Integer id) throws QaUserException{
		if(id==null) {
			throw new QaUserException("传入对象无主键，删除失败！");
		}else if(id==1) {
			throw new QaUserException("管理员用户不允许删除！");
		}
		//删除
		qaSysUserRepository.deleteById(id);
	}
	
	@Override
	public QaSysUserDto update(QaSysUserDto dto) throws QaUserException{
		if(dto==null) {
			throw new QaUserException("传入对象为空，更新失败！");
		}else if(dto.getUserId()==null) {
			throw new QaUserException("传入对象无主键，更新失败！");
		}else if(!qaSysUserRepository.existsById(dto.getUserId())) {
			throw new QaUserException("传入对象在数据库中不存在，更新失败！");
		}
		try {
			//dto 转 pojo
			QaSysUser t = new QaSysUser();
			PropertyUtils.copyProperties(t,dto);
			//设置更新日期
			t.setLastUpdateDate(new Date());
			//保存
			t = qaSysUserRepository.save(t);
			//pojo 转 dto
			PropertyUtils.copyProperties(dto,t);
			return dto;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new QaUserException("pojo转dto失败",e);
		}
	}
}
