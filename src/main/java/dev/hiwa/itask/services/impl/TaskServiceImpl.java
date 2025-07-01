package dev.hiwa.itask.services.impl;

import dev.hiwa.itask.domain.dto.TaskDto;
import dev.hiwa.itask.domain.entities.Task;
import dev.hiwa.itask.domain.entities.TaskList;
import dev.hiwa.itask.exceptions.ResourceNotFoundException;
import dev.hiwa.itask.mappers.TaskMapper;
import dev.hiwa.itask.repositories.TaskListRepository;
import dev.hiwa.itask.repositories.TaskRepository;
import dev.hiwa.itask.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final TaskMapper taskMapper;

    @Transactional
    @Override
    public List<TaskDto> getAllTasksByTaskList_Id(UUID taskListId) {
        boolean exists = taskListRepository.existsById(taskListId);
        if (!exists) {
            throw new ResourceNotFoundException(TaskList.class.getSimpleName(), "id", taskListId);
        }

        return taskRepository.findAllByTaskList_Id(taskListId).stream().map(taskMapper::toDto).toList();
    }

    @Transactional
    @Override
    public TaskDto createTask(UUID taskListId, TaskDto taskDto) {
        TaskList taskList = taskListRepository
                .findById(taskListId)
                .orElseThrow(() -> new ResourceNotFoundException(TaskList.class.getSimpleName(),
                                                                 "id",
                                                                 taskListId
                ));

        Task task = taskMapper.fromDto(taskDto);
        task.setId(null);
        task.setTaskList(taskList);

        taskRepository.save(task);

        return taskMapper.toDto(task);
    }

    @Transactional
    @Override
    public TaskDto getTaskByIdAndTaskList_Id(UUID taskListId, UUID id) {
        boolean exists = taskListRepository.existsById(taskListId);
        if (!exists) {
            throw new ResourceNotFoundException(TaskList.class.getSimpleName(), "id", taskListId);
        }
        Task task = taskRepository
                .findByIdAndTaskList_Id(id, taskListId)
                .orElseThrow(() -> new ResourceNotFoundException(Task.class.getSimpleName(), "id", id));

        return taskMapper.toDto(task);
    }

    @Transactional
    @Override
    public TaskDto updateTask(UUID taskListId, UUID id, TaskDto taskDto) {
        boolean exists = taskListRepository.existsById(taskListId);
        if (!exists) {
            throw new ResourceNotFoundException(TaskList.class.getSimpleName(), "id", taskListId);
        }

        Task taskToUpdate = taskRepository
                .findByIdAndTaskList_Id(id, taskListId)
                .orElseThrow(() -> new ResourceNotFoundException(Task.class.getSimpleName(), "id", id));

        taskToUpdate.setTitle(taskDto.title());
        taskToUpdate.setDescription(taskDto.description());
        taskToUpdate.setStatus(taskDto.status());
        taskToUpdate.setPriority(taskDto.priority());
        taskToUpdate.setDueDate(taskDto.dueDate());

        taskRepository.save(taskToUpdate);

        return taskMapper.toDto(taskToUpdate);

    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID id) {
        boolean exists = taskListRepository.existsById(taskListId);
        if (!exists) {
            throw new ResourceNotFoundException(TaskList.class.getSimpleName(), "id", taskListId);
        }

        taskRepository
                .findByIdAndTaskList_Id(id, taskListId)
                .orElseThrow(() -> new ResourceNotFoundException(Task.class.getSimpleName(), "id", id));

        taskRepository.deleteById(id);
    }
}
