package com.rvapp.courseapi;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");    
   }
	
  @Bean
  public Docket greetingApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.rvapp.courseapi."))
        .build()
        .apiInfo(metaData()).securityContexts(Arrays.asList(securityContext()))
        .securitySchemes(Arrays.asList(basicAuthScheme()));

  }
  
  private SecurityContext securityContext() {
      return SecurityContext.builder()
              .securityReferences(Arrays.asList(basicAuthReference()))
              .build();
  }

  private SecurityScheme basicAuthScheme() {
      return new BasicAuth("basicAuth");
  }

  private SecurityReference basicAuthReference() {
      return new SecurityReference("basicAuth", new AuthorizationScope[0]);
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("Spring Boot REST API")
        .description("\"Spring Boot REST API\"")
        .version("1.0.0")
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
        .build();
  }

  @Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addRedirectViewController("/com.rvapp.courseapi/v2/api-docs", "/v2/api-docs");
	    registry.addRedirectViewController("/com.rvapp.courseapi/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
	    registry.addRedirectViewController("/com.rvapp.courseapi/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
	    registry.addRedirectViewController("/com.rvapp.courseapi/swagger-resources", "/swagger-resources");
	}

	
}
