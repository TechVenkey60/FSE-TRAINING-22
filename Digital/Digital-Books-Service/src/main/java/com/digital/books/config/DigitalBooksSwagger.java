package com.digital.books.config;

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
public class DigitalBooksSwagger {

    @Bean
    public Docket createSwaggerUI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.digital.books"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Digital Books WareHouse")
                .description("Tracking Information About Books And It's Subscriptions")
                .termsOfServiceUrl("https://www.digitalbooks.com/")
                .contact(new Contact("Venkatesh Kokkanti","https://www.vrk.in/","venkat123@gmail.com"))
                .version("1.0")
                .build();
    }
}
