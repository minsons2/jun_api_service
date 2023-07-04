package com.bjc.lcp.api.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjc.lcp.api.controller.sql.ISQLBuilder;
import com.bjc.lcp.api.dbutil.DbUtil;
import com.gitthub.wujun728.engine.common.DataResult;
import com.gitthub.wujun728.engine.common.RequestWrapper;
import com.gitthub.wujun728.engine.generator.GenUtils;
import com.gitthub.wujun728.engine.groovy.mapping.RequestMappingExecutor;
import com.gitthub.wujun728.engine.util.JdbcUtil;
import com.gitthub.wujun728.mybatis.sql.SqlMeta;
import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
//import com.jun.plugin.system.entity.SysUser;
//import com.jun.plugin.system.service.SysDictService;
//import com.jun.plugin.system.service.UserService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

@Slf4j
@Controller
public class TestController111 {

//	@Resource
//	SysDictService sysDictService;
//
//	@Resource
//	UserService userService;

	@GetMapping("/api/genTableSQL/{tableName}")
	@ResponseBody
	public DataResult genTableCRUDSQL(HttpServletRequest request,@PathVariable String tableName) throws IOException, TemplateException, SQLException {
		System.out.println("Request URI: " + request.getRequestURI());

		HttpServletRequest requestWrapper = new RequestWrapper((HttpServletRequest) request);
		Map param = RequestMappingExecutor.getParameters(requestWrapper);

		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl( "jdbc:mysql://localhost:3306/db_qixing?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8");
		ds.setUsername("root");
		ds.setPassword("");

		// 准备数据
		HashMap<String, Object> data = new HashMap<>();
//		String tableName = "test333";
		data.putAll(GenUtils.getTableData(tableName, ds.getConnection()));

		DbUtil.initDb( DbUtil.MASTER,
				"jdbc:mysql://localhost:3306/db_qixing?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8",
				"root", "");

		Map<String, Object> sqlParamDelete = DbUtil.buildSqlParams(data, DbUtil.deleteSQL(data), DbUtil.deleteSQLParams(data),"delete");
//		DbUtil.executeSQL(ds,sqlParamDelete,DbUtil.insertSQL(data));
		Db.use(DbUtil.MASTER).delete(" delete from api_config where path = ? ",new Object[]{sqlParamDelete.get("path")});
		Db.use(DbUtil.MASTER).save("api_config",DbUtil.getPkNames(ds,"api_config"),DbUtil.getTableRecord(ds,"api_config",sqlParamDelete));
		//DbUtil.printSQLParams(data);


		Map<String, Object> sqlParamSave = DbUtil.buildSqlParams(data, DbUtil.insertSQL(data), DbUtil.insertSQLParams(data),"save");
		Db.use(DbUtil.MASTER).delete(" delete from api_config where path = ? ",new Object[]{sqlParamSave.get("path")});
		Db.use(DbUtil.MASTER).save("api_config",DbUtil.getPkNames(ds,"api_config"),DbUtil.getTableRecord(ds,"api_config",sqlParamSave));
//		DbUtil.executeSQL(ds,sqlParamSave,DbUtil.insertSQL(data));

		Map<String, Object> sqlParamLoad = DbUtil.buildSqlParams(data, DbUtil.loadSQL(data), DbUtil.loadSQLParams(data),"load");
		Db.use(DbUtil.MASTER).delete(" delete from api_config where path = ? ",new Object[]{sqlParamLoad.get("path")});
		Db.use(DbUtil.MASTER).save("api_config",DbUtil.getPkNames(ds,"api_config"),DbUtil.getTableRecord(ds,"api_config",sqlParamLoad));
//		DbUtil.executeSQL(ds,sqlParamLoad,DbUtil.insertSQL(data));

		String paramsCount = "[]";
		Map<String, Object> sqlParamCount = DbUtil.buildSqlParams(data, DbUtil.pageListCountSQL(data), paramsCount,"count");
		Db.use(DbUtil.MASTER).delete(" delete from api_config where path = ? ",new Object[]{sqlParamCount.get("path")});
		Db.use(DbUtil.MASTER).save("api_config",DbUtil.getPkNames(ds,"api_config"),DbUtil.getTableRecord(ds,"api_config",sqlParamCount));
//		DbUtil.executeSQL(ds,sqlParamCount,DbUtil.insertSQL(data));


		String pageListParams = "[{'name':'pageNumber','type':'int'},{'name':'pageSize','type':'int'}]";
		Map<String, Object> sqlParamPage = DbUtil.buildSqlParams(data, DbUtil.pageListSQL(data), pageListParams,"page");
		Db.use(DbUtil.MASTER).delete(" delete from api_config where path = ? ",new Object[]{sqlParamPage.get("path")});
		Db.use(DbUtil.MASTER).save("api_config",DbUtil.getPkNames(ds,"api_config"),DbUtil.getTableRecord(ds,"api_config",sqlParamPage));
//		DbUtil.executeSQL(ds,sqlParamPage,DbUtil.insertSQL(data));


		Map<String, Object> sqlParamUpdate = DbUtil.buildSqlParams(data, DbUtil.updateSQL(data), DbUtil.updateSQLParams(data),"update");
		Db.use(DbUtil.MASTER).delete(" delete from api_config where path = ? ",new Object[]{sqlParamUpdate.get("path")});
		Db.use(DbUtil.MASTER).save("api_config",DbUtil.getPkNames(ds,"api_config"),DbUtil.getTableRecord(ds,"api_config",sqlParamUpdate));
//		DbUtil.executeSQL(ds,sqlParamUpdate,DbUtil.insertSQL(data));

		String className = GenUtils.genTemplateStr(data, "${classInfo.className?uncap_first}");
		List apis = Db.use(DbUtil.MASTER).query(" select id,path from api_config where path like ? ",new Object[]{"%"+className+"%"});
		return new DataResult(200,"脚本接口初始化成功！",apis);
	}
	@GetMapping("/api/test111/test111")
	@ResponseBody
	public String test111333(HttpServletRequest request){
		System.out.println("Request URI: " + request.getRequestURI());
		return  "111";
	}
	@GetMapping("/abc/test111")
	@ResponseBody
	public String test111333abc(HttpServletRequest request){
		System.out.println("Request URI: " + request.getRequestURI());
		return  "111abc";
	}
	@GetMapping("/api/test111/test222")
	public String test222333(HttpServletRequest request){
		System.out.println("Request URI: " + request.getRequestURI());
		return  "thymeleaf";
	}



	}
