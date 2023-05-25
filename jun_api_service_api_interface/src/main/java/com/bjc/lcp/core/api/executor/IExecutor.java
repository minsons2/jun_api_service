package com.bjc.lcp.core.api.executor;

import java.util.Map;

public interface IExecutor<T, P extends Map> {
	
	public static String CONTEXT = "context";
	public static Integer SUCCESS = 0;
	public static Integer ERROR = -1;

	/**
	 * 执行接口
	 * 
	 * @return
	 * @throws Exception 
	 */
	public T execute(P params) throws Exception;

	/**
	 * 回滚接口，业务补偿
	 * 
	 * @return
	 */
	public T rollback(P params);

}
