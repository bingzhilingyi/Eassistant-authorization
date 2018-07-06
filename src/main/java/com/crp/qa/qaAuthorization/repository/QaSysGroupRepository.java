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
}
