package com.ixchou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 23:53<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: Swagger的配置类<br/>
 * <b>Description</b>:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ixchou"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建 API 的基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("新筹平台")
                .description("新筹平台 RESTful 接口文档")
                .termsOfServiceUrl("http://www.ixchou.com/")
                .contact(new Contact("新筹", "http://www.ixchou.com", "admin@ixchou.com"))
                .version("1.0.0")
                .build();
    }
}
