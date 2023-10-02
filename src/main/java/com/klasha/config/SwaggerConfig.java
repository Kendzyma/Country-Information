package com.klasha.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.klasha")
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("com.klasha")
                .pathsToMatch("/**").addOpenApiCustomiser(getOpenApiCustomiser(springShopOpenAPI()))
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI() .info(new Info().title("Country Information")
                        .description("EndPoints to get Country info")
                        .version("v0.0.1") .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Retrieves the top N most populated cities from Ghana, New Zealand, and Italy," +
                                "Retrieves population, capital city, location, currency, ISO2&3 information for the specified country," +
                                "Retrieves a list of cities in each state of the specified country," +
                                "Converts a monetary amount from the specified country's currency to the target currency")
                        .url("https://springshop.wiki.github.org/docs"));
    }
    public OpenApiCustomiser getOpenApiCustomiser(OpenAPI openAPI){
        return new OpenApiCustomiser() {
            @Override
            public void customise(OpenAPI openApi) {

            }
        };
    }
}
