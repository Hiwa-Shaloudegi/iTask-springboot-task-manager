package dev.hiwa.itask.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer tasksCount,
        Double progress,
        List<TaskDto> tasks
) {}