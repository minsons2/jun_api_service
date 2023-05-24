package com.bjc.lcp.core.api.compoent;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.bjc.lcp.core.api.executor.IExecutor;
import com.gitthub.wujun728.engine.common.DataResult;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;

/**
 * 需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
public class TestJfinalCRUDGroovyBean  implements  IExecutor<DataResult, Map<String,Object>>  {

	@PostConstruct
	public void test(){
		DruidPlugin dp = new DruidPlugin(SpringUtil.getProperty("spring.datasource.url"),
				SpringUtil.getProperty("spring.datasource.username"),
				SpringUtil.getProperty("spring.datasource.password"));
		ActiveRecordPlugin arp = new ActiveRecordPlugin("test1",dp);
		// arp.addMapping("blog", Blog.class);
		// 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
		dp.start();
		arp.start();
	}



	@Override
	public DataResult execute(Map<String, Object> parms) throws Exception {
		Record data = new Record().set("title", "1111").set("content", "content").set("remark", "remark");
		Boolean resutl = Db.use("test1").save("test", data);
		Map<String, Object> params = parms;
		Map<String, Object> map = new HashMap<>();
		map.put("reward", "6666");
		map.put("resutl", resutl);
		System.out.println("返回数据为：" + JSONUtil.toJsonStr(map));
		return DataResult.success(map);
	}

	@Override
	public DataResult rollback(Map<String, Object> parms) {
		return null;
	}
}

