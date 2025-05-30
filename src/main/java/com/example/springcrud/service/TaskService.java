package com.example.springcrud.service;

import com.example.springcrud.model.Task;
import com.example.springcrud.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(String title) {
        if (title == null) {
            return taskRepository.findAll();
        } else {
            return taskRepository.findByTitleContaining(title);
        }
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(new Task(task.getTitle(), task.getDescription(), false));
    }

    public Optional<Task> updateTask(Long id, Task task) {
        return taskRepository.findById(id).map(existing -> {
            existing.setTitle(task.getTitle());
            existing.setDescription(task.getDescription());
            existing.setPublished(task.isPublished());
            return taskRepository.save(existing);
        });
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public List<Task> findByPublished() {
        return taskRepository.findByPublished(true);
    }
}
