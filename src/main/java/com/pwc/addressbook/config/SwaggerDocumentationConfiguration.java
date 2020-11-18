package com.pwc.addressbook.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author Sukanta
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfiguration {

	@Value(value = "${swagger.enabled : true}")
	Boolean swaggerEnabled;

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
		      new HashSet<String>(Arrays.asList("application/json",
		          "application/xml"));
		
	@Bean
	public Docket swaggerAddressBookApi10() {
	    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
    		.produces(DEFAULT_PRODUCES_AND_CONSUMES)
	        .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
	        .groupName("address-book-api-1.0")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("com.pwc.addressbook"))
	            .paths(regex("/address-book/v1.0.*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.0").title("User API").description("Documentation Address Book API v1.0").build());
	}
	@Bean
	public Docket swaggerAddressBookApi11() {
	    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
    		.produces(DEFAULT_PRODUCES_AND_CONSUMES)
	        .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
	        .groupName("address-book-api-1.1")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("com.pwc.addressbook"))
	            .paths(regex("/address-book/v1.1.*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.1").title("Address Book API").description("Documentation Address Book API v1.1").build());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Address Book API GATEWAY")
				.description("Sample REST API for centalized documentation using Spring Boot and spring-fox swagger 2 ")
				.contact(new Contact("Sukanta", "https://github.com/sukantaworkspace", "sukanta.workspace@gmail.com"))
				.version("1.0.0")
				.build();
	}

}