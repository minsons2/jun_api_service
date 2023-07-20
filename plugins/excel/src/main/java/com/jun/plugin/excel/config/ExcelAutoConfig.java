package com.jun.plugin.excel.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 小懒虫 <auntvt＠163.com>
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.excel")
//@MapperScan(basePackages = "com.jun.plugin.excel.mapper")
@ServletComponentScan(basePackages = {"com.jun.plugin.excel"})
public class ExcelAutoConfig {
}
