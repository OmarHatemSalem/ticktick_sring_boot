package com.example.ticktick.dao;

import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ticktick.model.TodoItem;

@Repository("postgres")
public class TodoDataAccessService implements TodoDao{

    private final JdbcTemplate template;

    public TodoDataAccessService(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public int insertTODO(UUID id, TodoItem todo) {
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
        String strDate = formatter.format(todo.getDate());  

        String sql = "INSERT INTO tick (id, name, date, description, comp) VALUES ('"
                    +id+"', '"+todo.getName()+"', DATE '"+strDate+"', '"+todo.getDesc()+"', FALSE)";

        return template.update(sql, new Object[]{}, new int[]{});
    }

    @Override
    public List<TodoItem> selectAllItems() {
        String sql = "SELECT * FROM tick";

        return template.query(
                sql,
                (rs, rowNum) ->
                        {
                            try {
                                return new TodoItem(
                                        UUID.fromString(rs.getString("id")),
                                        rs.getString("name"),
                                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date")),
                                        rs.getString("description"),
                                        rs.getBoolean("comp")  
                                );
                            } catch (ParseException e) {
                                return new TodoItem(
                                        UUID.fromString(rs.getString("id")),
                                        rs.getString("name"),
                                        null,
                                        rs.getString("description"),
                                        rs.getBoolean("comp")  
                                );
                            }
                        }
        );
    }

    @Override
    public Optional<TodoItem> selectItemByID(UUID id) {
        String sql = "SELECT * FROM tick WHERE id = ?";

        TodoItem person = template.queryForObject(
                sql,
                new Object[]{id},
                new int[]{Types.OTHER},
                (rs, rowNum) ->
                        {
                            try {
                                return new TodoItem(
                                        UUID.fromString(rs.getString("id")),
                                        rs.getString("name"),
                                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date")),
                                        rs.getString("description"),
                                        rs.getBoolean("comp")  
                                );
                            } catch (ParseException e) {
                                return new TodoItem(
                                        UUID.fromString(rs.getString("id")),
                                        rs.getString("name"),
                                        null,
                                        rs.getString("description"),
                                        rs.getBoolean("comp")  
                                );
                            }
                        }
        );

        return Optional.ofNullable(person);
    }

    @Override
    public int deleteItemByID(UUID id) {
        String sql = "DELETE FROM tick WHERE id= ? ";   
        
        return template.update(sql, new Object[]{id}, new int[]{Types.OTHER});
    }

    @Override
    public int updateItemByID(UUID id, TodoItem todo) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
        String strDate = formatter.format(todo.getDate());  

        String sql = "UPDATE tick\nSET name = '"
                        +todo.getName()+"', date = DATE '"+strDate+"', description='', comp = "+todo.getComp()
                        +"\nWHERE id = '" + id + "'";

        return template.update(sql, new Object[]{}, new int[]{});
    }

   
    
}
