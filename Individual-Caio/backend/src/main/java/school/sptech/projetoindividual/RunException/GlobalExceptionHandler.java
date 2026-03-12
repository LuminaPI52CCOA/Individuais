package school.sptech.projetoindividual.RunException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(RetornoApi.class)
    public ResponseEntity<Map<String, Object>> handleRetornoApi(RetornoApi ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", 400);
        body.put("error", "Erro de Validação");
        body.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(body);
    }
}