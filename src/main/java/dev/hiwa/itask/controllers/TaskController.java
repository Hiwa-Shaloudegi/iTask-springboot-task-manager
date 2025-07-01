package dev.hiwa.itask.controllers;

import dev.hiwa.itask.domain.dto.TaskDto;
import dev.hiwa.itask.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task-lists/{task-list-id}/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasksByTaskList_Id(
            @PathVariable("task-list-id") UUID taskListId
    ) {
        List<TaskDto> tasks = taskService.getAllTasksByTaskList_Id(taskListId);

        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
            @PathVariable("task-list-id") UUID taskListId,
            @Valid @RequestBody TaskDto taskDto,
            UriComponentsBuilder uriBuilder
    ) {
        TaskDto savedTask = taskService.createTask(taskListId, taskDto);

        URI uri = uriBuilder
                .path("/api/task-lists/taskListId/tasks/{id}")
                .buildAndExpand(savedTask.id())
                .toUri();

        return ResponseEntity.created(uri).body(savedTask);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskByIdAndTaskList_Id(
            @PathVariable("task-list-id") UUID taskListId, @PathVariable("id") UUID id
    ) {
        TaskDto task = taskService.getTaskByIdAndTaskList_Id(taskListId, id);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("task-list-id") UUID taskListId,
            @PathVariable("id") UUID id,
            @Valid @RequestBody TaskDto taskDto
    ) {
        TaskDto task = taskService.updateTask(taskListId, id, taskDto);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus > deleteTask(
            @PathVariable("task-list-id") UUID taskListId, @PathVariable("id") UUID id
    ) {
        taskService.deleteTask(taskListId, id);

        return ResponseEntity.noContent().build();
    }
}
