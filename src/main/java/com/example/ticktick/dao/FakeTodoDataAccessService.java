package com.example.ticktick.dao;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.ticktick.model.TodoItem;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


@Repository("FakeDao")
public class FakeTodoDataAccessService implements TodoDao {

    private static List<TodoItem> items = new ArrayList<TodoItem>(); 
    
    @Override
    public int insertTODO(UUID id, TodoItem todo) {
        items.add(new TodoItem(id, todo.getName(), todo.getDate(), todo.getDesc(), todo.getComp()));
        return 0;
    }

    @Override
    public List<TodoItem> selectAllItems() {
        return items;
    }

    @Override
    public int deleteItemByID(UUID id) {
        Optional<TodoItem> maybe = selectItemByID(id);
        if (maybe.isEmpty()) {return 0;}
        else  {
            items.remove(maybe.get());
            return 1;
        } 
    }

    @Override
    public int updateItemByID(UUID id, TodoItem todo) {
        Optional<TodoItem> maybe = selectItemByID(id);
        if (maybe.isEmpty()) {return 0;}
        else  {
            int index = items.indexOf(maybe);
            items.set(index, new TodoItem(id, todo.getName(), todo.getDate(), todo.getDesc(), todo.getComp()));
            return 1;
        } 
    }

    @Override
    public Optional<TodoItem> selectItemByID(UUID id) {
        return items.stream().filter(item -> item.getID().equals(id)).findFirst();
    }

}
