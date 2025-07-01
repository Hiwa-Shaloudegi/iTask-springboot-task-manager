package dev.hiwa.itask.services;

import dev.hiwa.itask.domain.dto.TaskListDto;

import java.util.List;
import java.util.UUID;

public interface TaskListService {
    List<TaskListDto> getAllTaskLists();

    TaskListDto createTaskList(TaskListDto taskListDto);

    TaskListDto getTaskListById(UUID id);

    TaskListDto updateTaskListById(UUID id, TaskListDto taskListDto);

    void deleteTaskListById(UUID id);
}
