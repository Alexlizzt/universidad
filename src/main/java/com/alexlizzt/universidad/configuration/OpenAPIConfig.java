package com.alexlizzt.universidad.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Collections;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI getDocket(){
        return new OpenAPI()
                .info(new Info()
                    .title("Universidad Backend API")
                    .version("v2")
                    .description("Api para la administracion de universidad")
                    .contact(new Contact()
                            .name("Soporte Técnico")
                            .url("https://example.com/soporte")
                            .email("soporte@example.com"))
                    .license(new License()
                            .name("Licencia MIT")
                            .url("https://opensource.org/licenses/MIT"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación completa del proyecto")
                        .url("https://github.com/alexlizzt/universidad"));
    }

}
