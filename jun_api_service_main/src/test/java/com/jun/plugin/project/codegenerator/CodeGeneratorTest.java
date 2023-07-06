package com.jun.plugin.project.codegenerator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.alibaba.druid.pool.DruidDataSource;
import com.gitthub.wujun728.engine.generator.ClassInfo;
import com.gitthub.wujun728.engine.generator.FieldInfo;
import com.gitthub.wujun728.engine.generator.GenUtils;
import com.google.common.collect.Lists;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class CodeGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(CodeGeneratorTest.class);

    public static Properties props = new Properties(); // 配置文件

    @Test
    public void codeGenerator() throws Exception {
        GenUtils.PROJECT_PATH = FileUtil.getParent(CodeGeneratorTest.class.getProtectionDomain().getCodeSource().getLocation().getPath(),2);
        InputStream is = GenUtils.class.getClassLoader().getResourceAsStream("config.properties");
        props.load(is);
        GenUtils.TEMPLATE_FILE_PATH = GenUtils.PROJECT_PATH + props.getProperty("templateFilePath");// 模板位置

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(GenUtils.props.getProperty("driver"));
        ds.setUrl(GenUtils.props.getProperty("url"));
        ds.setUsername(GenUtils.props.getProperty("uname"));
        ds.setPassword(GenUtils.props.getProperty("pwd"));
        List<String> tableNames = Arrays.asList("sys_user", "abc");
        List<ClassInfo> classInfos = Lists.newArrayList();
        tableNames.stream().forEach(t -> {
            Table table = MetaUtil.getTableMeta(ds, t);
            if(table.getPkNames().size()>0){//没有主键是不生成的
                classInfos.add(this.getClassInfo(table));
            }
        });
        genTables(classInfos);
    }


    public static void genTables(List<ClassInfo> classInfos) throws Exception {
        genTables(classInfos, "");
    }

    public static void genTables(List<ClassInfo> classInfos, String genType) throws Exception {
        if (StringUtils.isEmpty(genType)) {
            genType = "single" ;
        }
        List<String> templates = getTemplates();
//		switch (genType){
//			case "single" : templates = getTemplates();break;
////			case "parent" : templates = getTemplates();break;
//		}
//        List<ClassInfo> classInfos = GenUtils.getClassInfo(tables);
        classInfos.forEach(classInfo -> {
            Map<String, Object> datas = new HashMap<String, Object>();
            datas.put("classInfo", classInfo);
            datas.put("authorName", "wujun");
            datas.put("isLombok", true);
            datas.put("isAutoImport", true);
            datas.put("isWithPackage", true);
            datas.put("isSwagger", true);
            datas.put("isComment", true);
            datas.put("packageName", GenUtils.PACKAGE);
            Map<String, String> result = new HashMap<String, String>();
            try {
                result = GenUtils.processTemplatesStringWriter(datas, templates);
                GenUtils.processTemplatesFileWriter(classInfo, datas, templates);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item : result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
                }
            }
            logger.info("生成代码行数：{}", lineNum);
        });
        if (CollectionUtils.isEmpty(classInfos)) {
            logger.error("找不到当前表的元数据classInfos.size()：{}", classInfos.size());
        }
    }

    public static List<String> getTemplates() {
        List<String> templates = Lists.newArrayList();
        templates.add("code-generator/mybatis-plus-single/controller.ftl");
        templates.add("code-generator/mybatis-plus-single/entity.ftl");
        templates.add("code-generator/mybatis-plus-single/mapper.ftl");
        templates.add("code-generator/mybatis-plus-single/service.ftl");
        templates.add("code-generator/mybatis-plus-single/dto.ftl");
        templates.add("code-generator/mybatis-plus-single/vo.ftl");
        templates.add("code-generator/mybatis-plus-single/service.impl.ftl");
        return templates;
    }


    public static ClassInfo getClassInfo(Table table) {
        // V1 初始化数据及对象 模板V1 field List
        List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
        for (Column column : table.getColumns()) {
            // V1 初始化数据及对象
            String remarks = column.getComment();// cloumnsSet.getString("REMARKS");// 列的描述
            String columnName = column.getName();// cloumnsSet.getString("COLUMN_NAME"); // 获取列名
            String javaType = GenUtils.getType(column.getType()/*cloumnsSet.getInt("DATA_TYPE")*/);// 获取类型，并转成JavaType
            String columnType = column.getTypeName();// 获取类型，并转成JavaType
            long COLUMN_SIZE = column.getSize();// cloumnsSet.getInt("COLUMN_SIZE");// 获取
            String COLUMN_DEF = column.getColumnDef();// cloumnsSet.getString("COLUMN_DEF");// 获取
            Boolean nullable = column.isNullable();// cloumnsSet.getInt("NULLABLE");// 获取
            String propertyName = GenUtils.replace_(GenUtils.replaceRowPreStr(columnName));// 处理列名，驼峰
            Boolean isPk = column.isPk();

            // V1 初始化数据及对象
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setColumnName(columnName);
            fieldInfo.setColumnType(columnType);
            fieldInfo.setFieldName(propertyName);
            fieldInfo.setFieldClass(GenUtils.simpleName(javaType));
            fieldInfo.setFieldComment(remarks);
            fieldInfo.setColumnSize(COLUMN_SIZE);
            fieldInfo.setNullable(nullable);
            fieldInfo.setFieldType(javaType);
            fieldInfo.setIsPrimaryKey(isPk);
            fieldList.add(fieldInfo);
        }
        if (fieldList != null && fieldList.size() > 0) {
            ClassInfo classInfo = new ClassInfo();
            classInfo.setTableName(table.getTableName());
            String className = GenUtils.replace_(GenUtils.replaceTabblePreStr(table.getTableName())); // 名字操作,去掉tab_,tb_，去掉_并转驼峰
            String classNameFirstUpper = GenUtils.firstUpper(className); // 大写对象
            classInfo.setClassName(classNameFirstUpper);
            classInfo.setClassComment(table.getComment());
            classInfo.setFieldList(fieldList);
            classInfo.setPkSize(table.getPkNames().size());
            return classInfo;
        }
        return null;
    }

}
