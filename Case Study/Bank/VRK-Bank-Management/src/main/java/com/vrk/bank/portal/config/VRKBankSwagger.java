package com.vrk.bank.portal.config;

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
public class VRKBankSwagger {

    @Bean
    public Docket createSwaggerUI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vrk.bank.portal"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("VRK Bank Management")
                .description("Tracking User Account and Loan Details.")
                .termsOfServiceUrl("https://www.vrk.com/")
                .contact(new Contact("Venkatesh Kokkanti","https://www.vrk.in/","venkat123@gmail.com"))
                .version("1.0")
                .build();
    }
}
