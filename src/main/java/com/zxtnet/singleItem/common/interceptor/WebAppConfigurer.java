package com.zxtnet.singleItem.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @description: web配置类，配置拦截器等
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Bean
    public TemplateInterceptor getTemplateInterceptor() {
        return new TemplateInterceptor();
    }

    //  配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                //  拦截所有请求
//                .addPathPatterns("/**")
//                //  排除请求
//                .excludePathPatterns("/user/login", "/user/register")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

}
