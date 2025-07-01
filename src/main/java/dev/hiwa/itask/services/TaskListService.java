package dev.hiwa.itask.services;

import dev.hiwa.itask.domain.dto.TaskListDto;

import java.util.List;

public interface TaskListService {
    List<TaskListDto> getAllTaskLists();

    TaskListDto createTaskList(TaskListDto taskListDto);
}
