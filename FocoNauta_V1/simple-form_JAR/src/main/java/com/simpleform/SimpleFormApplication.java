package com.simpleform;
// Importações necessárias
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação que marca esta classe como a classe de aplicação Spring Boot
@SpringBootApplication
public class SimpleFormApplication {

    // Método principal (main) que é executado quando o aplicativo é iniciado
    public static void main(String[] args) {
        // Inicia o aplicativo Spring Boot passando a classe principal (SimpleFormApplication) e os argumentos da linha de comando
        SpringApplication.run(SimpleFormApplication.class, args);
    }
}

