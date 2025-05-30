package com.example.springcrud.controller;

import com.example.springcrud.model.Task;
import com.example.springcrud.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Получить все задачи
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    // Получить задачу по id
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    // Создать новую задачу
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        int result = taskService.save(task);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Обновить задачу
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskService.findById(id);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }
        task.setId(id);
        int result = taskService.update(task);
        if (result > 0) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Удалить задачу по id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        int result = taskService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Удалить все задачи
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        taskService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // Найти все опубликованные задачи
    @GetMapping("/published")
    public ResponseEntity<List<Task>> findByPublished(@RequestParam boolean status) {
        List<Task> tasks = taskService.findByPublished(status);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    // Поиск задач по названию (частичное совпадение)
    @GetMapping("/search")
    public ResponseEntity<List<Task>> findByTitle(@RequestParam String title) {
        List<Task> tasks = taskService.findByTitleContaining(title);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }
}