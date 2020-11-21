package com.humuson.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "CLIENT WEB API 명세서",
                description = "메시지 발송을 예약하고 결과를 조회하는 웹 사이트 내부 API 명세입니다.",
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


