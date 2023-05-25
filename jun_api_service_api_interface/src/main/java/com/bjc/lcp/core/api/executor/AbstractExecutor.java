package com.bjc.lcp.core.api.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.plugin.activerecord.Db;
import org.apache.commons.lang3.ArrayUtils;

import com.alibaba.fastjson.JSON;
import com.bjc.lcp.engine.common.Context;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.stream.StreamUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractExecutor {
	protected static final int SUCCESS = 0;
	protected static final int RESPONSE_ERROR = 1;
	protected static final int RESPONSE_NULL = 100;
	protected static final int ERROR = -1;
	protected boolean debug = false;
	protected String ip;
	protected int port;
	protected int headLen;
	protected String encode;
	protected String headtag;
	protected HttpServletRequest request;
	protected int maxTrnCnt = 1000; // 服务端流控默认值
	
	
	Context context;
	
//	public AbstractExecutor(Context context) {
//		this.context = context;
//	}

	protected static ThreadLocal<Context> localContext = new ThreadLocal<Context>();

//    protected static ThreadLocal<Integer> localRetCode = new ThreadLocal<Integer>();

//    protected static ThreadLocal<Map<String,Object>> localParams = new ThreadLocal<Map<String,Object>>();
//
//    protected static ThreadLocal<Object> localResult = new ThreadLocal<Object>();
    
	
	
    
	public void initDb(String appNo, String url, String username, String password) {
		Boolean isExtsis = true;
		try {
			Db.use(appNo);
		} catch (IllegalArgumentException e) {
			isExtsis = false;
			log.warn(e.getMessage());
		}
		if( !isExtsis ){
			DruidPlugin dp = new DruidPlugin(url, username, password);
			ActiveRecordPlugin arp = new ActiveRecordPlugin(appNo, dp);
			arp.setDevMode(true);
			arp.setShowSql(true);
			dp.start();
			arp.start();
			log.warn("Config have bean created by configName: {}",appNo);
		}
	}

	// protected FlowType flowType;

	public Map<String,Object> collectParams(Map<String,Object> params) {
		//序列化时过滤掉request和response
		Map<String, Object> collect = MapUtil.filter(params, map -> (!(map.getValue() instanceof HttpServletRequest) && !(map.getValue() instanceof HttpServletResponse)));
		return collect;
	}
	 

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * 参数列表
	 * ***/
	//protected List<CompArgConfig> config;

	/**
	 * 数据总线
	 * **/
	//protected IContext context;

	/**
	 * 组件运行时对象
	 * */
	//protected ComponentRuntime runtime;

	protected long createTime; // 创建日期
	
	protected Map<String,Object> parameters; // 创建日期
	
	

//	@SuppressWarnings("unchecked")
//	public AbstractExecutor(IContext context, ComponentRuntime runtime) {
//		this.context = context;
//		this.runtime = runtime;
//		// this.flowType=flowType;
//		this.config = (List<CompArgConfig>) runtime.getConfig();
//		Collections.sort(this.config);
//	}
//
//	public IContext getContext() {
//		return context;
//	}
//
//	public ComponentRuntime getRuntime() {
//		return runtime;
//	}
//
//	public List<CompArgConfig> getConfig() {
//		return this.config;
//	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	// 获取组件参数，经过El解析引擎得到最终的参数值
	public String getPara(String paramName) {
		Object val = MapUtil.getStr(parameters, paramName);
		return (val == null) ? "" : val.toString();
	}

	protected Object getParaObject(String paramName) {
		Object val = MapUtil.get(parameters, paramName, Object.class);
		return (val == null) ? "" : val;
	}

	/** 获取组件参数，经过El解析引擎得到最终的参数值(防止参数值出现“，”分隔符) kht **/
	protected Object[] getParas(String paramName) {
		Object[] results = null;
		Object obj = getParaObject(paramName);
		if (obj == null || "".equals(obj.toString()) || obj.toString().trim().length() == 0) {
			return new Object[0];
		} else {
			String para = obj.toString().trim();
			String reg = "\\w*\\$\\{([^\\}^\\{]*[,]+[^\\}^\\{]*)\\}\\w*";
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(para);
			List<String> list = new ArrayList<String>();
			while (m.find()) {
				list.add(m.group(0));
			}
			int num = 0;
			String repArg = para.replaceAll(reg, "&_#");
			String[] paras = repArg.split(",", -1);
			results = new Object[paras.length];
			for (int i = 0; i < paras.length; i++) {
				if ("&_#".equals(paras[i])) {
					paras[i] = list.get(num++);
				}
				Object result = getParaObject(paramName);
				results[i] = result == null ? "" : result;
			}
		}
		return results;
	}

	/**
	 * 组件执行成功公共处理
	 * 
	 */
	protected void compSuccReturn() {
		String sData = "组件" + this.getClass().getName() + "执行成功";
	}

	/**
	 * 组件执行成功公共处理
	 * 
	 */
	protected void compSuccReturnWithMsg(String describe) {
		String sData = "组件" + this.getClass().getName() + "执行成功,具体信息：" + describe;
	}

	/**
	 * 组件失败公共处理
	 * 
	 * @param retCode
	 * @param retMsg
	 */
	protected void compErrorReturn(String retCode, String retMsg) {
	}

	/**
	 * 组件失败公共处理，打印异常堆栈
	 * 
	 * @param retCode
	 * @param retMsg
	 */
	protected void compErrorReturn(String retCode, String retMsg, Exception e) {
		String sData =  "组件返回码:[" + retCode + "]" + "组件返回信息:" + retMsg + e.getMessage() + e.getMessage();
	}

	/**
	 * 组件失败公共处理
	 * 
	 * @param retCode
	 * @param retMsg
	 */
	protected void compErrorReturn(String retCode, String retMsg, Object... params) {
		try {
			String sData =   "组件错误码[" + retCode + "]" + "组件返回信息:" + retMsg + params;
		} catch (Exception e) {
			this.writeRuntimeLog(e.getMessage());
		}
	}

	/**
	 * 校验最少参数个数,不满足条件，就设置组件、交易返回码为 CBP2011，“组件参数个数错 ”
	 * 
	 * @param count
	 * @return
	 */
	protected boolean checkMinParaCountAndSetRespCode(int count, boolean print) {
		int num = parameters.size();
		if (num < count) {
			// 判断是否更新交易级返回码
			String sData =   "组件参数个数错" ;
			return false;
		} else {
			if (print) {
				printCompnentArgs();
			}
			return true;
		}

	}

	protected boolean checkMinParaCountAndSetRespCode(int count) {
		return checkMinParaCountAndSetRespCode(count, true);

	}

	/**
	 * 打印组件参数
	 */
	protected void printCompnentArgs() {
		parameters.forEach((k,v)->{
			log.debug( "组件第" + k + "个参数[" + v + "]");
			
		});
	}

 

	/**
	 * 校验参数个数
	 * 
	 * @param count
	 * @return
	 */
	protected boolean checkParaCount(int count) {
		if (parameters.size() != count) {
			return false;
		} else {
			printCompnentArgs();
			return true;
		}
	}

	/**
	 * 记录日志方法，组件至少要进行输入、输出的记录，以便调试排错
	 */
	public void writeRuntimeLog(String message, Object... params) {
	}

	/**
	 * 记录组件需要打印到日志里的信息，以便进行调试排错
	 */
	public void printCompnentLog(String message) {

	}

}
