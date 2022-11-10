package com.digital.user.books.config;

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

@Configuration
@EnableSwagger2
public class UserServiceSwaggerConfig {

    @Bean
    public Docket createSwaggerUI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.digital.user.books"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("User Management Service")
                .description("Storing And Retrieving The User Roles Information")
                .termsOfServiceUrl("https://www.userstore.com/")
                .contact(new Contact("Venkatesh Kokkanti","https://www.vrk.in/","venkat123@gmail.com"))
                .version("1.0")
                .build();
    }
}
