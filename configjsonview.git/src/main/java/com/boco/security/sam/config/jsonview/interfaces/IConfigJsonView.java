package com.boco.security.sam.config.jsonview.interfaces;


/** 
 * Description： 
 * 		统一配置管理模块管理接口.
 * CopyRright(c) 2014 boco.com.cn All rights reserved.
 * Author：  liuyang9
 * Create Date：  2014-11-26
 * Modified By：
 * Modified Date：
 * Why & What is modified：
 * Version：1.0
 */
public interface IConfigJsonView {
	
	/** 
	 * Description: 
	 * 		使用配置信息VO全类名，获取其对应的Vo对象。
	 * @param：
	 * 		classFullName 包含包名+类名的全称。
	 * @return：
	 * 		返回指定的Vo组成的Object对象，客户端类需使用类型自行强转。
	 * @Author：liuyang9 
	 * @Create Date：2014-11-26 
	 */
	public String queryConfigInfo(Object obj);
	
	/**
	 * Description: 
	 * 		保存指定的配置信息Vo，此方法相当于增加、修改、删除方法。
	 * 			--相当于增加方法，即直接保存指定配置信息Vo；
	 * 			--相当于修改方法，即直接保存指定配置信息Vo，覆盖原有信息；
	 * 			--相当于删除方法，即需要在Vo中指定一个属性是否生效进行控制。
	 * 		保存的数据会覆盖原有数据，
	 * 		基于安全性考虑，密码字段如果不为空才覆盖。
	 * @param：
	 * 		configVo 需要保存的配置信息Vo
	 * @return：
	 * 		操作是否成功.
	 * @Author：liuyang9 
	 * @Create Date：2014-11-26 
	 */
	public String saveConfigInfo(Object obj);
	
	/** 
	 * Description: 
	 * 		使用配置信息VO全类名，获取其对应的Vo对象。
	 * @param：
	 * 		classFullName 包含包名+类名的全称。
	 * @return：
	 * 		返回指定的Vo组成的Object对象，客户端类需使用类型自行强转。
	 * @Author：liuyang9 
	 * @Create Date：2014-11-26 
	 */
	public String queryConfigInfoName(Object obj);
	
	/**
	 * Description: 
	 * 		保存指定的配置信息Vo，此方法相当于增加、修改、删除方法。
	 * 			--相当于增加方法，即直接保存指定配置信息Vo；
	 * 			--相当于修改方法，即直接保存指定配置信息Vo，覆盖原有信息；
	 * 			--相当于删除方法，即需要在Vo中指定一个属性是否生效进行控制。
	 * 		保存的数据会覆盖原有数据，
	 * 		基于安全性考虑，密码字段如果不为空才覆盖。
	 * @param：
	 * 		configVo 需要保存的配置信息Vo
	 * @return：
	 * 		操作是否成功.
	 * @Author：liuyang9 
	 * @Create Date：2014-11-26 
	 */
	public String saveConfigInfoName(Object obj);
	
	/**
	 * 获取字典数据，根据code获取list
	*     
	* 方法描述：   
	* 创建人：madaoyong   
	* 创建时间：2015年5月19日 下午12:34:23   
	* 修改人：madaoyong   
	* 修改时间：2015年5月19日 下午12:34:23   
	* 修改备注：   
	* @version    
	*
	 */
	public String queryConstantsInfo(String coding);
	
	/**
	 * 获取平台数据库目录信息
	 * @Title: queryPlatformDirectory 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String queryPlatformDirectory(Object obj);
	
	/**
	 * 获取服务目录信息
	 * @Title: queryPlatformDirectory 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String queryServiceDirectory(Object obj);
}