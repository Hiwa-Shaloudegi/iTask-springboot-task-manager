package dev.hiwa.itask.domain.dto;

public record ErrorDto(
        int status,
        String message
) {}
