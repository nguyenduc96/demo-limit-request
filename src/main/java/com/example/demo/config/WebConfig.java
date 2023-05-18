package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private RequestLimitingInterceptor requestLimitingInterceptor;

    @Autowired
    public void setRequestLimitingInterceptor(RequestLimitingInterceptor requestLimitingInterceptor) {
        this.requestLimitingInterceptor = requestLimitingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLimitingInterceptor);
    }
}
