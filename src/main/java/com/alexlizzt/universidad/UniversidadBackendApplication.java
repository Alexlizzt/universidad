package com.alexlizzt.universidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UniversidadBackendApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(UniversidadBackendApplication.class, args);
        String perfilActivo = String.join(", ", context.getEnvironment().getActiveProfiles());
        System.out.printf("🚀Aplicación iniciada con perfil: %s%n", perfilActivo.isEmpty() ? "default" : perfilActivo);
	}

}
