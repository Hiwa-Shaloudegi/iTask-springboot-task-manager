package dev.hiwa.itask.mappers.impl;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.domain.entities.TaskList;
import dev.hiwa.itask.mappers.TaskListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapperImpl taskMapper;

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        TaskList taskList = new TaskList();
        taskList.setId(taskListDto.id());
        taskList.setTitle(taskListDto.title());
        taskList.setDescription(taskListDto.description());
        taskList.setTasks(taskListDto.tasks().stream().map(taskMapper::fromDto).toList());

        return taskList;
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        int tasksCount = taskList.getTasks().size();
        double progress = taskList.calculateProgress();

        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                tasksCount,
                progress,
                taskList.getTasks().stream().map(taskMapper::toDto).toList()
        );
    }
}
