package com.bjc.lcp.core.api.compoent;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.bjc.lcp.core.api.executor.AbstractExecutor;
import com.bjc.lcp.core.api.executor.IExecutor;
import com.gitthub.wujun728.engine.common.DataResult;
import com.jfinal.plugin.activerecord.Db;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class CompoentExecutorSave extends AbstractExecutor implements  IExecutor<DataResult, Map<String,Object>>  {
	String appNo = "A000101";

	@PostConstruct
	public void test(){
		Console.log("url = " + SpringUtil.getProperty("spring.datasource.url"));
		initDb(appNo, SpringUtil.getProperty("spring.datasource.url"),
				SpringUtil.getProperty("spring.datasource.username"),
				SpringUtil.getProperty("spring.datasource.password"));
	}

	@Override
	public DataResult execute(Map params) {
		System.out.println("入参：" + JSONUtil.toJsonStr(params));
		Map<String, Object> map = new HashMap<>();
		try {
			Object[] sqliParams = Arrays.asList("aaa","bbb","cccc","dddd").toArray() ;
			int icount = Db.use(appNo).update("insert into test (title,content,remark,field_name_test ) VALUES ( ?, ?, ?, ? ) ", sqliParams);
			System.out.println(JSON.toJSONString(icount));
			map.put("record", sqliParams);
			map.put("count", icount);
			System.out.println("返回数据为：" + JSONUtil.toJsonStr(map));
		} catch (Exception e) {
			e.printStackTrace();
			return DataResult.fail(e.getMessage());
		}
		return DataResult.success(map);
	}

	@Override
	public DataResult rollback(Map params) {
		return null;
	}


}
