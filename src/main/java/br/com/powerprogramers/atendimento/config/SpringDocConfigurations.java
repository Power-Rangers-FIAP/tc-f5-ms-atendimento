package br.com.powerprogramers.atendimento.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Users API")
                                .version("1.0")
                                .description("Microservices 'Users' - Tech Challenge FIAP F5"));
    }
}
