package com.boco.security.sam.config.jsonview.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.iam.common.resoperation.ResourceException;
import com.boco.iam.common.resoperation.ResourceFactory;
import com.boco.iam.common.resoperation.device.unix.ArgUNIXLogin;
import com.boco.iam.common.resoperation.device.unix.UNIXSamResource;
import com.boco.iam.common.resoperation.device.unix.impl.LinuxSam;

public class LinuxTools {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	ResourceFactory resourceFactory  = new ResourceFactory();
	LinuxSam linux ;
	ArgUNIXLogin argLogin;
	String ip;
	
	/**
	 * 需要初始化加载的prober引擎集合对象 
	*     
	* 方法描述：   
	* 创建人：madaoyong   
	* 创建时间：2015年6月23日 上午8:48:39   
	* 修改人：madaoyong   
	* 修改时间：2015年5月23日 上午8:48:39   
	* 修改备注：   
	* @version    
	*
	 */
	@SuppressWarnings("static-access")
	public LinuxTools(String[] parameter) {
		logger.info("需要初始化加载的Linux_SCAM引擎底层资源操作包");
		this.linux = (LinuxSam)resourceFactory.createUNIXSamDevice("DIC_UNIX_HOST_LINUX_SAM");
		String prompt [] = {parameter[3]};
		String rootPrompt [] = {parameter[3]};
		this.ip = parameter[0];
		linux.setReadTimeOut(1200000);
		linux.setEchoSleepTime(1200000);
		boolean isAdminUser = false;
		if("root".equals(parameter[1])){
			isAdminUser = true;
		}
		this.argLogin = new ArgUNIXLogin();
		this.argLogin.loginName = parameter[1];
		this.argLogin.loginPassword = parameter[2];
		//this.argLogin.isRootLogin = true;
		this.argLogin.isAdminUser = isAdminUser;
		this.argLogin.rootPrompt = rootPrompt;
		this.argLogin.prompt = prompt;
		this.argLogin.rootPassword = parameter[2];
		this.argLogin.isSUDO = false;
		this.argLogin.isSU = "su - ";
	}
	
	/**
	 * 发送登陆请求
	*     
	* 方法描述：   
	* 创建人：madaoyong   
	* 创建时间：2015年6月23日 上午8:47:57   
	* 修改人：madaoyong   
	* 修改时间：2015年6月23日 上午8:47:57   
	* 修改备注：   
	* @version    
	*
	 */
	public boolean clientLogin(){
		logger.info("登陆LINUX指令");
		boolean isLogin = false;
		try {
			linux.open(ip,UNIXSamResource.DIC_ACCESSPROTOCOL_TYPE_UNIXHOST_SSH2);
			isLogin = linux.login(argLogin, null);
			logger.info("登陆LINUX结果："+isLogin);
		} catch (ResourceException e) {
			System.out.println("异常： " + e.getMessage());
		}
		return isLogin;
	}
	
	/**
	 * 退出
	*     
	* 方法描述：   
	* 创建人：ThinkPad   
	* 创建时间：2015年6月15日 下午3:21:11   
	* 修改人：ThinkPad   
	* 修改时间：2015年6月15日 下午3:21:11   
	* 修改备注：   
	* @version    
	*
	 */
	public void clientLoginOut(){
		logger.info("退出LINUX指令");
		try {
			linux.close();
		} catch (ResourceException e) {
			System.out.println("异常： " + e.getMessage());
		}
	}
	
	/**
	 * 发送指令
	*     
	* 方法描述：   
	* 创建人：madaoyong   
	* 创建时间：2015年6月23日 上午8:47:57   
	* 修改人：madaoyong   
	* 修改时间：2015年6月23日 上午8:47:57   
	* 修改备注：   
	* @version    
	 * @throws ResourceException 
	*
	 */
	public String sendCmd(String cmd) throws ResourceException{
		logger.info("发送LINUX_SCAM指令");
		String result = "";
		result = linux.sendCmdResult(cmd);
		return result;
	}
	
	
	/**
	 * 调用实例
	*     
	* 方法描述：   
	* 创建人：madaoyong   
	* 创建时间：2015年6月10日 下午4:35:38   
	* 修改人：madaoyong   
	* 修改时间：2015年6月10日 下午4:35:38   
	* 修改备注：   
	* @version    
	*
	 */
	public static void main(String[] args) {
		try{
			String[] parameter = new String[]{"172.16.2.24", "root", "Az123456", "Az123456", "#", "#"};
			LinuxTools tool = new LinuxTools(parameter);
			if(tool.clientLogin()){
				String result = "";
				String directoryAddress = "/opt/nodejs/jlsqm/jlsqmviewdemo";
				String logsAddress = "access.log";
				String logsNumber = "1000";
//				result = tool.sendCmd("cd "+directoryAddress);
//				System.out.println("进入服务目录地址回显："+result);
				//赋予操作权限
//				result = tool.sendCmd("chmod 777 app.js");
//				System.out.println("赋予操作权限回显："+result);
				//启动服务脚本
				result = tool.sendCmd("tail -n "+logsNumber+" "+directoryAddress+"/logs/"+logsAddress);
				//result = tool.sendCmd("forever -l "+directoryAddress+"/logs/access.log -e "+directoryAddress+"/logs/error.log -a stop app.js");
				System.out.println("启动服务脚本回显："+result);
				tool.clientLoginOut();
			}
			
		} catch (ResourceException e) {
			e.printStackTrace();
		}
	}

}
