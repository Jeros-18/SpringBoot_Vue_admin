package com.example.demo.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 告诉spring容器，这个类是一个配置类
@EnableSwagger2 // 启用swagger2功能
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*"))) // 不去暴露的接口
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    // API文档页面显示信息
    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("前口端分离项目大乱炖模板API文档")
                .description("本文档描述了前口端分离项目大乱炖接口定义")
                .version("1.0")
                .contact(new Contact("jahui", "http://atjh.com", "1474352803@qq.com"))
                .build();
    }


}


