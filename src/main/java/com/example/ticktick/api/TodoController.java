package com.example.ticktick.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticktick.model.TodoItem;
import com.example.ticktick.service.TodoService;

import org.springframework.web.bind.annotation.DeleteMapping;

@RequestMapping("api/v1/item")
@RestController
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public void addItem (@Valid @NonNull @RequestBody TodoItem item) {
        todoService.addItem(item);
    }

    @GetMapping
    public List<TodoItem> getAllItems() {
        return todoService.getAllItems();
    }

    @GetMapping(path = "{id}")
    public TodoItem getItemByID(@PathVariable("id") UUID id) {
        return todoService.getItemByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteID(@PathVariable("id") UUID id) {
        todoService.deleteItem(id);
    }

    @PutMapping(path="{id}")
    public void updateItem(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody TodoItem newPerson) {
        todoService.updateItem(id, newPerson);
    }

  
}
