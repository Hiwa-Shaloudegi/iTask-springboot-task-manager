package dev.hiwa.itask.services.impl;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.domain.entities.TaskList;
import dev.hiwa.itask.exceptions.ResourceNotFoundException;
import dev.hiwa.itask.mappers.TaskListMapper;
import dev.hiwa.itask.repositories.TaskListRepository;
import dev.hiwa.itask.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    @Override
    public List<TaskListDto> getAllTaskLists() {
        List<TaskList> taskLists = taskListRepository.findAll();
        return taskLists.stream().map(taskListMapper::toDto).toList();

    }

    @Override
    public TaskListDto createTaskList(TaskListDto taskListDto) {
        TaskList taskList = taskListMapper.fromDto(taskListDto);
        taskList.setId(null);
        taskList.setCreatedAt(LocalDateTime.now());
        taskList.setUpdatedAt(LocalDateTime.now());

        TaskList savedTaskList = taskListRepository.save(taskList);

        return taskListMapper.toDto(savedTaskList);
    }

    @Override
    public TaskListDto getTaskListById(UUID id) {
        TaskList taskList = taskListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TaskList.class.getSimpleName(), "id", id));

        return taskListMapper.toDto(taskList);
    }

}
