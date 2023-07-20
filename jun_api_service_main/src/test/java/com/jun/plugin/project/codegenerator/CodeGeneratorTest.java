package com.jun.plugin.project.codegenerator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.dialect.PropsUtil;
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

    @Test
    public void codeGenerator() throws Exception {
        GenUtils.init(PropsUtil.get("config").toProperties(),getTemplates());
        GenUtils.genCode(Arrays.asList("sys_code_generate_config", "sys_code_generate"));
    }

    public static List<String> getTemplates() {
        List<String> templates = Lists.newArrayList();
        templates.add("code-generator/mybatis-plus-single/controller.java.ftl");
        templates.add("code-generator/mybatis-plus-single/dto.java.ftl");
        templates.add("code-generator/mybatis-plus-single/entity.java.ftl");
        templates.add("code-generator/mybatis-plus-single/mapper.java.ftl");
        templates.add("code-generator/mybatis-plus-single/service.java.ftl");
        templates.add("code-generator/mybatis-plus-single/service.impl.java.ftl");
        templates.add("code-generator/mybatis-plus-single/vo.java.ftl");
        templates.add("code-generator/mybatis-plus-single/menu.sql.ftl");
//        templates.add("code-generator/mybatis-plus-single/edit.html.ftl");
        templates.add("code-generator/mybatis-plus-single/list.html.ftl");
        return templates;
    }



}
