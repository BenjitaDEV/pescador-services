package com.caleta.pescador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient loteWebClient(){
        return WebClient.builder()
            .baseUrl("https://usuarioev-1.onrender.com")
            .build();
    }

}
