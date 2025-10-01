package com.alexlizzt.universidad.excepciones;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log personalizado
        System.err.println("🚨 Error general: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor");
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDBException(DataAccessException ex) {
        System.err.println("❌ Error de base de datos: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se pudo conectar a la base de datos");
    }
}
