package com.boco.security.sam.config.jsonview.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.security.core.jsonview.JsonViewSupport;
import com.boco.security.core.utils.json.tools.JsonTools;
import com.boco.security.sam.config.interfaces.IConfigManage;
import com.boco.security.sam.config.jsonview.interfaces.IConfigJsonView;

/** 
 * Description： 
 * 		统一配置管理模块管理类，
 * 		实现了 com.boco.security.sam.config.interfaces.IConfigManage接口.
 * CopyRright(c): 2014 boco.com.cn All rights reserved.
 * Author：  liuyang9
 * Create Date：  2014-12-02
 * Modified By：
 * Modified Date：
 * Why & What is modified：
 * Version：1.0
 */
public class ConfigJsonView extends JsonViewSupport implements IConfigJsonView {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigJsonView.class.getName());
	
	private IConfigManage configService;

	public IConfigManage getConfigService() {
		return configService;
	}

	public void setConfigService(IConfigManage configService) {
		this.configService = configService;
	}
	
	/**
	 * 获取当前系统在线组件的对象列表
	 */
	@SuppressWarnings("unchecked")
	public String queryConfigInfo(Object obj) {
		logger.info("获取当前系统在线组件的对象列表");
		String resultJson = "";
		Map<String, Object> voMap = (Map<String, Object>) obj;
		String className = String.valueOf(voMap.get("className"));
		try {
			resultJson = JsonTools.obj2json(configService.queryConfigInfo(Class.forName(className)));
		} catch (Exception e) {
			logger.error("class 不存在，请检查："+className);
		}
		return resultJson;
	}

	/**
	 * 保存当前系统在线组件的对象列表
	 */
	@SuppressWarnings("unchecked")
	public String saveConfigInfo(Object obj) {
		String message = "保存失败！";
		Map<String, Object> voMap = (Map<String, Object>) obj;
		boolean result = configService.saveConfigInfo(voMap);
		if(result){
			message = "保存成功！";
		}
		return this.makeSuccessJson(message, result);
	}
	
	/**
	 * 获取当前系统在线组件的对象列表
	 */
	@SuppressWarnings("unchecked")
	public String queryConfigInfoName(Object obj) {
		logger.info("获取当前系统在线组件的对象列表");
		String resultJson = "";
		Map<String, Object> voMap = (Map<String, Object>) obj;
		String className = String.valueOf(voMap.get("className"));
		String dubboName = String.valueOf(voMap.get("dubboName"));
		try {
			resultJson = JsonTools.obj2json(configService.queryConfigInfoName(Class.forName(className), dubboName));
		} catch (Exception e) {
			logger.error("class 不存在，请检查："+className);
		}
		return resultJson;
	}

	/**
	 * 保存当前系统在线组件的对象列表
	 */
	@SuppressWarnings("unchecked")
	public String saveConfigInfoName(Object obj) {
		String message = "保存失败！";
		Map<String, Object> voMap = (Map<String, Object>) obj;
		String dubboName = String.valueOf(voMap.get("dubboName"));
		boolean result = configService.saveConfigInfoName(voMap, dubboName);
		if(result){
			message = "保存成功！";
		}
		return this.makeSuccessJson(message, result);
	}

	/**
	 * 获取字典数据，根据code获取list
	 */
	public String queryConstantsInfo(String coding) {
		List<Map<String, Object>> list = configService.queryConstantsInfo(coding);
		return JsonTools.list2jsonarray(list);
	}
	
	/**
	 * 获取平台数据库目录信息
	 */
	public String queryPlatformDirectory(Object obj) {
		List<Map<String, String>> list = configService.queryPlatformDirectory();
		return JsonTools.list2jsonarray(list);
	}
	
	/**
	 * 获取服务目录信息
	 */
	public String queryServiceDirectory(Object obj) {
		List<Map<String, String>> list = configService.queryServiceDirectory();
		return JsonTools.list2jsonarray(list);
	}

	
}
