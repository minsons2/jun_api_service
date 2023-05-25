package com.bjc.lcp.api.service;

import java.util.Map;

import com.bjc.lcp.core.api.executor.IExecutor;
import com.gitthub.wujun728.engine.common.DataResult;

import cn.hutool.core.lang.Console;

public class TestCompnent implements  IExecutor<Integer, Map<String,Object>>  {

	@Override
	public Integer execute(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Console.print(" run TestCompnent execute method 111   ");
		return 0;
	}

	@Override
	public Integer rollback(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Console.print(" run TestCompnent rollback method 222   ");
		return 0;
	}

	 

}
