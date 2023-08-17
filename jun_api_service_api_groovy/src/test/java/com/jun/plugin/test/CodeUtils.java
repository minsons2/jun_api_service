package com.jun.plugin.test;

import com.jun.plugin.generator.GenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;

/**
 * 代码生成器，根据DatabaseMetaData及数据表名称生成对应的Model、Mapper、Service、Controller简化基础代码逻辑开发。
 * @author Wujun
 */
public class CodeUtils {
	private static final Logger logger = LoggerFactory.getLogger(CodeUtils.class);
	
	public static void main(String[] args) throws Exception {
		String tables = "res_basc,res_basc_arg,api_config";
//		String tables = "git_user";
//		String tables = "app_infoenvt,app_member,app_datasource,app_git_config,git_user,app_deploy_config";
		//GenUtils.isDefaultTemplate = true;
		GenUtils.genTables(GenUtils.getClassInfo(tables.split(",")),Arrays.asList(tables.split(",")));;
	}

}
