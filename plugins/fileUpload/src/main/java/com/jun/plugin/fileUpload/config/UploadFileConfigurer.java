package com.jun.plugin.fileUpload.config;

import com.jun.plugin.common.utils.SpringUtil;
import com.jun.plugin.fileUpload.test.FileUpload;
import com.jun.plugin.fileUpload.config.properties.UploadProjectProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 小懒虫
 * @date 2018/11/3
 */
@Configuration
public class UploadFileConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        UploadProjectProperties properties = SpringUtil.getBean(UploadProjectProperties.class);
        registry.addResourceHandler(properties.getStaticPath()).addResourceLocations("file:" + FileUpload.getUploadPath());
    }
}
