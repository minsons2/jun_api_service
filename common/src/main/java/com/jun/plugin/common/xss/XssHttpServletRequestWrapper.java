package com.jun.plugin.common.xss;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Xss防护过滤处理
 * @author wujun
 * @date 2018/12/9
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        Safelist whitelist = Safelist.relaxed();

        String[] params = super.getParameterValues(name);
        if(params != null){
            for (int i=0; i<params.length; i++) {
                params[i] = Jsoup.clean(params[i], whitelist).trim();
            }
        }
        return params;
    }
}