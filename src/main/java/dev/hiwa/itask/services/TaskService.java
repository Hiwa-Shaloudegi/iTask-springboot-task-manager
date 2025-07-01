package dev.hiwa.itask.services;

import dev.hiwa.itask.domain.dto.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDto> getAllTasksByTaskList_Id(UUID taskListId);
    TaskDto createTask(UUID taskListId, TaskDto taskDto);

    TaskDto getTaskByIdAndTaskList_Id(UUID taskListId, UUID id);

    TaskDto updateTask(UUID taskListId, UUID id, TaskDto taskDto);

    void deleteTask(UUID taskListId, UUID id);
}
