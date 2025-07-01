package dev.hiwa.itask.controllers;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.services.TaskListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {
    private final TaskListService taskListService;

    @GetMapping
    public ResponseEntity<List<TaskListDto>> getTaskLists() {
        return ResponseEntity.ok(taskListService.getAllTaskLists());
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(
            @Valid @RequestBody TaskListDto taskListDto, UriComponentsBuilder uriBuilder
    ) {
        TaskListDto savedTaskList = taskListService.createTaskList(taskListDto);
        var uri = uriBuilder.path("/api/task-lists/{id}").buildAndExpand(savedTaskList.id()).toUri();

        return ResponseEntity.created(uri).body(savedTaskList);
    }

}
