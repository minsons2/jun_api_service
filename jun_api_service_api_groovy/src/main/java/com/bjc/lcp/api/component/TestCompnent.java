package com.bjc.lcp.api.component;

import cn.hutool.core.lang.Console;
import com.gitthub.wujun728.engine.interfaces.IExecutor;

import java.util.Map;

public class TestCompnent implements IExecutor<Integer, Map<String,Object>> {

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
