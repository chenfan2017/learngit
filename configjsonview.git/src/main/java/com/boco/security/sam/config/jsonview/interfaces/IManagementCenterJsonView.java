package com.boco.security.sam.config.jsonview.interfaces;

import com.boco.security.core.exception.ServiceException;


/**
 * 统一配置管理中心jsonview
 * @ClassName: IManagementCenterJsonView 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author madaoyong 
 * @date 2016年4月1日 上午9:23:17
 */
public interface IManagementCenterJsonView {
	
	/**
	 * 启动dubbo服务
	 * @Title: startDubboService 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param obj deviceIp、deviceUser、deviceRootPass、devicePass、prompt、rootPrompt、directoryAddress
	 * @param @return
	 * @param @throws ServiceException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String startDubboService(Object obj) throws ServiceException;
	
	/**
	 * 停止dubbo服务
	 * @Title: stopDubboService 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param obj deviceIp、deviceUser、deviceRootPass、devicePass、prompt、rootPrompt、directoryAddress
	 * @param @return
	 * @param @throws ServiceException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String stopDubboService(Object obj) throws ServiceException;
	
	/**
	 * 查看dubbo服务状态
	 * @Title: lookDubboService 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param obj deviceIp、deviceUser、deviceRootPass、devicePass、prompt、rootPrompt、directoryAddress
	 * @param @return
	 * @param @throws ServiceException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String lookDubboService(Object obj) throws ServiceException;
	
	/**
	 * 启动nodejs服务
	 * @Title: startNodejsService 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param obj  deviceIp、deviceUser、deviceRootPass、devicePass、prompt、rootPrompt、directoryAddress
	 * @param @return
	 * @param @throws ServiceException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String startNodejsService(Object obj) throws ServiceException;
	
	/**
	 * 停止nodejs服务
	 * @Title: stopNodejsService 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param obj deviceIp、deviceUser、deviceRootPass、devicePass、prompt、rootPrompt、directoryAddress
	 * @param @return
	 * @param @throws ServiceException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String stopNodejsService(Object obj) throws ServiceException;
	
	/**
	 * 查看nodejs服务状态
	 * @Title: lookNodejsService 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param obj deviceIp、deviceUser、deviceRootPass、devicePass、prompt、rootPrompt、directoryAddress
	 * @param @return
	 * @param @throws ServiceException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String lookNodejsService(Object obj) throws ServiceException;
	
	/**
	 * 查看运行日志
	 * @Title: lookServiceLogs 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param obj deviceIp、deviceUser、deviceRootPass、devicePass、prompt、rootPrompt、directoryAddress、logsAddress
	 * @param @return
	 * @param @throws ServiceException 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String lookServiceLogs(Object obj) throws ServiceException;
	
}