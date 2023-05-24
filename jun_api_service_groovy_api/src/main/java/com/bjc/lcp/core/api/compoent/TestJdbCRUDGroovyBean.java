package com.bjc.lcp.core.api.compoent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.alibaba.druid.pool.DruidDataSource;
import com.bjc.lcp.core.api.executor.IExecutor;
import com.gitthub.wujun728.engine.common.DataResult;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;

/**
 * 需要把该代码放进DB，api_groovy，测试ActiveRecord-CURD-CURD-保存在庫裡面
 */
public class TestJdbCRUDGroovyBean  implements  IExecutor<DataResult, Map<String,Object>>  {
	
	DruidDataSource ds = new DruidDataSource();
			
	@PostConstruct
	public void test(){
		//DruidDataSource ds = new DruidDataSource();
		ds.setUrl(SpringUtil.getProperty("spring.datasource.url"));
		Console.log("url = " + SpringUtil.getProperty("spring.datasource.url"));
		ds.setUsername(SpringUtil.getProperty("spring.datasource.username"));
		ds.setPassword(SpringUtil.getProperty("spring.datasource.password"));
	}
 

	@Override
	public DataResult execute(Map<String, Object> parms) throws Exception {
		Map<String, Object> params = parms;
		Map<String, Object> map = new HashMap<>();
		try {
			Long pk = null;
			Entity entity = Entity.create("test")
				    .set("content", "unitTestUser11"+RandomUtil.randomInt())
				    .set("title", "title-"+RandomUtil.randomInt())
				    .set("remark", "remark"+RandomUtil.randomInt());
			try {
				pk = Db.use(ds).insertForGeneratedKey(
					    entity
					);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			entity.set("pk", pk);

			map.put("record", entity);
			System.out.println("返回数据为：" + JSONUtil.toJsonStr(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DataResult.success(map);
	}

	@Override
	public DataResult rollback(Map<String, Object> parms) {
		// TODO Auto-generated method stub
		return null;
	}
}
