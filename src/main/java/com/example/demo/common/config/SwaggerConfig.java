package com.example.demo.common.config;

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
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.ant("/api/**")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("REST API Documentation").description("API 리스트").version("V0.1")
				.termsOfServiceUrl("Terms of service").contact(new Contact("장인기", "", "rookieceo@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").build();
	}
}
