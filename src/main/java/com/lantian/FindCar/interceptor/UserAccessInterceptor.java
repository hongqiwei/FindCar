package com.lantian.FindCar.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.lantian.FindCar.annotation.AccessRequired;
import com.lantian.FindCar.util.CommonUtil;

public class UserAccessInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = Logger.getLogger(UserAccessInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AccessRequired annotation = method.getAnnotation(AccessRequired.class);
        if (annotation != null) {
           String access_token = request.getParameter("access_token");
           log.info("access_token :"+access_token);
           if(CommonUtil.isEmpty(access_token)){
        	   JSONObject jsonObject = new JSONObject();
        	   jsonObject.put("result", "no_login");
        	   response.getWriter().write(jsonObject.toString());
        	   return false;
           }
        }
        return true;
    }
}