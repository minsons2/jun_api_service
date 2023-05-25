package com.bjc.lcp.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjc.lcp.core.api.executor.AbstractExecutor;
import com.bjc.lcp.core.api.executor.IExecutor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * 组件ID：BAS000000000100
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句） 
 * 版本历史：1.0.0版 参数说明： 
 * 参 数1：要执行的SQL语句 
 * 参 数2：sql参数 
 * 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_config，测试JSONOBject对象直接返回-保存在庫裡面
 */
public class TestUploadService extends AbstractExecutor implements  IExecutor<JSONObject, Map<String,Object>>  {

	@Override
	public JSONObject execute(Map<String, Object> params) throws Exception{
		HttpServletRequest request = (HttpServletRequest) params.get("_request");
		super.parameters = params;
		String servletPath = (String) params.get("path");
		System.out.println(JSON.toJSONString(collectParams(params)));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    MultipartFile file = multipartRequest.getFile("file"); // 通过参数名获取指定文件
	    FileUtil.writeBytes(file.getBytes(), "D:/abc/"+RandomUtil.randomInt()+file.getOriginalFilename());
	    String bizid = multipartRequest.getParameter("bizid");
	    String fileName = file.getOriginalFilename();
	    
	    JSONObject json = new JSONObject();
	    json.put("fileName", fileName);
	    json.put("bizid", getPara("bizid"));
	    json.put("filePaht", bizid);
		return json;
	}

	@Override
	public JSONObject rollback(Map<String, Object> parms) {
		return null;
	}

}
