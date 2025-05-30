package com.example.springcrud.service;

import com.example.springcrud.model.Task;
import com.example.springcrud.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id){
        return taskRepository.findById(id);
    }

    @Override
    public int save(Task task){
        return taskRepository.save(task);
    }

    @Override
    public int update(Task task){
        return taskRepository.update(task);
    }

    @Override
    public int deleteById(Long id){
        return taskRepository.deleteById(id);
    }

    @Override
    public int deleteAll(){
        return taskRepository.deleteAll();
    }

    @Override
    public List<Task> findByPublished(boolean published){
        return taskRepository.findByPublished(published);
    }

    @Override
    public  List<Task> findByTitleContaining(String title){
        return taskRepository.findByTitleContaining(title);
    }
}
