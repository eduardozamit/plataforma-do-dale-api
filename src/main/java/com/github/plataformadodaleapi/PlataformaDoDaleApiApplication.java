package com.github.plataformadodaleapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api Backend", version = "1", description = "Api que gerencia recrutadores e alunos"))
@EnableScheduling
public class PlataformaDoDaleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlataformaDoDaleApiApplication.class, args);
    }
}