package com.jun.plugin.fileUpload.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.fileUpload")
@MapperScan(basePackages = "com.jun.plugin.fileUpload.mapper")
@ServletComponentScan(basePackages = {"com.jun.plugin.fileUpload"})
public class FileUploadAutoConfig {
}
