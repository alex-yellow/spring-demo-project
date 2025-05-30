package com.example.springcrud.repository;

import com.example.springcrud.model.Task;

import java.util.List;

public interface TaskRepository {
    int save(Task task);
    int update(Task task);
    Task findById(Long id);
    List<Task> findAll();
    List<Task> findByPublished(boolean published);
    List<Task> findByTitleContaining(String title);
    int deleteById(Long id);
    int deleteAll();
    public List<Task> findPaginatedAndSorted(String sortBy, String order, int page, int size);
}
