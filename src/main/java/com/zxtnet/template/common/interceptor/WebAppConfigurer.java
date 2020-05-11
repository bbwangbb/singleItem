package com.zxtnet.template.common.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @description: web配置类，配置拦截器等
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Value("${api.prefix.global}")
    private String apiPrefixGlobal;

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

    /**
     * @description:        todo：对有该些注解的接口添加请求前缀（可设置多个）
     * @author 郭海斌
     * @date 2020/5/7
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //  只对接口添加/api前缀
        configurer.addPathPrefix(apiPrefixGlobal,c -> c.isAnnotationPresent(RestController.class));
    }

}
