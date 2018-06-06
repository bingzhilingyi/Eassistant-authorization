/**
 * huangyue
 * 2018年5月29日
 */
package com.crp.qa.qaAuthorization.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crp.qa.qaAuthorization.util.transfer.QaBaseTransfer;

/**
 * @author huangyue
 * @date 2018年5月29日 上午10:09:19
 * @ClassName QaBaseForwardController
 */
@Component 
public class QaBaseController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(QaBaseController.class);
	
	/**
	 * 返回错误信息并打印日志的通用方法
	 * @author huangyue
	 * @date 2018年5月15日 下午1:59:26
	 * @param e
	 * @param dto
	 */
	protected void returnError(Exception e,QaBaseTransfer dto) {
		LOGGER.error(e.getMessage(),e);
		dto.setStatus("failed");
		dto.setMessage(e.getMessage());
	}
}
