package com.proyectosmera.ms_hotelgema_gestion_reservas.exceptions;

import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // ------------------------------
  // 1. Recurso no encontrado local
  // ------------------------------
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("error", "Recurso no encontrado");
    response.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  // ------------------------------
  // 2. Validación con @Valid (DTO)
  // ------------------------------
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

    Map<String, String> fieldErrors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        fieldErrors.put(error.getField(), error.getDefaultMessage())
    );

    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Validación fallida");
    response.put("messages", fieldErrors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  // ------------------------------
  // 3. Validaciones a nivel de Service (@Validated)
  // ------------------------------
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Validación fallida");
    response.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Object> handleValidationException(ValidationException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Bad Request");
    body.put("message", ex.getMessage());
    body.put("timestamp", LocalDateTime.now());

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  // ------------------------------
  // 4. Errores desde otro microservicio vía Feign
  // ------------------------------
  @ExceptionHandler(FeignException.class)
  public ResponseEntity<Map<String, Object>> handleFeignException(FeignException ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", ex.status());
    response.put("error", "Error al comunicarse con el microservicio");
    response.put("message", extraerMensajeFeign(ex));

    return ResponseEntity.status(ex.status()).body(response);
  }

  // Extraer mensaje legible desde la respuesta Feign (JSON remoto)
  private String extraerMensajeFeign(FeignException ex) {
    try {
      return ex.contentUTF8(); // Devuelve JSON del ms-usuarios
    } catch (Exception e) {
      return ex.getMessage();
    }
  }

  // ------------------------------
  // 5. Error genérico NO esperado
  // ------------------------------
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {

    ex.printStackTrace(); // log para debug

    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.put("error", "Error inesperado");
    response.put("message", "Ha ocurrido un error interno. Por favor, contacte con soporte.");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
