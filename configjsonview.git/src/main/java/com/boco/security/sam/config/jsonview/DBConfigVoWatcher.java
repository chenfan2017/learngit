package com.boco.security.sam.config.jsonview;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;

import com.boco.security.sam.config.interfaces.IConfigManage;
import com.boco.security.sam.config.vo.ConfigVo;
import com.boco.security.sam.config.vo.DBConfigVo;

public class DBConfigVoWatcher implements CuratorWatcher{
	private IConfigManage configService;
	
	public void process(WatchedEvent event) throws Exception {
		//! 接收到监听事件立即注册一个先的监听.
		SamConfigJsonViewMain.zookeeperTools.getData(
				ConfigVo.CONFIG_ROOT_PATH+"/"+DBConfigVo.class.getName(), 
				this);
		if(event.getType() == EventType.NodeDataChanged){
			//节点该变读取最新信息.
			DBConfigVo dbConfig = configService.queryConfigInfo(DBConfigVo.class);
			System.out.println("发生变更-获取数据库配置信息对象：DBURL="+dbConfig.getUrl());
		}
	}

	public void setConfigService(IConfigManage configService) {
		this.configService = configService;
	}
	public IConfigManage getConfigService() {
		return configService;
	}
}
