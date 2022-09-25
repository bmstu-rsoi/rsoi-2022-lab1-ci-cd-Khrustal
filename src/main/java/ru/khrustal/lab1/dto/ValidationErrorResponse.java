package ru.khrustal.lab1.dto;

import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private String message;
    private Map<String, String> errors;
}
