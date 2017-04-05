package com.boco.security.sam.config.jsonview;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.boco.security.core.exception.ExceptionManager;
import com.boco.security.core.init.zk.ZookeeperTools;
import com.boco.security.core.utils.common.PropertiesTools;
import com.boco.security.sam.config.Config_I18N;
import com.boco.security.sam.config.vo.MonitorVo;

/** 
 * Description： 
 * 		统一配置中心ConfigService模块启动入口类。
 * 		1.负责初始化一些zookeeper储存必要路径。
 * 		2.将注册中心注册到zookeeper中心。
 * 		3.zookeeper无法谅解或初始化过程中有问题将导致无法启动。
 * CopyRright(c) 2014 boco.com.cn All rights reserved.
 * Author：  liuyang9
 * Create Date：  2014-11-26
 * Modified By：
 * Modified Date：
 * Why & What is modified：
 * Version：1.0
 */
public class SamConfigJsonViewMain {
	private static final Logger logger = LoggerFactory.getLogger(SamConfigJsonViewMain.class.getName());
	
	private static String SERVER_NAME = "统一配置中心服务";
	private static String SERVER_TYPE = "CONFIG_SERVER";
	public static MonitorVo environmentVo = new MonitorVo(SERVER_TYPE, SERVER_NAME);
	/**
	 * Zookeeper配置文件信息
	 */
	public static Map<String, String> zkConfigMap = null;
	public static ZookeeperTools zookeeperTools;
	
	public static void main(String[] args) {
		logger.info("Connecting zookeeper server ......");
		try {
			String path = SamConfigJsonViewMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			InputStream in = null;
			if (path.endsWith(".jar")) { //部署模式
				in = new FileInputStream("./config/zkConfig.properties");
			} else { //开发模式
				in = Thread.currentThread().getContextClassLoader().getResourceAsStream("zkConfig.properties");
			}
			zkConfigMap = PropertiesTools.readIntoMap(in);
		} catch (Exception e) {
			logger.info("Read config file failure, exit ... ");
			ExceptionManager.process(e);
			System.exit(0);
		}
		//! 启动Spring容器
		List<String> configs = new ArrayList<String>();
		configs.add("classpath:/spring/ConfigJsonViewService.xml");
		configs.add("classpath:/spring/JsonViewDubboService.xml");

		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext(
					configs.toArray(new String[] {}));
		} catch (BeansException e1) {
			e1.printStackTrace();
			return;
		}
		context.start();
		
		//! I18N初始化.
		if (Config_I18N.init()){
			logger.info(Config_I18N.getLanguage());
		} else {
			context.close();
			logger.info("Load language file failed!!!");
			return;
		}
		
		//! zookeeper初始化.
		SamConfigJsonViewMain.zookeeperTools = (ZookeeperTools)context.getBean("zookeeperTools");
		SamConfigJsonViewMain.zookeeperTools.init();
		
		try {
			logger.info(Config_I18N.getLocalLanguage("config_configServiceMain_message_00002"));
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace(); 
			context.close();
		}
	}
}
