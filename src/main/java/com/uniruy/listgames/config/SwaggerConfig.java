package com.uniruy.listgames.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod") // Ativa somente fora de produção
public class SwaggerConfig {
    // Nenhuma config extra por enquanto
}