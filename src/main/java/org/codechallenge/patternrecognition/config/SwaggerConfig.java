/*
 * Copyright (c) Bank Julius Baer & Co. Ltd., 2020.
 * All Rights Reserved.
 *
 */
package org.codechallenge.patternrecognition.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

/**
 * {@link SwaggerConfig} enables the swagger-doc
 *
 * @author Fincons
 * @since 1.0
 */
@Configuration
public class SwaggerConfig {
    /**
     * Sets a custom {@link OpenAPI} instance.
     *
     * @param appName        the project name
     * @param appVersion     the project version
     * @param appDescription the project description
     * @param termsOfService the terms of service
     * @param licenseName    the license name
     * @param licenseUrl     the license URL
     * @return an {@link OpenAPI} instance
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appName,
                                 @Value("${spring.application.version}") String appVersion,
                                 @Value("${spring.application.description}") String appDescription,
                                 @Value("${spring.application.termsOfService}") String termsOfService,
                                 @Value("${spring.application.license.name}") String licenseName,
                                 @Value("${spring.application.license.url}") String licenseUrl) {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(SecurityScheme.Type.APIKEY.toString(), apiKeySecuritySchema()))
                .info(new Info()
                        .title(appName)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService(termsOfService)
                        .license(new License().name(licenseName).url(licenseUrl))
                )
                .security(Collections.singletonList(new SecurityRequirement().addList(SecurityScheme.Type.APIKEY.toString(), Arrays.asList("read", "write"))));
    }

    /**
     * Provides the authentication field to set a bearer token.
     *
     * @return a {@link SecurityScheme} instance
     */
    public SecurityScheme apiKeySecuritySchema() {
        return new SecurityScheme()
                .name("Authorization")
                .description("Use a JWT token")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.APIKEY);
    }
}