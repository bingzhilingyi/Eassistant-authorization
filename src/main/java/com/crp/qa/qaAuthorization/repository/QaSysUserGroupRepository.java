package com.crp.qa.qaAuthorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crp.qa.qaAuthorization.domain.pojo.QaSysUserGroup;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QaSysUserGroupRepository extends JpaRepository<QaSysUserGroup,Integer> {
	
	/**
	 * 判断userId不为空的情况下，group id是否存在
	 * @param groupId
	 * @return
	 * @Date 2018年7月10日
	 * @author huangyue
	 */
	boolean existsByGroupIdAndUserIdNotNull(Integer groupId);
}