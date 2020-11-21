package com.humuson.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "humuson-intern project API 명세서",
                description = "API 명세서",
                version = "v1"
//                contact = @Contact(name = "humuson-imc", email = "sooyoung971229@gmail.com"),
//                license = @License(name = "Apache 2.0",
//                        url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        )
)
@Configuration
public class SwaggerConfiguration {
    /**
     * customGameOpenApi.
     * @return GroupedOpenApi
     */
    @Bean
    public GroupedOpenApi customGameOpenApi() {
        String[] paths = {"/**"};
        return GroupedOpenApi.builder().group("Defualt API").pathsToMatch(paths)
                .build();
    }
}


