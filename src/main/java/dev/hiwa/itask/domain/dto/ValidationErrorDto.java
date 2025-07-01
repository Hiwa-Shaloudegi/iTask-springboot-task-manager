package dev.hiwa.itask.domain.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record ValidationErrorDto(
        int status,
        String message,
        Map<String, String> errors
) {}
