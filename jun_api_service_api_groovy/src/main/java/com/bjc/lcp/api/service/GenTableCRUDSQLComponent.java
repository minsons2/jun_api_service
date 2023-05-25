package com.bjc.lcp.api.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.bjc.lcp.core.api.executor.IExecutor;
import com.gitthub.wujun728.engine.common.DataResult;
import com.gitthub.wujun728.engine.generator.GenUtils;
import com.gitthub.wujun728.engine.util.JdbcUtil;
import com.gitthub.wujun728.mybatis.sql.SqlMeta;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.spring.SpringUtil;
import freemarker.template.TemplateException;

/**
 * 组件ID：BAS000000000100 描 述：执行指定的SQL语句（insert/update/delete/select语句）
 * 版本历史：1.0.0版 参数说明： 参 数1：要执行的SQL语句 参 数2：sql参数 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
public class GenTableCRUDSQLComponent implements  IExecutor<DataResult, Map<String,Object>>  {

	public static void initDb(String configName, String url, String username, String password) {
		DruidPlugin dp = new DruidPlugin(url, username, password);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(configName, dp);
		// 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
		dp.start();
		arp.start();
	}

	@Override
	public DataResult execute(Map<String, Object> params) throws Exception {
		// 校验组件参数+校验上下文参数，TODO
//		if (!checkMinParaCountAndSetRespCode(3)) {
//			return ERROR;
//		}
		// 初始化数据库
		String configName = "A000001";
		initDb(configName, SpringUtil.getProperty("spring.datasource.url"),
				SpringUtil.getProperty("spring.datasource.username"),
				SpringUtil.getProperty("spring.datasource.password"));

		DataSource ds = Db.use(configName).getConfig().getDataSource();

		String tableName = MapUtil.getStr(params, "tableName");
		Table tableMeta = MetaUtil.getTableMeta(ds, tableName);

		// 执行SQL语句 eq: delete from test where id = ?
		Object[] paras = MapUtil.getStr(params, "sParas").split(","); // sql语句参数清单 eq: id (多个逗号分隔)
		String sResultPath = MapUtil.getStr(params, "sResult");// 执行SQL语句处理记录数存放标签 eq： 1 (更新的条数)

		try {
			Object[] paramVal = paras;// { "abc", "123" };

			// 测试代码--begin
			// *************************************************************************
			Object[] sqlparams = { 22 };
			List<Record> datas = Db.use(configName)
					.find("select id,title,content,remark,field_name_test from test where id > ?", sqlparams);
			System.out.println(JSON.toJSONString(datas));

			Object[] sqlUparams = { 25 };
			Object[] paras1 = { "id" };
			int ucount = Db.use(configName).update("update test set content = content + '-666' where id = ?",
					sqlUparams);
			System.out.println(JSON.toJSONString(ucount));

			Object[] sqlDparams = { 26 };
			int dcount = Db.use(configName).update("delete from test  where id = ?", sqlDparams);
			System.out.println(JSON.toJSONString(dcount));

//			Object[] sqliParams = Arrays.asList(10,"aaa","bbb","cccc","dddd").toArray() ;
			Object[] sqliParams = new Object[5];
			sqliParams[0] = 10;
			sqliParams[1] = "aaa";
			sqliParams[2] = "bbb";
			sqliParams[3] = "cccc";
			sqliParams[4] = "dddd";
			int icount = Db.use(configName).update(
					"insert into test (id, title,content,remark,field_name_test ) VALUES ( ?,?, ?, ?, ? ) ",
					sqliParams);
			System.out.println(JSON.toJSONString(icount));
			// 测试代码--end
			// *************************************************************************

		} catch (Exception e) {
			int errorCode = ((SQLException) e).getErrorCode();
			// if (errorCode == -803) {
			if (e.getMessage().matches(".*Duplicate.*PRIMARY.*") || errorCode == -803 || errorCode == 1062) {
				e.printStackTrace();
				throw new Exception("数据库主键冲突！");
			} else {
				e.printStackTrace();
				throw new Exception("数据库操作出错！");
			}
		}
		return DataResult.success();
	}

	@Override
	public DataResult rollback(Map<String, Object> parms) {
		return null;
	}

	public static void main(String[] args) throws IOException, TemplateException, SQLException {
		// 初始化数据库
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl(
				"jdbc:mysql://10.100.201.155:3306/lcp-dev?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8");
		ds.setUsername("root");
		ds.setPassword("123456");

		// 准备数据
		HashMap<String, Object> data = new HashMap<>();
		String tableName = "api_config";
		data.putAll(GenUtils.getTableData(tableName, ds.getConnection()));

		String configName = "master";
		initDb(configName,
				"jdbc:mysql://10.100.201.155:3306/lcp-dev?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8",
				"root", "123456");

//		String deleteSQL = deleteSQL(data);
//		String params = deleteSQLParams(data);
//		Map<String, Object> sqlParam = buildSqlParams(data, deleteSQL, params,"delete");
//		executeSQL(ds,sqlParam,insertSQL(data));
//		printSQLParams(data);
		

//		String insertSQL = insertSQL(data);
//		String params = insertSQLParams(data);
//		Map<String, Object> sqlParam = buildSqlParams(data, insertSQL, params,"save");
//		executeSQL(ds,sqlParam,insertSQL(data));
		
		
//		String loadSQL = loadSQL(data);
//		String params = loadSQLParams(data);
//		Map<String, Object> sqlParam = buildSqlParams(data, loadSQL, params,"load");
//		executeSQL(ds,sqlParam,insertSQL(data));
		
		
//		String pageListCountSQL = pageListCountSQL(data);
//		String params = "[]";
//		Map<String, Object> sqlParam = buildSqlParams(data, pageListCountSQL, params,"count");
//		executeSQL(ds,sqlParam,insertSQL(data));
		
		
		
//		String pageListSQL = pageListSQL(data);
//		String params = "[{'name':'pageSize','type':'int'},{'name':'offset','type':'int'}]";
//		Map<String, Object> sqlParam = buildSqlParams(data, pageListSQL, params,"pageList");
//		executeSQL(ds,sqlParam,insertSQL(data));
		
		
		String updateSQL = updateSQL(data);
		String params = updateSQLParams(data);
		Map<String, Object> sqlParam = buildSqlParams(data, updateSQL, params,"update");
		executeSQL(ds,sqlParam,insertSQL(data));
		
	}

	@SuppressWarnings("unused")
	private static Object executeSQL(DataSource ds, Map<String, Object> sqlParam, String deleteSQL)
			throws SQLException {
		SqlMeta sqlMeta = JdbcUtil.getEngine().parse(deleteSQL, sqlParam);
		Object datas = JdbcUtil.executeSql(ds.getConnection(), sqlMeta.getSql(), sqlMeta.getJdbcParamValues());
		System.err.println(JSON.toJSONString(datas));
		return datas;
	}

	private static String deleteSQL(HashMap<String, Object> data) throws IOException, TemplateException, SQLException {
		String deleteSQLTemplate = " DELETE FROM ${classInfo.tableName} \r\n" 
				+ "        WHERE "
				+ "            <#list classInfo.pkfieldList as fieldItem >\r\n"
				+ "                <#if fieldItem.isPrimaryKey != false  > "
				+ "                     ${fieldItem.columnName} = ${r\"#{\"}${fieldItem.fieldName}${r\"}\"} <#if fieldItem_has_next>AND</#if>   "
				+ "                </#if> " 
				+ "                <#if fieldItem.isPrimaryKey == false  > "
				+ "                    <if test=\"null != ${fieldItem.fieldName} and '' != ${fieldItem.fieldName}\">  ${fieldItem.columnName} = ${r\"#{\"}${fieldItem.fieldName}${r\"}\"} <#if fieldItem_has_next>AND</#if>  ${r\"</if>\"} "
				+ "                </#if> " 
				+ "            </#list>\r\n";
		String deleteSQL = GenUtils.genTemplateStr(data, deleteSQLTemplate);
		return deleteSQL;
	}

	private static Map<String, Object> buildSqlParams(HashMap<String, Object> data, String sql,
			String params,String cuudFlag) throws IOException, TemplateException {
		Map<String, Object> sqlParam = new HashMap<String, Object>();
		String className = GenUtils.genTemplateStr(data, "${classInfo.className?uncap_first}");
		String path = "/api/"+GenUtils.genTemplateStr(data, "${classInfo.className?uncap_first}")+"/"+cuudFlag;
		sqlParam.put("refs", "master");
		sqlParam.put("path", path);
		// sqlParam.put("method", "method-value");
		sqlParam.put("params", params);
		sqlParam.put("interfaceId", className + "-"+cuudFlag);
		sqlParam.put("beanName", className + "-"+cuudFlag);
		sqlParam.put("datasourceId", "local");
		sqlParam.put("scriptype", "SQL");
		sqlParam.put("scripcontent", sql);
		sqlParam.put("status", "ENABLE");
		sqlParam.put("groupName", className);
		sqlParam.put("sort", "0");
		sqlParam.put("extendInfo", "extendInfo-value");
		sqlParam.put("openTrans", "1");
		sqlParam.put("creator", "admin");
		sqlParam.put("createdTime", DateUtil.now());
		sqlParam.put("updateTime", DateUtil.now());
		sqlParam.put("lasupdate", "admin");
		return sqlParam;
	}

	private static String deleteSQLParams(HashMap<String, Object> data) throws IOException, TemplateException {
		String paramsSQLTemplate = " [ \r\n"
				+ "<#list classInfo.pkfieldList as fieldItem >" 
				+ " <#if fieldItem.isPrimaryKey != false  >"
				+ "  {'name':'${fieldItem.fieldName}','type':'${fieldItem.columnType}','notNull':${fieldItem.notNull?string('true', 'false')},'maxLength':${fieldItem.columnSize?c}}<#if fieldItem_has_next>,</#if> \r\n" 
				+ "</#if>"
				+ "</#list>"
				+ " ] ";
		String params = GenUtils.genTemplateStr(data,  paramsSQLTemplate);
		return params;
	}

	private static String printSQLParams(HashMap<String, Object> data)
			throws IOException, TemplateException, SQLException {
		String deleteSQLTemplate = " Map<String, Object> sqlParam = new HashMap<String, Object>();\r\n"
				+ "<#list classInfo.pkfieldList as fieldItem >" + "<#if fieldItem.isPrimaryKey != false  >"
				+ "  sqlParam.put(\"${fieldItem.fieldName}\", \"${fieldItem.fieldName}-value\");\r\n" + "</#if>"
				+ "</#list>";
		GenUtils.genTemplateStr(data, "delete", deleteSQLTemplate);
		deleteSQLTemplate = " Map<String, Object> sqlParam = new HashMap<String, Object>();\r\n"
				+ "<#list classInfo.fieldList as fieldItem >" + "<#if fieldItem.isPrimaryKey == false  >"
				+ "  sqlParam.put(\"${fieldItem.fieldName}\", \"${fieldItem.fieldName}-value\");\r\n" + "</#if>"
				+ "</#list>";
		GenUtils.genTemplateStr(data, "delete", deleteSQLTemplate);
		return null;
	}

	private static String pageListCountSQL(HashMap<String, Object> data) throws IOException, TemplateException {
		String pageListCountSQL = " SELECT count(1)\r\n" + "        FROM ${classInfo.tableName} ";
		return GenUtils.genTemplateStr(data, "pageListCountSQL", pageListCountSQL);
	}

	private static String pageListSQL(HashMap<String, Object> data) throws IOException, TemplateException {
		String pageListSQL = " SELECT  <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>\r\n"
				+ "            <#list classInfo.fieldList as fieldItem >\r\n"
				+ "                ${fieldItem.columnName} as ${fieldItem.fieldName} <#if fieldItem_has_next>,</#if>\r\n"
				+ "            </#list>\r\n" + "        </#if>  \r\n" + "        FROM ${classInfo.tableName}\r\n"
				+ "        LIMIT ${r\"#{offset}\"}, ${r\"#{pageSize}\"} ";
		return GenUtils.genTemplateStr(data, "pageListSQL", pageListSQL);
	}

	private static String loadSQL(HashMap<String, Object> data) throws IOException, TemplateException {
		String loadSQL = " SELECT  <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>\r\n"
				+ "            <#list classInfo.fieldList as fieldItem >\r\n"
				+ "                ${fieldItem.columnName} as ${fieldItem.fieldName} <#if fieldItem_has_next>,</#if>\r\n"
				+ "            </#list>\r\n" 
				+ "        </#if>  \r\n" 
				+ "        FROM ${classInfo.tableName}\r\n"
				+ "        WHERE " 
				+ "            <#list classInfo.pkfieldList as fieldItem >\r\n"
				+ "                <#if fieldItem.isPrimaryKey != false  > "
				+ "                     ${fieldItem.columnName} = ${r\"#{\"}${fieldItem.fieldName}${r\"}\"} <#if fieldItem_has_next>AND</#if>   "
				+ "                </#if> " 
				+ "                <#if fieldItem.isPrimaryKey == false  > "
				+ "                    <if test=\"null != ${fieldItem.fieldName} and '' != ${fieldItem.fieldName}\">  ${fieldItem.columnName} = ${r\"#{\"}${fieldItem.fieldName}${r\"}\"} <#if fieldItem_has_next>AND</#if>  ${r\"</if>\"} "
				+ "                </#if> " 
				+ "            </#list>\r\n";
		return GenUtils.genTemplateStr(data, "load", loadSQL);
	}
	private static String loadSQLParams(HashMap<String, Object> data) throws IOException, TemplateException {
		String paramsSQLTemplate = " [ \r\n"
				+ "<#list classInfo.pkfieldList as fieldItem >" 
				+ " <#if fieldItem.isPrimaryKey != false  >"
				+ "  {'name':'${fieldItem.fieldName}','type':'${fieldItem.columnType}','notNull':${fieldItem.notNull?string('true', 'false')},'maxLength':${fieldItem.columnSize?c}}<#if fieldItem_has_next>,</#if> \r\n" 
				+ "</#if>"
				+ "</#list>"
				+ " ] ";
		String params = GenUtils.genTemplateStr(data,  paramsSQLTemplate);
		return params;
	}

	private static String insertSQL(HashMap<String, Object> data) throws IOException, TemplateException {
		String insertSQL = " INSERT INTO ${classInfo.tableName}\r\n"
				+ "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\r\n"
				+ "            <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>\r\n"
				+ "                <#list classInfo.fieldList as fieldItem >\r\n"
				+ "                    <#if fieldItem.columnName != \"id\" >\r\n"
				+ "                        <if test=\"null != ${fieldItem.fieldName} and '' != ${fieldItem.fieldName}\">\r\n"
				+ "                        ${fieldItem.columnName}<#if fieldItem_has_next>,</#if>\r\n"
				+ "                        ${r\"</if>\"}\r\n" 
				+ "                    </#if>\r\n"
				+ "                </#list>\r\n" 
				+ "            </#if>\r\n" 
				+ "        </trim>\r\n"
				+ "        <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\r\n"
				+ "            <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>\r\n"
				+ "                <#list classInfo.fieldList as fieldItem >\r\n"
				+ "                    <#if fieldItem.columnName != \"id\" >\r\n"
				+ "                    <#--<#if fieldItem.columnName=\"addtime\" || fieldItem.columnName=\"updatetime\" >\r\n"
				+ "                    ${r\"<if test ='null != \"}${fieldItem.fieldName}${r\"'>\"}\r\n"
				+ "                        NOW()<#if fieldItem_has_next>,</#if>\r\n"
				+ "                    ${r\"</if>\"}\r\n" 
				+ "                    <#else>-->\r\n"
				+ "                        <if test=\"null != ${fieldItem.fieldName} and '' != ${fieldItem.fieldName}\">\r\n"
				+ "                        ${r\"#{\"}${fieldItem.fieldName}${r\"}\"}<#if fieldItem_has_next>,</#if>\r\n"
				+ "                        ${r\"</if>\"}\r\n" 
				+ "                    <#--</#if>-->\r\n"
				+ "                    </#if>\r\n" 
				+ "                </#list>\r\n" 
				+ "            </#if>\r\n"
				+ "        </trim> ";
		return GenUtils.genTemplateStr(data, "insert", insertSQL);
	}
	private static String insertSQLParams(HashMap<String, Object> data) throws IOException, TemplateException {
		String paramsSQLTemplate = " [ \r\n"
				+ "            <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>\r\n"
				+ "                <#list classInfo.fieldList as fieldItem >\r\n"
				+ "                    <#if fieldItem.columnName != \"id\" >\r\n"
				+ "  {'name':'${fieldItem.fieldName}','type':'${fieldItem.columnType}','notNull':${fieldItem.notNull?string('true', 'false')},'maxLength':${fieldItem.columnSize?c}}<#if fieldItem_has_next>,</#if> \r\n" 
				+ "                    </#if>\r\n"
				+ "                </#list>\r\n" 
				+ "            </#if>\r\n" 
				+ " ] ";
		String params = GenUtils.genTemplateStr(data,  paramsSQLTemplate);
		return params;
	}

	private static String updateSQL(HashMap<String, Object> data) throws IOException, TemplateException {
		String updateSQL = " UPDATE ${classInfo.tableName}\r\n" + "        <set>\r\n"
				+ "            <#list classInfo.fieldList as fieldItem >\r\n"
				+ "                <#if fieldItem.columnName != \"id\" && fieldItem.columnName != \"AddTime\" && fieldItem.columnName != \"UpdateTime\" >\r\n"
				+ "                    <if test=\"null != ${fieldItem.fieldName} and '' != ${fieldItem.fieldName}\">${fieldItem.columnName} = ${r\"#{\"}${fieldItem.fieldName}${r\"}\"}<#if fieldItem_has_next>,</#if>${r\"</if>\"}\r\n"
				+ "                </#if>\r\n" 
				+ "            </#list>\r\n" 
				+ "        </set>\r\n" 
				+ "        WHERE "
				+ "            <#list classInfo.pkfieldList as fieldItem >\r\n"
				+ "                <#if fieldItem.isPrimaryKey != false  > "
				+ "                     ${fieldItem.columnName} = ${r\"#{\"}${fieldItem.fieldName}${r\"}\"} <#if fieldItem_has_next>AND</#if>   "
				+ "                </#if> " 
				+ "                <#if fieldItem.isPrimaryKey == false  > "
				+ "                    <if test=\"null != ${fieldItem.fieldName} and '' != ${fieldItem.fieldName}\">  ${fieldItem.columnName} = ${r\"#{\"}${fieldItem.fieldName}${r\"}\"} <#if fieldItem_has_next>AND</#if>  ${r\"</if>\"} "
				+ "                </#if> " 
				+ "            </#list>\r\n";
		return GenUtils.genTemplateStr(data, "update", updateSQL);
	}
	private static String updateSQLParams(HashMap<String, Object> data) throws IOException, TemplateException {
		String paramsSQLTemplate = " [ \r\n"
				+ "            <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>\r\n"
				+ "                <#list classInfo.fieldList as fieldItem >\r\n"
//				+ "                    <#if fieldItem.columnName != \"id\" >\r\n"
				+ "  {'name':'${fieldItem.fieldName}','type':'${fieldItem.columnType}','notNull':${fieldItem.notNull?string('true', 'false')},'maxLength':${fieldItem.columnSize?c}}<#if fieldItem_has_next>,</#if> \r\n" 
//				+ "                    </#if>\r\n"
				+ "                </#list>\r\n" 
				+ "            </#if>\r\n" 
				+ " ] ";
		String params = GenUtils.genTemplateStr(data,  paramsSQLTemplate);
		return params;
	}

}
