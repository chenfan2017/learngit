package com.boco.security.sam.config.jsonview;

import org.apache.curator.framework.CuratorFramework;

import com.boco.security.core.init.zk.IZKListener;
import com.boco.security.sam.config.vo.ConfigVo;
import com.boco.security.sam.config.vo.DBConfigVo;

/** 
 * Description： 
 * 		Zk客户端(即CuratorFramework)在session过期的情况下，
 * 		实现自动重新在zookeeper上注册服务的监听类。
 * CopyRright(c) 2014 boco.com.cn All rights reserved.
 * Author：  liuyang9
 * Create Date：  2014-12-01
 * Modified By：
 * Modified Date：
 * Why & What is modified：
 * Version：1.0
 */
public class JsonViewConfigListener implements IZKListener {
	private DBConfigVoWatcher watcher;
	/**
	 * 这里启动时可以使用一个监听，完成多个ConfigVo的子类对象的Watcher。
	 */
	public void executor(CuratorFramework client) {
//		try {
//			SamConfigJsonViewMain.zookeeperTools.getData(ConfigVo.CONFIG_ROOT_PATH+"/"+DBConfigVo.class.getName(), watcher);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	public void setWatcher(DBConfigVoWatcher watcher) {
		this.watcher = watcher;
	}
}
