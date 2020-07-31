package com.lambda.buildweek.wunderlist.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambda.buildweek.wunderlist.exceptions.ResourceNotFoundException;
import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.Todo;
import com.lambda.buildweek.wunderlist.repositories.ToDoListRepository;
import com.lambda.buildweek.wunderlist.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "toDoService")
public class ToDoServiceImpl implements TodosService
{
    @Autowired
    TodoRepository todorepos;

    @Autowired
    ToDoListRepository todolistrepos;

    @Override
    public void deleteAll()
    {
        todorepos.deleteAll();
    }

    @Override
    public List<Todo> findAll()
    {
        List<Todo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Transactional
    @Override
    public Todo save(Todo todo, long listid)
    {

        Todo newToDo = new Todo();
        ToDoList addToList = todolistrepos.findById(listid)
            .orElseThrow(() -> new ResourceNotFoundException("List " + listid + " not found! Must create a list to add a todo!"));

        newToDo.setDescription(todo.getDescription());
        newToDo.setMonth(todo.getMonth());
        newToDo.setDay(todo.getDay());
        newToDo.setCompleted(todo.isCompleted());
        newToDo.setTodolist(addToList);

        return todorepos.save(newToDo);

    }

    @Transactional
    @Override
    public Todo markComplete(long todoid)
    {
        Todo t = new Todo();
        t = todorepos.findById(todoid)
            .orElseThrow(() -> new ResourceNotFoundException("To Do Item " + todoid  + "Not Found!"));
        t.setCompleted(true);

        return todorepos.save(t);
    }

    @Override
    public Todo update(
        Todo todo,
        long todoid)
    {
        Todo currentTodo = todorepos.findById(todoid)
            .orElseThrow(() -> new ResourceNotFoundException("To do item " + todoid + "Not Found!"));
        if (todo.getDescription() != null)
        {
            currentTodo.setDescription(todo.getDescription());
        }

        if (todo.getMonth() != null)
        {
            currentTodo.setMonth(todo.getMonth());
        }

        if (todo.getDay() != null)
        {
            currentTodo.setDay(todo.getDay());
        }

        return todorepos.save(currentTodo);
    }

    @Override
    public void delete(long todoid)
    {
        todorepos.deleteById(todoid);
    }
}
