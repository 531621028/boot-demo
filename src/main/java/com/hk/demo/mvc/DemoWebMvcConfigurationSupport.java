package com.hk.demo.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * Created by hukangkang 2018/8/17
 */
@Configuration
public class DemoWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Bean
    public DemoReturnValueHandler demoReturnValueHandler() {
        return new DemoReturnValueHandler();
    }

    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(demoReturnValueHandler());
    }
}
