package dev.hiwa.itask.domain.dto;

import dev.hiwa.itask.domain.entities.TaskPriority;
import dev.hiwa.itask.domain.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        @NotNull @NotBlank String title,
        String description,
        LocalDateTime dueDate,
        @NotNull @NotBlank TaskPriority priority,
        @NotNull @NotBlank TaskStatus status
) {}
