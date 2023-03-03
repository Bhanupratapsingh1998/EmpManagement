package com.empManagement.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	 @Bean
	 public Docket api() {                
	     return new Docket(DocumentationType.SWAGGER_2)          
	       .select()
	       .apis(RequestHandlerSelectors.any())
	       .paths(PathSelectors.any())
	       .build()
	       .apiInfo(apiInfo());
	 }

	 private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "EMPLOYEE MANAGEMENT SYSTEM : REST API's", 
	       "This project is all about the Employee Details.", 
	       "API TOS", 
	       "Terms of service", 
	       new Contact("BHANU PRATAP SINGH", "www.hashstudioz.com", "bhanu.pratap@hashstudioz.com"), 
	       "License of API", "API license URL", Collections.emptyList());
	 }
}
