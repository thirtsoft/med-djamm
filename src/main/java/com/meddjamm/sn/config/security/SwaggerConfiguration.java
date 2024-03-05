//package com.meddjamm.sn.config.security;
//
//import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.Collections;
//import java.util.List;
//
////@Configuration
////@EnableSwagger2
//public class SwaggerConfiguration {
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(
//                        new ApiInfoBuilder()
//                                .description("Gestion Hospi API documentation")
//                                .title("Gestion Hosto REST API")
//                                .build()
//                )
//                .groupName("REST API V1")
////                .securityContexts(Collections.singletonList(securityContext()))
////                .securitySchemes(Collections.singletonList(apiKey()))
//                .useDefaultResponseMessages(false)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.meddjamm.sn"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Collections.singletonList(
//                new SecurityReference("JWT", authorizationScopes));
//    }
//}