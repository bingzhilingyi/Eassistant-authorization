/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaAuthorization.service.inte;

import java.util.Set;

import com.crp.qa.qaAuthorization.domain.dto.QaSysUserDto;
import com.crp.qa.qaAuthorization.util.exception.QaLoginException;

/**
 * @author huangyue
 * @date 2018年5月31日 下午6:31:01
 * @ClassName QaLoginService
 */
public interface QaLoginService {

	/**
	 * 登录
	 * @author huangyue
	 * @date 2018年5月31日 下午6:37:14
	 * @param account
	 * @param password
	 * @return
	 * @throws QaLoginException
	 */
	public QaSysUserDto login(String account,String password) throws QaLoginException;
	
	/**
	 * 判断是否登录
	 * @author huangyue
	 * @date 2018年5月31日 下午7:00:38
	 * @param token
	 * @return
	 * @throws QaLoginException
	 */
	public boolean isLogin(String token) throws QaLoginException;
	
	
	/**
	 * 根据token获取当前用户信息
	 * @param token
	 * @return
	 * @throws QaLoginException
	 * @Date 2018年7月11日
	 * @author huangyue
	 */
	public QaSysUserDto findByToken(String token) throws QaLoginException;
}
