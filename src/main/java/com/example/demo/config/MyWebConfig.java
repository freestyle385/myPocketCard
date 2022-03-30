package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import com.example.demo.interceptor.NeedLogoutInterceptor;


@Configuration
public class MyWebConfig implements WebMvcConfigurer{
	
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	@Autowired
	NeedLogoutInterceptor needLogoutInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration beforeActionIr = registry.addInterceptor(beforeActionInterceptor);
		beforeActionIr.addPathPatterns("/**").excludePathPatterns("/resource/**");
		
		InterceptorRegistration needLoginIr = registry.addInterceptor(needLoginInterceptor);
		needLoginIr.addPathPatterns("/usr/card/list**").addPathPatterns("/usr/card/**Write**")
		.addPathPatterns("/usr/member/info**").addPathPatterns("/usr/member/doDelete");
		
		InterceptorRegistration needLogoutIr = registry.addInterceptor(needLogoutInterceptor);
		needLogoutIr.addPathPatterns("/usr/member/**Login**");
	}
}
