package com.bjc.lcp.api.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.bjc.lcp.core.api.executor.IExecutor;
import com.gitthub.wujun728.engine.common.DataResult;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;

@Component
public class CommonDBCompoent implements IExecutor<DataResult,Map<String,Object>> {

	@Autowired
	JdbcTemplate jt;

	DruidDataSource ds = new DruidDataSource();

	@PostConstruct
	public void test(){
		ds.setUrl(SpringUtil.getProperty("spring.datasource.url"));
		ds.setUsername(SpringUtil.getProperty("spring.datasource.username"));
		ds.setPassword(SpringUtil.getProperty("spring.datasource.password"));
	}

	@Override
	public DataResult execute(Map params) {
		if(jt!=null) {
			try {
				System.out.println("jdbcTemplate注入成功：" + jt.getDataSource().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		QueryRunner queryRunner = new QueryRunner(ds);
//		//2 执行update方法
//		String sql = "insert into product(pid,pname,price,category_id) values(?,?,?,?)";
//		Object[] params = { 990,"测试",100,"c009" };
//		int r = queryRunner.update(sql,params); 
		
		Map<String, Object> map = new HashMap<>();
		try {
			Long pk = null;
			Entity entity = Entity.create("test");
			entity.set("content", "unitTestUser11"+RandomUtil.randomInt());
			entity.set("title", "title-"+RandomUtil.randomInt());
			entity.set("remark", "remark"+RandomUtil.randomInt());
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
	public DataResult rollback(Map params) {
		return null;
	}


}
