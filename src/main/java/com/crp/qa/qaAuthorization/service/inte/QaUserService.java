package com.crp.qa.qaAuthorization.service.inte;

import java.util.List;

import com.crp.qa.qaAuthorization.domain.dto.QaPagedDto;
import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.domain.pojo.QaSysUser;
import com.crp.qa.qaAuthorization.util.exception.QaUserException;

/**
 * 用户管理服务接口
 * @author huangyue
 * @date 2018年5月9日 下午9:42:46
 * @ClassName QaSysUserService
 */
public interface QaUserService extends BaseService<QaSysUser>{
	/**
	 * 获取所有用户
	 * @author huangyue
	 * @date 2018年5月14日 下午4:58:57
	 * @return
	 * @throws QaUserException
	 */
	public List<QaSysUserDto> findAll() throws QaUserException;
	
	/**
	 * 获取分页的所有用户
	 * @author huangyue
	 * @date 2018年5月14日 下午4:29:21
	 * @param page 第几页
	 * @param size 每页几条数据
	 * @return
	 * @throws QaUserException
	 */
	public QaPagedDto<QaSysUserDto> findPagedAll(Integer page,Integer size) throws QaUserException;
	
	/**
	 * 根据id查找用户
	 * @author huangyue
	 * @date 2018年5月14日 下午4:59:12
	 * @param id
	 * @return
	 * @throws QaUserException
	 */
	public QaSysUserDto findById(Integer id) throws QaUserException;
	
	/**
	 * 根据账号精确查找用户
	 * @author huangyue
	 * @date 2018年5月14日 下午5:10:22
	 * @param account 账户
	 * @return
	 * @throws QaUserException
	 */
	public QaSysUserDto findByAccountEqual(String account) throws QaUserException;
	
	/**
	 * 根据账号或姓名模糊查找用户
	 * @author huangyue
	 * @date 2018年5月14日 下午5:10:22
	 * @param account 账户或姓名
	 * @return
	 * @throws QaUserException
	 */
	public List<QaSysUserDto> findByAccountOrName(String account) throws QaUserException;
	
	/**
	 * 根据账号或姓名模糊查找用户,分页
	 * @author huangyue
	 * @date 2018年5月15日 上午11:00:08
	 * @param account 账户或姓名
	 * @param page 当前页数
	 * @param size 每页大小
	 * @param isSlice 是否返回slice。
	 * 			false :  返回page，包含总页数及总条目数，效率低。
	 * 			true: 返回slice，只有数据，没有总页数和总条目数，效率高。
	 * @return
	 * @throws QaUserException
	 */
	public QaPagedDto<QaSysUserDto> findPagedByAccountOrName(String account,Integer page,Integer size,boolean isSlice) throws QaUserException;
	
	/**
	 * 保存传入的对象并返回保存后的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午5:04:11
	 * @param dto
	 * @return
	 * @throws QaUserException
	 */
	public QaSysUserDto save(QaSysUserDto dto) throws QaUserException;
	/**
	 * 根据id删除对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:32:50
	 * @param dto
	 * @throws QaUserException
	 */
	public void deleteById(Integer id) throws QaUserException;
	
	/**
	 * 根据传入的对象更新并返回更新后的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:47:29
	 * @param dto
	 * @return
	 * @throws QaUserException
	 */
	public QaSysUserDto update(QaSysUserDto dto) throws QaUserException;
}
