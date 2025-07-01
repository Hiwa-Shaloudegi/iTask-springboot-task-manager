package dev.hiwa.itask.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        @NotNull @NotBlank String title,
        String description,
        Integer tasksCount,
        Double progress,
        List<TaskDto> tasks
) {}