package com.zxtnet.singleItem.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @description: swagger配置类
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@Configuration      //标注为配置类
@EnableSwagger2     //启动swagger
public class SwaggerConfig {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Bean
    public Docket createRestApi() {
        //指定包位置
        RequestHandlerSelectors.basePackage("com.zixintong.springboot.controller");
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                //apiInfo指定测试文档基本信息，这部分将在页面展示
                .apiInfo(apiInfo())
                .select()
                //apis() 控制哪些接口暴露给swagger，
                // RequestHandlerSelectors.any() 所有都暴露
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    //基本信息，页面展示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试项目标题")
                .description("接口描述")
                //联系人实体类
                .contact(
                        new Contact("郭海斌", "网址", "guo1051127705@qq.com")
                )
                //版本号
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
