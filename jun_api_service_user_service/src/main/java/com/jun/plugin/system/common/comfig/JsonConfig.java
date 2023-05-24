package com.jun.plugin.system.common.comfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;

@Configuration
public class JsonConfig {
 

//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//		// 设置解析JSON工具类
//		ObjectMapper objectMapper = new ObjectMapper();
//		// 设置解析日期的工具类
//		objectMapper.setDateFormat(dateFormat);
//		// 忽略未知属性 防止解析报错
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		jsonConverter.setObjectMapper(objectMapper);
//		List<MediaType> list = new ArrayList<>();
//		list.add(MediaType.APPLICATION_JSON_UTF8);
//		jsonConverter.setSupportedMediaTypes(list);
//		return jsonConverter;
//	}

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
		// 根据实际业务支持各种复杂格式的日期字符串。
		@Override
		public Date parse(String source) {
			try {
				return super.parse(source);// 支持解析指定pattern类型。
			} catch (Exception e) {
				try {
					return new StdDateFormat().parse(source);// 支持解析long类型的时间戳
				} catch (ParseException e1) {
					throw new RuntimeException("日期格式非法：" + e);
				}
			}
		}
	};
}
