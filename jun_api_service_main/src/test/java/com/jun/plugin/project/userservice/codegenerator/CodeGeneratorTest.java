package com.jun.plugin.project.userservice.codegenerator;

import cn.hutool.setting.dialect.PropsUtil;
import com.google.common.collect.Lists;
import com.jun.plugin.generator.GenUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


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
