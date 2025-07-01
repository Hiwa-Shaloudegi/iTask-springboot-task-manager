package dev.hiwa.itask.mappers.impl;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.domain.entities.TaskList;
import dev.hiwa.itask.mappers.TaskListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        taskList.setTasks(Optional
                                  .ofNullable(taskListDto.tasks())
                                  .map(taskDtos -> taskDtos.stream().map(taskMapper::fromDto).toList())
                                  .orElse(null));


        return taskList;
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(taskList.getId(),
                               taskList.getTitle(),
                               taskList.getDescription(),
                               Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                               taskList.calculateProgress(),
                               Optional
                                       .ofNullable(taskList.getTasks())
                                       .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                                       .orElse(null)
        );

    }
}
