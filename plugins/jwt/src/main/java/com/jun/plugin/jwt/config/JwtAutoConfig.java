package com.jun.plugin.jwt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 小懒虫 <auntvt＠163.com>
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.jwt")
//@MapperScan(basePackages = "com.jun.plugin.jwt")
@ServletComponentScan(basePackages = {"com.jun.plugin.jwt"})
public class JwtAutoConfig {
}
