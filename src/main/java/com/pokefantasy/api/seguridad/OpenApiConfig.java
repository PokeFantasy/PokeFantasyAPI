package com.pokefantasy.api.seguridad;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pokeFantasyOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PokeFantasy API")
                        .version("0.0.1-SNAPSHOT")
                        .description("API REST del proyecto intermodular PokeFantasy Trainer "
                                + "(2º DAM, IES Venancio Blanco — Salamanca). "
                                + "Casi todos los endpoints requieren autenticación JWT. "
                                + "Usa POST /api/usuarios/login para obtener un token y pulsa el botón "
                                + "\"Authorize\" de arriba a la derecha para fijarlo en todas las llamadas.")
                        .contact(new Contact()
                                .name("Manuel Blanco Cuadrado")
                                .email("manuel.blacua@educa.jcyl.es")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Token JWT obtenido de POST /api/usuarios/login")));
    }
}
