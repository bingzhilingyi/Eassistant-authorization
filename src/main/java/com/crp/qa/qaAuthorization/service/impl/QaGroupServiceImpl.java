package com.crp.qa.qaAuthorization.service.impl;

import com.crp.qa.qaAuthorization.domain.pojo.QaSysGroup;
import com.crp.qa.qaAuthorization.domain.dto.QaPagedDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupSimpleDto;
import com.crp.qa.qaAuthorization.service.inte.QaGroupService;
import com.crp.qa.qaAuthorization.service.impl.BaseServiceImpl;
import com.crp.qa.qaAuthorization.util.exception.QaGroupException;
import com.crp.qa.qaAuthorization.repository.QaSysGroupRepository;
import com.crp.qa.qaAuthorization.repository.QaSysUserGroupRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.dozer.MappingException;

/**
 * group service
 * @author huangyue
 * @date 2018-7-2
 */
@Service(value="qaGroupService")
@Transactional
public class QaGroupServiceImpl extends BaseServiceImpl<QaSysGroup> implements QaGroupService{
    
    @Autowired
    private QaSysGroupRepository qaSysGroupRepository; 
    @Autowired
    private QaSysUserGroupRepository qaSysUserGroupRepository; 
    
    /**
     * get all group
     * @author huangyue
     * @date 2018-7-2
     * @return List<QaSysGroupDto>
     * @throws QaGroupExcepthion
     */
    @Override
    public List<QaSysGroupSimpleDto> findAll() throws QaGroupException{
        //find all
        List<QaSysGroup> tList = qaSysGroupRepository.findAll();
        try{
        	return pojoToDto(QaSysGroupSimpleDto.class,tList);
        }catch(InstantiationException | IllegalAccessException | MappingException e){
        	throw new QaGroupException("pojo to dto failed",e);
        }
    }
     
    @Override
    public QaSysGroupDto findById(Integer groupId) throws QaGroupException {
    	//ensure group id exists
    	if(groupId==null) {
    		throw new QaGroupException("groupId not exists!");
    	}
    	//find by group id
    	Optional<QaSysGroup> op = qaSysGroupRepository.findById(groupId);
    	if(op.isPresent()) {
    		QaSysGroup t = op.get();
    		QaSysGroupDto d = new QaSysGroupDto();
    		try{
    			mapper.map(t, d);
    			return d;
    		}catch(MappingException e){
        		throw new QaGroupException("pojo to dto failed",e);
        	}
    	}
    	return null; 
    }


    /**
      * get by group name
      * @author huangyue
      * @date 2018-7-2
      * @return QaSysGroupDto
      * @throws QaGroupExcepthion
      */
    @Override
    public QaSysGroupDto findByGroupName(String groupName) throws QaGroupException{
    	//ensure groupName exists
        if(groupName==null || "".equals(groupName)){
           throw new QaGroupException("groupName not exists!");
        }
        //find by group name
        QaSysGroup t = qaSysGroupRepository.findByGroupName(groupName);
        if(t!=null) {
        	try{
        		QaSysGroupDto d = new QaSysGroupDto();
        		mapper.map(t,d);
        		return d;
        	}catch(MappingException e){
        		throw new QaGroupException("pojo to dto failed",e);
        	}
        }
        return null;
    }


    /**
     * get by group name like
     * @author huangyue	
     * @date 2018-7-2
     * @return List<QaSysGroupDto>
     * @throws QaGroupExcepthion
     */
    @Override
    public QaPagedDto<QaSysGroupSimpleDto> findByGroupNameLike(String groupName,Integer page,Integer size) throws QaGroupException{	 
        //初始化参数
    	page = page==null?1:page;
    	size = size==null?20:size;
            	 
    	//定义查询结果
 		List<QaSysGroup> tList = new ArrayList<QaSysGroup>();
    	 
    	//设置分页信息
 		Pageable pageable = PageRequest.of(page,size);
    	 
    	//find by group name like
    	Page<QaSysGroup> pg = qaSysGroupRepository.findByGroupNameContainingOrderByGroupName(groupName.trim(), pageable);
    	tList = pg.getContent();
    	 
    	//定义返回值
 		QaPagedDto<QaSysGroupSimpleDto> returnDto = new QaPagedDto<QaSysGroupSimpleDto>();
 		try {
 			 List<QaSysGroupSimpleDto> dList = pojoToDto(QaSysGroupSimpleDto.class,tList);
 			 returnDto.setList(dList);
 			 returnDto.setTotalElements(pg.getTotalElements());
 			 returnDto.setTotalPages(pg.getTotalPages());
 		} catch (IllegalAccessException | InstantiationException e) {
 			throw new QaGroupException("pojo转dto失败",e);
 		} 
 		return returnDto;
    }

	@Override
	public QaSysGroupDto save(QaSysGroupDto dto) throws QaGroupException {
		if(dto==null) {
			throw new QaGroupException("传入对象为空，保存失败！");
		}else if(dto.getGroupId()!=null) {
			throw new QaGroupException("传入对象已有主键，保存失败！");
		}else if(qaSysGroupRepository.existsByGroupName(dto.getGroupName())) {
			throw new QaGroupException("权限组名重复，保存失败！");
		}
		try {
			//dto 转 pojo
			QaSysGroup t = new QaSysGroup();
			mapper.map(dto, t);
			//设置日期
			t.setCreationDate(new Date());
			t.setLastUpdateDate(new Date());
			//保存
			t = qaSysGroupRepository.save(t);
			//pojo 转 dto
			mapper.map(t, dto);
			return dto;
		} catch (MappingException e) {
			throw new QaGroupException("pojo转dto失败",e);
		}
	}

	@Override
	public void delete(Integer id) throws QaGroupException {
		if(id==null) {
			throw new QaGroupException("传入对象无主键，删除失败！");
		}else if(qaSysUserGroupRepository.existsByGroupIdAndUserIdNotNull(id)) {
			//判断当前角色是否有用户在使用
			throw new QaGroupException("当前角色已被用户使用，不能删除！");
		}		
		//删除
		qaSysGroupRepository.deleteById(id);
	}

	@Override
	public QaSysGroupDto update(QaSysGroupDto dto) throws QaGroupException {
		if(dto==null) {
			throw new QaGroupException("传入对象为空，更新失败！");
		}else if(dto.getGroupId()==null) {
			throw new QaGroupException("传入对象无主键，更新失败！");
		}else if(!qaSysGroupRepository.existsById(dto.getGroupId())) {
			throw new QaGroupException("传入对象在数据库中不存在，更新失败！");
		}else if(qaSysGroupRepository.existsByGroupNameAndGroupIdNot(dto.getGroupName(),dto.getGroupId())) {
			throw new QaGroupException("权限组名重复，更新失败！");
		}
		try {
			//dto 转 pojo
			QaSysGroup t = new QaSysGroup();
			mapper.map(dto, t);
			//设置更新日期
			t.setLastUpdateDate(new Date());
			//保存
			t = qaSysGroupRepository.saveAndFlush(t);
			//pojo 转 dto
			mapper.map(t, dto);
			return dto;
		} catch (MappingException e) {
			throw new QaGroupException("pojo转dto失败",e);
		}
	}

        
}
