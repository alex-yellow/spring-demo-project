package com.example.springcrud.service;

import com.example.springcrud.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    int save(Task task);
    int update(Task task);
    Task findById(Long id);
    List<Task> findAll();
    List<Task> findByPublished(boolean published);
    List<Task> findByTitleContaining(String title);
    int deleteById(Long id);
    int deleteAll();
}
