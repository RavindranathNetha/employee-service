package com.learning.ark.training.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.learning.ark.training.util.Constants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Ravi Kumar Annepu
 */
@Configuration
public class SwaggerConfig {

	private static final String TITLE = "Training Web Services";

	private static final String DESC = TITLE;

	private static final String VERSION = "1.0.0";

	private static final String CONTACT_NAME = "Ravi Kumar Annepu";

	private static final String LICENSE = "License";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).securitySchemes(securitySchemes());
	}

	private List<? extends SecurityScheme> securitySchemes() {
		return Collections
				.singletonList(new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, Constants.HEADER));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(TITLE, DESC, VERSION, "", CONTACT_NAME, LICENSE, "");

	}

}
