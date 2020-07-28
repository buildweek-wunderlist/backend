package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.exceptions.ResourceNotFoundException;
import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.Todo;
import com.lambda.buildweek.wunderlist.repositories.ToDoListRepository;
import com.lambda.buildweek.wunderlist.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Todo save(Todo todo)
    {
        return null;
    }
}
