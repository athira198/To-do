package com.example.spring_boot_3_todo_application.controllers;

import com.example.spring_boot_3_todo_application.models.todoItem;
import com.example.spring_boot_3_todo_application.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class TodoFormController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(todoItem TodoItem)
    {
        return "new-todo-item";
    }
    @PostMapping("/todo")
    public String createTodoitem(@Valid todoItem TodoItem, BindingResult result, Model model )
    {
        todoItem item=new todoItem();
        item.setDescription(TodoItem.getDescription());
        item.setIsComplete(TodoItem.getIsComplete());
        todoItemService.save( TodoItem);
        return "redirect:/";

    }
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        todoItem TodoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        todoItemService.delete(TodoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        todoItem TodoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        model.addAttribute("todo", TodoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid todoItem TodoItem, BindingResult result, Model model) {

        todoItem item = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(TodoItem.getIsComplete());
        item.setDescription(TodoItem.getDescription());

        todoItemService.save(item);

        return "redirect:/";
    }
}
