package hu.marktsoft.epipoc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig
{
    @Bean
    public OpenAPI openApi()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("epi-poc")
                        .description("epi-poc")
                        .version("1.0")
                        .license(new License()
                                .name("Apache License, Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Andras Gyongyosi")
                                .email("gyongyosi.andras@marktsoft.hu")))
                .components(new Components()
                        .addSecuritySchemes("basic", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
                        .addSecuritySchemes("bearer", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer"))
                );
    }

}