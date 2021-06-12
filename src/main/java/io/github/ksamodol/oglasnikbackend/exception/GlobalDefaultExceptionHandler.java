package io.github.ksamodol.oglasnikbackend.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.coyote.Response;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(Exception e, WebRequest webRequest) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(Exception e, WebRequest webRequest) {
        if(e.getCause() instanceof InvalidFormatException && ((InvalidFormatException) e.getCause()).getValue() instanceof String ) {
            String badValue = (String) ((InvalidFormatException) e.getCause()).getValue();
            return ResponseEntity.badRequest().body("Unrecognized value '" + badValue + "'");
        }
        return ResponseEntity.badRequest().build();
    }
}
