package com.bjc.lcp.core.api.executor;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gitthub.wujun728.engine.common.ApiConfig;
import com.gitthub.wujun728.engine.common.ApiDataSource;
import com.gitthub.wujun728.engine.common.ApiProperties;
import com.gitthub.wujun728.engine.common.ApiService;
import com.gitthub.wujun728.engine.common.ApiSql;
import com.gitthub.wujun728.engine.common.DataResult;
import com.gitthub.wujun728.engine.groovy.cache.IApiConfigCache;
import com.gitthub.wujun728.engine.groovy.core.cache.GroovyInfo;
import com.gitthub.wujun728.engine.groovy.core.cache.GroovyInnerCache;
import com.gitthub.wujun728.engine.groovy.mapping.RequestMappingExecutor;
import com.gitthub.wujun728.engine.plugin.CachePlugin;
import com.gitthub.wujun728.engine.plugin.PluginManager;
import com.gitthub.wujun728.engine.plugin.TransformPlugin;
import com.gitthub.wujun728.engine.util.JdbcUtil;
import com.gitthub.wujun728.engine.util.PoolManager;
import com.gitthub.wujun728.mybatis.sql.SqlMeta;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 将存储的API注册为request mapping,并且提供对入参及存储的执行脚本进行解析。 输出解析后的最终脚本提供给脚本执行器`@Link
 * DataSourceDialect`。然后对结果进行封装返回
 */
@SuppressWarnings({ "rawtypes" })
@Slf4j
@Component("HttpMappingExecutor")
public class HttpMappingExecutor extends RequestMappingExecutor
/*implements  ICompentExecutor, ApplicationContextAware, InitializingBean */{

	@Autowired
	private ApiProperties apiProperties;

	@Autowired
	private ApiService apiService;

	@Autowired
	private IApiConfigCache apiInfoCache;

	@Autowired
	private ServerProperties serverProperties;

	private List<String> bodyMethods = Arrays.asList("POST", "PUT", "PATCH");
	
	/**
	 * 默认执行逻辑
	 */
	public void process(HttpServletRequest request,HttpServletResponse response) throws Throwable {
//		this.request = request;
//		this.response = response;
		log.info("HttpMappingExecutor execute  begin ");
//		R r = null ;
		Object data = null;
		String servletPath = request.getRequestURI();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			//  执行SQL逻辑  *****************************************************************************************************
			// 校验接口是否存在
			ApiConfig config = apiInfoCache.get(servletPath);
			if (config == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				out.append(JSON.toJSONString(DataResult.fail("Api not exists")));
			}
			switch (config.getScriptType()) {
			case "SQL":
				data = doSQLProcess(config, request, response);
				break;
			case "Class":
				data = doGroovyProcess(config, request, response);
				break;
			case "Groovy": 
				data = doGroovyProcess(config, request, response);
				break;
			case "Jython": // TODO
				data = doGroovyProcess(config, request, response);
				break; // TODO
			case "JavaScript": // TODO
				data = doGroovyProcess(config, request, response);
			case "Jruby":// TODO
				data = doGroovyProcess(config, request, response);
				break;
			default:
				break;
			}
			out.append(JSON.toJSONString(data));
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.append(JSON.toJSONString(DataResult.fail(e.toString())));
			log.error(e.toString(), e);
		} finally {
			if (out != null)
				out.close();
		}
		log.info("HttpMappingExecutor execute  end ");
	}
	
	
	
	public Object doGroovyProcess(ApiConfig config, HttpServletRequest request, HttpServletResponse response) {
		String beanName = GroovyInnerCache.getByPath(config.getPath());
        //GroovyInfo groovyInfo = GroovyInnerCache.getGroovyInfoByPath(config.getPath());
        Map<String, Object> params = getParameters(request, config);
        params.put("_request", request);
    	IExecutor bean = SpringUtil.getBean(beanName);
		try {
			return bean.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public Object doSQLProcess(ApiConfig config, HttpServletRequest request, HttpServletResponse response) {
		try {
			ApiDataSource datasource = apiService.getDatasource(config.getDatasourceId());
			if (datasource == null || datasource.getId()==null) {
				response.setStatus(500);
				return DataResult.fail("Datasource not exists!");
			}
			Map<String, Object> params = getParameters(request, config);
//			if(MapUtil.getStr(params,"pageNumber")!=null && MapUtil.getStr(params,"pageSize")!=null ){
//				Integer size = Convert.convert(Integer.class, params.get("pageSize"));
//				Integer page = Convert.convert(Integer.class, params.get("pageNumber"));
//				params.put("pageSize", size);
//				params.put("pageNumber", size*(page-1));
//			}
			List<ApiSql> sqlList = config.getSqlList();
			if (CollectionUtils.isEmpty(params) && !CollectionUtils.isEmpty(sqlList) && JSON.toJSONString(sqlList).contains("#")) {
				return DataResult.fail("Request parameter is not exists(请求入参不能为空)!");
			}
			ApiDataSource ds = new ApiDataSource();
			BeanUtil.copyProperties(datasource,ds, false);
			DruidPooledConnection connection = PoolManager.getPooledConnection(ds);
			// 是否开启事务
			boolean flag = config.getOpenTrans() == 1 ? true : false;
			// 执行sql
			List<Object> dataList = executeSql(connection, sqlList, params, flag);
			// 执行数据转换
			for (int i = 0; i < sqlList.size(); i++) {
				ApiSql apiSql = sqlList.get(i);
				Object data = dataList.get(i);
				// 如果此单条sql是查询类sql，并且配置了数据转换插件
				if (data instanceof Iterable && StringUtils.isNotBlank(apiSql.getTransformPlugin())) {
					log.info("transform plugin execute");
					List<JSONObject> sourceData = (List<JSONObject>) (data); // 查询类sql的返回结果才可以这样强制转换，只有查询类sql才可以配置转换插件
					TransformPlugin transformPlugin = (TransformPlugin) PluginManager.getPlugin(apiSql.getTransformPlugin());
					Object resData = transformPlugin.transform(sourceData, apiSql.getTransformPluginParams());
					dataList.set(i, resData);// 重新设置值
				}
			}
			Object res = dataList;
			// 如果只有单条sql,返回结果不是数组格式
			if (dataList.size() == 1) {
				res = dataList.get(0);
			}
			// 设置缓存
			if (StringUtils.isNoneBlank(config.getCachePlugin())) {
				CachePlugin cachePlugin = (CachePlugin) PluginManager.getPlugin(config.getCachePlugin());
				ApiConfig apiConfig = new ApiConfig();
				BeanUtil.copyProperties(config,apiConfig, false);
				cachePlugin.set(apiConfig, params, res);
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	

	public List<Object> executeSql(Connection connection, List<ApiSql> sqlList, Map<String, Object> sqlParam,
			boolean flag) {
		List<Object> dataList = new ArrayList<>();
		SqlMeta _sqlMeta = null;
		try {
			if (flag)
				connection.setAutoCommit(false);
			else
				connection.setAutoCommit(true);
			for (ApiSql apiSql : sqlList) {
				SqlMeta sqlMeta = JdbcUtil.getEngine().parse(apiSql.getSqlText(), sqlParam);
				_sqlMeta = sqlMeta;
				Object data = JdbcUtil.executeSql(connection, sqlMeta.getSql(), sqlMeta.getJdbcParamValues());
				log.info("SQL执行-，当前执行的SQL：{},当前执行的SQL参数：{}",_sqlMeta.getSql(),_sqlMeta.getJdbcParamValues());
				dataList.add(data);
			}
			if (flag)
				connection.commit();
			return dataList;
		} catch (Exception e) {
			try {
				if (flag)
					connection.rollback();
				if(_sqlMeta !=null) {
					log.error("SQL执行失败，当前执行的SQL：{},当前执行的SQL参数：{}",_sqlMeta.getSql(),_sqlMeta.getJdbcParamValues());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			if(_sqlMeta !=null) {
				throw new RuntimeException(e +";\n\t SQL执行失败，当前执行的SQL：["+_sqlMeta.getSql()+"],当前执行的SQL参数:"+_sqlMeta.getJdbcParamValues()+"");
			}else {
				throw new RuntimeException(e);
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


//	   @Override
//	    public void afterPropertiesSet() throws Exception {
////	        GroovyScriptFactory.setGroovyParserEngine(this);
//	    }
//
//	    @Override
//	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//	        this.applicationContext = applicationContext;
//	    }
//
//
//
//		@Override
//		public DataResult rollback(Map parms) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//
//		@Override
//		public String bascName() {
//			return null;
//		}


}
