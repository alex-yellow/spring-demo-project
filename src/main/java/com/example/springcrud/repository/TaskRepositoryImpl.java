package com.example.springcrud.repository;

import com.example.springcrud.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final TaskRowMapper taskRowMapper = new TaskRowMapper();

    @Override
    public List<Task> findAll(){
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, taskRowMapper);
    }

    @Override
    public Task findById(Long id){
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, taskRowMapper);
    }

    @Override
    public int save(Task task){
        String sql = "INSERT INTO tasks (title, description, published) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.isPublished());
    }

    @Override
    public int update(Task task){
        String sql = "UPDATE tasks SET title = ?, description = ?, published = ? WHERE id = ?";
        return jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.isPublished(), task.getId());
    }

    @Override
    public int deleteById(Long id){
        String sql = "DELETE FROM tasks WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteAll(){
        String sql = "DELETE FROM tasks";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Task> findByPublished(boolean published){
        String sql = "SELECT * FROM tasks WHERE published = ?";
        return jdbcTemplate.query(sql, taskRowMapper, published);
    }

    @Override
    public  List<Task> findByTitleContaining(String title){
        String sql = "SELECT * FROM tasks WHERE title ILIKE ?";
        return jdbcTemplate.query(sql, taskRowMapper, "%" + title + "%");
    }
}
