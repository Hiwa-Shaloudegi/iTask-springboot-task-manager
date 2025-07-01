package dev.hiwa.itask.controllers;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.services.TaskListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDto> getTaskListById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(taskListService.getTaskListById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListDto> updateTaskListById(
            @Valid @RequestBody TaskListDto taskListDto, @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(taskListService.updateTaskListById(id, taskListDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTaskListById(@PathVariable("id") UUID id) {
        taskListService.deleteTaskListById(id);
        return ResponseEntity.noContent().build();
    }

}
