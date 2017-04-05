package com.boco.security.sam.config.jsonview.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.security.core.exception.ServiceException;
import com.boco.security.sam.config.jsonview.interfaces.IManagementCenterJsonView;
import com.boco.security.sam.config.jsonview.util.LinuxTools;

/**
 * 统一配置管理中心jsonview
 * @ClassName: ManagementCenterJsonView 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author madaoyong 
 * @date 2016年4月1日 上午9:24:20
 */
public class ManagementCenterJsonView implements IManagementCenterJsonView {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementCenterJsonView.class.getName());
	
	/**
	 * 获取设备信息
	 * @Title: getDeviceParameter 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param paramMap
	 * @param @return 设定文件 
	 * @return String[] 返回类型 
	 * @throws
	 */
	private String[] getDeviceParameter(Map<String, Object> paramMap) {
		//设备IP
		String deviceIp = String.valueOf(paramMap.get("deviceIp"));
		//设备登陆用户
		String deviceUser = String.valueOf(paramMap.get("deviceUser"));
		//设备root密码
		String deviceRootPass = String.valueOf(paramMap.get("deviceRootPass"));
		//连接符
		String prompt = String.valueOf(paramMap.get("prompt"));
		//root连接符
		return new String[]{deviceIp, deviceUser, deviceRootPass, prompt};
	}

	/**
	 * 启动dubbo服务
	 * directoryAddress：服务所在目录地址
	 */
	@SuppressWarnings("unchecked")
	public String startDubboService(Object obj) throws ServiceException {
		logger.info("启动dubbo服务"+obj);
		String result = "";
		try {
			Map<String, Object> paramMap = (Map<String, Object>)obj;
			LinuxTools tool = new LinuxTools(this.getDeviceParameter(paramMap));
			if(tool.clientLogin()){
				String directoryAddress = String.valueOf(paramMap.get("directoryAddress"));
				//进入服务目录地址
				result = tool.sendCmd("cd "+directoryAddress);
				logger.info("进入服务目录地址回显："+result);
				//赋予操作权限
				result = tool.sendCmd("chmod 777 start.sh");
				logger.info("赋予操作权限回显："+result);
				//启动服务脚本
				result = tool.sendCmd("./start.sh start_ssh");
				logger.info("启动服务脚本回显："+result);
			}else{
				logger.error("启动nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
			}
		} catch (Exception e) {
			logger.error("启动dubbo服务异常，可能无法登陆linux主机，请检查登陆属性！"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 停止dubbo服务
	 */
	@SuppressWarnings("unchecked")
	public String stopDubboService(Object obj) throws ServiceException {
		logger.info("停止dubbo服务"+obj);
		String result = "";
		try {
			Map<String, Object> paramMap = (Map<String, Object>)obj;
			LinuxTools tool = new LinuxTools(this.getDeviceParameter(paramMap));
			if(tool.clientLogin()){
				String directoryAddress = String.valueOf(paramMap.get("directoryAddress"));
				//进入服务目录地址
				result = tool.sendCmd("cd "+directoryAddress);
				logger.info("进入服务目录地址回显："+result);
				//赋予操作权限
				result = tool.sendCmd("chmod 777 start.sh");
				logger.info("赋予操作权限回显："+result);
				//启动服务脚本
				result = tool.sendCmd("./start.sh stop");
				logger.info("停止服务脚本回显："+result);
			}else{
				logger.error("停止nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
			}
		} catch (Exception e) {
			logger.error("停止dubbo服务异常，可能无法登陆linux主机，请检查登陆属性！"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 查看dubbo服务状态
	 */
	@SuppressWarnings("unchecked")
	public String lookDubboService(Object obj) throws ServiceException {
		logger.info("查看dubbo服务"+obj);
		String result = "";
		try {
			Map<String, Object> paramMap = (Map<String, Object>)obj;
			LinuxTools tool = new LinuxTools(this.getDeviceParameter(paramMap));
			if(tool.clientLogin()){
				String directoryAddress = String.valueOf(paramMap.get("directoryAddress"));
				//进入服务目录地址
				result = tool.sendCmd("cd "+directoryAddress);
				logger.info("进入服务目录地址回显："+result);
				//赋予操作权限
				result = tool.sendCmd("chmod 777 start.sh");
				logger.info("赋予操作权限回显："+result);
				//启动服务脚本
				result = tool.sendCmd("./start.sh status");
				logger.info("查看服务脚本回显："+result);
			}else{
				logger.error("查看nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
			}
		} catch (Exception e) {
			logger.error("查看dubbo服务异常，可能无法登陆linux主机，请检查登陆属性！"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 启动nodejs服务
	 */
	@SuppressWarnings("unchecked")
	public String startNodejsService(Object obj) throws ServiceException {
		logger.info("启动nodejs服务"+obj);
		String result = "";
		try {
			Map<String, Object> paramMap = (Map<String, Object>)obj;
			LinuxTools tool = new LinuxTools(this.getDeviceParameter(paramMap));
			if(tool.clientLogin()){
				String directoryAddress = String.valueOf(paramMap.get("directoryAddress"));
				//进入服务目录地址
				result = tool.sendCmd("cd "+directoryAddress);
				logger.info("进入服务目录地址回显："+result);
				//赋予操作权限
				result = tool.sendCmd("chmod 777 app.js");
				logger.info("赋予操作权限回显："+result);
				//启动服务脚本
				result = tool.sendCmd("forever -l "+directoryAddress+"/logs/access.log -e "+directoryAddress+"/logs/error.log -a start app.js");
				logger.info("查看服务脚本回显："+result);
			}else{
				logger.error("启动nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
			}
		} catch (Exception e) {
			logger.error("启动nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
		}
		return result;
	}
	
	/**
	 * 停止nodejs服务
	 */
	@SuppressWarnings("unchecked")
	public String stopNodejsService(Object obj) throws ServiceException {
		logger.info("停止nodejs服务"+obj);
		String result = "";
		try {
			Map<String, Object> paramMap = (Map<String, Object>)obj;
			LinuxTools tool = new LinuxTools(this.getDeviceParameter(paramMap));
			if(tool.clientLogin()){
				String directoryAddress = String.valueOf(paramMap.get("directoryAddress"));
				//进入服务目录地址
				result = tool.sendCmd("cd "+directoryAddress);
				logger.info("进入服务目录地址回显："+result);
				//赋予操作权限
				result = tool.sendCmd("chmod 777 app.js");
				logger.info("赋予操作权限回显："+result);
				//启动服务脚本
				result = tool.sendCmd("forever -l "+directoryAddress+"/logs/access.log -e "+directoryAddress+"/logs/error.log -a stop app.js");
				logger.info("查看服务脚本回显："+result);
			}else{
				logger.error("启动nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
			}
		} catch (Exception e) {
			logger.error("停止nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
		}
		return result;
	}
	
	/**
	 * 查看nodejs服务状态
	 */
	@SuppressWarnings("unchecked")
	public String lookNodejsService(Object obj) throws ServiceException {
		logger.info("查看nodejs服务"+obj);
		String isResult = "stopped";
		try {
			Map<String, Object> paramMap = (Map<String, Object>)obj;
			LinuxTools tool = new LinuxTools(this.getDeviceParameter(paramMap));
			if(tool.clientLogin()){
				String result = "";
				String directoryAddress = String.valueOf(paramMap.get("directoryAddress"));
				//进入服务目录地址
				result = tool.sendCmd("cd "+directoryAddress);
				logger.info("进入服务目录地址回显："+result);
				//赋予操作权限
				result = tool.sendCmd("chmod 777 app.js");
				logger.info("赋予操作权限回显："+result);
				//启动服务脚本
				result = tool.sendCmd("forever list");
				logger.info("查看服务脚本回显："+result);
				if(result.contains(directoryAddress)){
					isResult = "runnig";
				}
			}else{
				logger.error("查看nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
			}
		} catch (Exception e) {
			logger.error("查看nodejs服务异常，可能无法登陆linux主机，请检查登陆属性！");
		}
		return isResult;
	}
	
	/**
	 * 查看运行日志
	 */
	@SuppressWarnings("unchecked")
	public String lookServiceLogs(Object obj) throws ServiceException {
		logger.info("查看运行日志"+obj);
		String result = "";
		try {
			Map<String, Object> paramMap = (Map<String, Object>)obj;
			LinuxTools tool = new LinuxTools(this.getDeviceParameter(paramMap));
			if(tool.clientLogin()){
				String directoryAddress = String.valueOf(paramMap.get("directoryAddress"));
				String logsAddress = String.valueOf(paramMap.get("logsAddress"));
				String logsNumber = String.valueOf(paramMap.get("logsNumber"));
				//获取日志信息
				result = tool.sendCmd("tail -n "+logsNumber+" "+directoryAddress+"/logs/"+logsAddress);
			}else{
				logger.error("查看运行日志异常，可能无法登陆linux主机，请检查登陆属性！");
			}
		} catch (Exception e) {
			logger.error("查看运行日志异常，可能无法登陆linux主机，请检查登陆属性！");
		}
		return result;
	}
	
}
