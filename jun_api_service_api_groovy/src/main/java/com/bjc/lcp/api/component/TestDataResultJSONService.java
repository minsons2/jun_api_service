package com.bjc.lcp.api.component;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjc.lcp.core.api.executor.AbstractExecutor;
import com.bjc.lcp.core.api.executor.IExecutor;
import com.bjc.lcp.engine.common.Context;
//import com.github.freakchick.orange.context.Context;
import com.gitthub.wujun728.engine.common.DataResult;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * 组件ID：BAS000000000100
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句） 
 * 版本历史：1.0.0版 参数说明： 
 * 参 数1：要执行的SQL语句 
 * 参 数2：sql参数 
 * 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
public class TestDataResultJSONService extends AbstractExecutor implements IExecutor<DataResult, Map<String,Object>> {

	public void initDb(String appNo, String url, String username, String password) {
		DruidPlugin dp = new DruidPlugin(url, username, password);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(appNo, dp);
		// 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
		dp.start();
		arp.start();
	}

	@Override
	public DataResult execute(Map<String, Object> params) throws Exception{
		
		String servletPath = (String) params.get("path");
		System.out.println(JSON.toJSONString(params));
		JSONObject json= JSON.parseObject(" {\r\n"
				+ "		\"userinfo\":{\r\n"
				+ "		\"id\":1,\r\n"
				+ "		\"username\":\"admin\",\r\n"
				+ "		\"nickname\":\"admin\",\r\n"
				+ "		\"mobile\":\"13888888888\",\r\n"
				+ "		\"avatar\":\"\",\r\n"
				+ "		\"score\":0,\r\n"
				+ "		\"token\":\"c8edcb1d-8c5c-4e5d-9e53-71d7024f9030\",\r\n"
				+ "		\"user_id\":1,\r\n"
				+ "		\"createtime\":1593422850,\r\n"
				+ "		\"expiretime\":1596014850,\r\n"
				+ "		\"expires_in\":2592000}} ");
		return DataResult.success(json);
	}

	@Override
	public DataResult rollback(Map<String, Object> parms) {
		return DataResult.success("");
	}
	
	

}
