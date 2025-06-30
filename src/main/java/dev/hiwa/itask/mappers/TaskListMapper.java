package dev.hiwa.itask.mappers;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
