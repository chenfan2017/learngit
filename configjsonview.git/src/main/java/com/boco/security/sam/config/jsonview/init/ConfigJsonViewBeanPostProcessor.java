package com.boco.security.sam.config.jsonview.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.boco.security.core.init.zk.ZookeeperTools;
import com.boco.security.sam.config.jsonview.SamConfigJsonViewMain;


/**Description:对Spring的bean初始化前后进行处理
 * Copyright (C) 2015 boco.com.cn All Right Reserved.
 * Author：HeLanshu
 * Create Date: 2015-5-9
 * Modified By：
 * Modified Date：
 * Why & What is modified：
 * Version 1.0
 */
public class ConfigJsonViewBeanPostProcessor implements BeanPostProcessor {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof com.boco.security.core.init.zk.ZookeeperTools) {
			com.boco.security.core.init.zk.ZookeeperTools zookeeperTools = (ZookeeperTools) bean;
			String adrress = SamConfigJsonViewMain.zkConfigMap.get("dubbo_registry_adrress");
			zookeeperTools.setZkConnectionString(adrress);
			logger.info("dubbo_registry_adrress:"+adrress);
		} else if (bean instanceof com.alibaba.dubbo.config.RegistryConfig) {
			com.alibaba.dubbo.config.RegistryConfig registryConfig = (RegistryConfig) bean;
			String adrress = SamConfigJsonViewMain.zkConfigMap.get("dubbo_registry_adrress");
			registryConfig.setAddress(adrress);
			logger.info("dubbo_registry_adrress:"+adrress);
		} else if (bean instanceof com.alibaba.dubbo.config.ProtocolConfig) {
			com.alibaba.dubbo.config.ProtocolConfig protocolConfig = (ProtocolConfig) bean;
			String post = SamConfigJsonViewMain.zkConfigMap.get("dubbo_port");
			protocolConfig.setPort(Integer.parseInt(post));
			logger.info("dubbo_registry_adrress:"+post);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
	

}
