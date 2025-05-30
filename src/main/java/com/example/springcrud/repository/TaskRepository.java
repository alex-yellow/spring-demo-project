package com.example.springcrud.repository;

import com.example.springcrud.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByPublished(boolean published);
    List<Task> findByTitleContaining(String title);
}
