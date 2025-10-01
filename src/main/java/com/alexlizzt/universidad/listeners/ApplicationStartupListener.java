package com.alexlizzt.universidad.listeners;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String port = event.getApplicationContext().getEnvironment().getProperty("server.port", "8080");
        String contextPath = event.getApplicationContext().getEnvironment().getProperty("server.servlet.context-path", "");
        System.out.println("Aplicación arrancada en: http://localhost:" + port + contextPath);
    }
}