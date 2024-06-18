package com.example.spring_boot_3_todo_application.services;

import com.example.spring_boot_3_todo_application.models.todoItem;
import com.example.spring_boot_3_todo_application.repositories.todoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TodoItemService {
    @Autowired
    private todoItemRepository TodoItemrepository;

    public Iterable<todoItem> getAll() {
        return TodoItemrepository.findAll();
    }
    public Optional<todoItem> getById(Long id) {
        return TodoItemrepository.findById(id);
    }

    public todoItem save(todoItem TodoItem) {

        if (TodoItem.getId()==null)
        {
            TodoItem.setCreatedAt(Instant.now());
        }
        TodoItem.setUpdatedAt(Instant.now());
        return TodoItemrepository.save(TodoItem);
    }
    public void delete(todoItem TodoItem) {
        TodoItemrepository.delete(TodoItem);
    }
}
