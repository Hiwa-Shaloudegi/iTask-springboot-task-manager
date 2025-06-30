package dev.hiwa.itask.controllers;

import dev.hiwa.itask.domain.dto.TaskListDto;
import dev.hiwa.itask.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/task-lists/")
public class TaskListController {
    private final TaskListService taskListService;

    @GetMapping
    public ResponseEntity<List<TaskListDto>> getTaskLists() {
         return ResponseEntity.ok(taskListService.getAllTaskLists());
    }

}
