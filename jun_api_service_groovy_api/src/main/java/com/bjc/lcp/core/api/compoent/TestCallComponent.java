package com.bjc.lcp.core.api.compoent;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import com.bjc.lcp.core.api.executor.IExecutor;
//import com.github.freakchick.orange.context.Context;
import com.gitthub.wujun728.engine.common.DataResult;

import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;

/**
 * 组件TestCallComponent
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句） 
 * 版本历史：1.0.0版 参数说明： 
 * 参 数1：要执行的SQL语句 
 * 参 数2：sql参数 
 * 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
@Slf4j
public class TestCallComponent implements  IExecutor<DataResult, Map<String,Object>>  {
	
	@Resource
	ServerProperties config;

	 
	@Override
	public DataResult execute(Map<String, Object> params) throws Exception{
		//校验组件参数+校验上下文参数，TODO
		// TODO Auto-generated method stub
		Console.print(" run TestCallComponent execute method have bean call 111   ");
		if(config!=null) {
			Console.print("ServerProperties is not null execute method have bean call 111   ");
		}
		return DataResult.success();
	}

	@Override
	public DataResult rollback(Map<String, Object> parms) {
		return null;
	}

}
