package com.crp.qa.qaAuthorization.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crp.qa.qaAuthorization.domain.pojo.QaSysUser;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QaSysUserRepository extends JpaRepository<QaSysUser,Integer> {
	
	/**
	 * 根据账户精确查找用户
	 * @author huangyue
	 * @date 2018年5月15日 上午11:12:39
	 * @param userAccount
	 * @return
	 */
	public QaSysUser findByUserAccount(String userAccount);
	
	/**
	 * 根据账户或姓名模糊查找，不分页
	 * @author huangyue
	 * @date 2018年5月15日 下午1:05:49
	 * @param userAccount
	 * @param userName
	 * @return
	 */
	public List<QaSysUser> findAllByUserAccountContainingOrUserNameContaining(String userAccount,String userName);
	
	/**
	 * 根据账户或姓名模糊查找，分页，返回page，含有数据总数与总页数，效率较低
	 * @author huangyue
	 * @date 2018年5月15日 下午12:57:59
	 * @param userAccount
	 * @param userName
	 * @param pageable
	 * @return
	 */
	public Page<QaSysUser> findByUserAccountContainingOrUserNameContaining(String userAccount,String userName,Pageable pageable);
	/**
	 * 根据账户或姓名模糊查找，分页，返回slice,只有数据，效率更高
	 * @author huangyue
	 * @date 2018年5月15日 下午12:59:25
	 * @param userAccount
	 * @param userName
	 * @param pageable
	 * @return
	 */
	public Slice<QaSysUser> getByUserAccountContainingOrUserNameContaining(String userAccount,String userName,Pageable pageable);
	
	
	
	static String baseSql = "FROM QaSysUser qsu,QaSysUserGroup qsug,QaSysGroup qsg "
			+ " WHERE qsu.userId = qsug.userId"
			+ " and qsug.groupId = qsg.groupId"
			+ " and qsg.groupName like %?1%";
	
	/**
	 * 根据角色查询用户
	 * @param groupName
	 * @param pageable
	 * @return
	 * @Date 2018年7月10日
	 * @author huangyue
	 */
	@Query(value = "SELECT  qsu " + baseSql,
		    countQuery = "SELECT count(1) " + baseSql)
	Page<QaSysUser> findByGroupNameContaining(String groupName, Pageable pageable);
}