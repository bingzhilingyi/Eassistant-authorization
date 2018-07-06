package com.crp.qa.qaAuthorization.service.inte;

import com.crp.qa.qaAuthorization.domain.pojo.QaSysGroup;
import com.crp.qa.qaAuthorization.domain.dto.QaPagedDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysGroupSimpleDto;
import com.crp.qa.qaAuthorization.util.exception.QaGroupException;
import com.crp.qa.qaAuthorization.service.inte.BaseService;

import java.util.List;

/**
 * group service interface
 * @author huangyue
 * @date 2018-7-2
 */
public interface QaGroupService extends BaseService<QaSysGroup>{

    /**
     * get all group
     * @author huangyue
     * @date 2018-7-2
     * @return List<QaSysGroupDto>
     * @throws QaGroupExcepthion
     */
     public List<QaSysGroupSimpleDto> findAll() throws QaGroupException;
     
     
     /**
      * find by id
      * @param groupId
      * @return
      * @throws QaGroupException
      * @Date 2018年7月4日
      * @author huangyue
      */
     public QaSysGroupDto findById(Integer groupId) throws QaGroupException;


    /**
     * get by group name
     * @author huangyue
     * @date 2018-7-2
     * @return QaSysGroupDto
     * @throws QaGroupExcepthion
     */
     public QaSysGroupDto findByGroupName(String groupName) throws QaGroupException;


    /**
     * get by group name like
     * @author huangyue
     * @date 2018-7-2
     * @return List<QaSysGroupDto>
     * @throws QaGroupExcepthion
     */
     public QaPagedDto<QaSysGroupSimpleDto> findByGroupNameLike(String groupName,Integer page,Integer size) throws QaGroupException;
     
     /**
      * create
      * @param dto
      * @return
      * @throws QaGroupException
      * @Date 2018年7月4日
      * @author huangyue
      */
     public QaSysGroupDto save(QaSysGroupDto dto) throws QaGroupException;
     
     /**
      * delete by id
      * @param id
      * @throws QaGroupException
      * @Date 2018年7月4日
      * @author huangyue
      */
     public void delete(Integer id) throws QaGroupException;
     
     /**
      * update
      * @param dto
      * @return
      * @throws QaGroupException
      * @Date 2018年7月4日
      * @author huangyue
      */
     public QaSysGroupDto update(QaSysGroupDto dto) throws QaGroupException;
}
