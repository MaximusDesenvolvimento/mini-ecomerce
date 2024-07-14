package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // Permite todas as requisições para todos os endpoints
                .allowedOrigins("*") // Permite requisições de qualquer origem
                .allowedMethods("*") // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true);
    }
}
