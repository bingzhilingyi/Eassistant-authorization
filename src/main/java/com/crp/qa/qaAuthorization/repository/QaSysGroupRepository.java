package com.crp.qa.qaAuthorization.repository; 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crp.qa.qaAuthorization.domain.pojo.QaSysGroup;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QaSysGroupRepository extends JpaRepository<QaSysGroup,Integer> {

	/**
	 * find by group_name
	 * @author huangyue
	 * @date 2018年7月2日 
	 * @param groupName
	 * @return QaSysGroup
	 */
    public QaSysGroup findByGroupName(String groupName);


	/**
	 * find by group_name like
	 * @author huangyue
	 * @date 2018年7月2日 
	 * @param groupName
	 * @param pageable
	 * @return Page<QaSysGroup>
	 */
    public Page<QaSysGroup> findByGroupNameContainingOrderByGroupName(String groupName,Pageable pageable);
    
    /**
     * 判断组名是否存在
     * @param groupName
     * @return
     * @Date 2018年7月10日
     * @author huangyue
     */
    public boolean existsByGroupName(String groupName);
    
    /**
     * 判断ID不为传入值时，组名是否存在
     * @param groupName
     * @param groupId
     * @return
     * @Date 2018年7月10日
     * @author huangyue
     */
    public boolean existsByGroupNameAndGroupIdNot(String groupName,Integer groupId);
}
