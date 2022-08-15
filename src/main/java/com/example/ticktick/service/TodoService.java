package com.example.ticktick.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ticktick.dao.TodoDao;
import com.example.ticktick.model.TodoItem;


@Service
public class TodoService {
    private final TodoDao todoDao;

    @Autowired
    public TodoService(@Qualifier("postgres") TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public int addItem(TodoItem item) {
        return todoDao.insertTODO(item);
    }

    public List<TodoItem> getAllItems() {
        return todoDao.selectAllItems();
    }

    public Optional<TodoItem> getItemByID(UUID id) {
        return todoDao.selectItemByID(id);
    }

    public int deleteItem(UUID id) {
        return todoDao.deleteItemByID(id);
    }

    public int updateItem(UUID id, TodoItem newItem) {
        return todoDao.updateItemByID(id, newItem);
    }

}
