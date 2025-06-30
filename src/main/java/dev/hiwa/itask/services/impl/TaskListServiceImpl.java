package dev.hiwa.itask.services.impl;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.domain.entities.TaskList;
import dev.hiwa.itask.mappers.TaskListMapper;
import dev.hiwa.itask.repositories.TaskListRepository;
import dev.hiwa.itask.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    @Override
    public List<TaskListDto> getAllTaskLists() {
        List<TaskList> taskLists= taskListRepository.findAll();
        return taskLists.stream().map(taskListMapper::toDto).toList();

    }
}
