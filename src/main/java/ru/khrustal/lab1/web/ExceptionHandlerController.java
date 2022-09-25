package ru.khrustal.lab1.web;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.khrustal.lab1.dto.ErrorResponse;
import ru.khrustal.lab1.dto.ValidationErrorResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponse badRequest(MethodArgumentNotValidException e) {
        Map<String, String> validationErrors = prepareValidationErrors(e.getFieldErrors());
        return new ValidationErrorResponse("Validation failed", validationErrors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse notFound(EntityNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse error(RuntimeException e) {
        return new ErrorResponse(e.getMessage());
    }

    private Map<String, String> prepareValidationErrors(List<FieldError> errors) {
        return errors.stream().collect(Collectors.toMap(
                FieldError::getField, e -> "Field has wrong value " + e.getRejectedValue() + ": " + e.getDefaultMessage()
        ));
    }
}
