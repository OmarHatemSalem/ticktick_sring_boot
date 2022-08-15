package com.example.ticktick.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.ticktick.model.TodoItem;

public interface TodoDao {
    int insertTODO(UUID id, TodoItem todo);

    default int insertTODO(TodoItem todo) {
        UUID id = UUID.randomUUID();
        return insertTODO(id, todo);
    }

    List<TodoItem> selectAllItems();

    
    Optional<TodoItem> selectItemByID(UUID id);
    
    int deleteItemByID(UUID id);
    int updateItemByID(UUID id, TodoItem todo);
}
