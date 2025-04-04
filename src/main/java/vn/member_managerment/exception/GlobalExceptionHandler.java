package vn.member_managerment.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, String>> handleAppException(AppException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        response.put("error", "Application Error");

        String originalMessage = ex.getMessage();
        String extractedMessage = extractConstraintError(originalMessage);
        response.put("message", extractedMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause().getMessage();
        String extractedMessage = extractConstraintError(message);
        Map<String, String> response = new HashMap<>();

        response.put("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        response.put("error", "Database Error");
        response.put("message", extractedMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> response = new HashMap<>();

        response.put("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        response.put("error", "Internal Server Error");
        response.put("message", "An unexpected error occurred. Please try again.");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String extractConstraintError(String message) {
        Pattern pattern = Pattern.compile("Duplicate entry '(.*?)' for key '(.*?)'");
        Matcher matcher = pattern.matcher(message);
        System.out.println("Extracted message: " + message);
        if (matcher.find()) {
            return "Duplicate entry: " + matcher.group(1) + " (Constraint: " + matcher.group(2) + ")";
        }
        return "Database constraint violation";
    }
}
