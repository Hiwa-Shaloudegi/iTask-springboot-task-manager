package dev.hiwa.itask.mappers;

import dev.hiwa.itask.domain.dto.TaskDto;
import dev.hiwa.itask.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
