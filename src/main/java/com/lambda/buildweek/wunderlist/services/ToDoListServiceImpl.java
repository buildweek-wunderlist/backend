package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.exceptions.ResourceNotFoundException;
import com.lambda.buildweek.wunderlist.models.*;
import com.lambda.buildweek.wunderlist.repositories.ToDoListRepository;
import com.lambda.buildweek.wunderlist.repositories.TodoRepository;
import com.lambda.buildweek.wunderlist.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "toDoListService")
public class ToDoListServiceImpl implements ToDoListService
{
    @Autowired
    ToDoListRepository todolistrepos;

    @Autowired
    TodoRepository todorepos;

    @Autowired
    UserRespository userrepos;

    @Override
    public void deleteAll()
    {
        todolistrepos.deleteAll();
    }


    @Override
    public List<ToDoList> findAll()
    {
        List<ToDoList> list = new ArrayList<>();
        todolistrepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public ToDoList findById(long id)
    {
        return todolistrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("To do list id " + id + " cannot be found!"));
    }

    @Transactional
    @Override
    public ToDoList save(ToDoList list)
    {
        ToDoList newToDoList = new ToDoList();

        if(list.getTodolistid() != 0)
        {
            todolistrepos.findById(list.getTodolistid())
                .orElseThrow(() -> new ResourceNotFoundException("List id " + list.getTodolistid() + "not found!"));
        }

        newToDoList.setTitle(list.getTitle());
        newToDoList.getTodos().clear();
        for( Todo t : list.getTodos())
        {
            Todo addTodo = new Todo();
            addTodo.setTodoid(t.getTodoid());
            addTodo.setDescription(t.getDescription());
            addTodo.setDay(t.getDay());
            addTodo.setCompleted(t.isCompleted());

            newToDoList.getTodos().add(addTodo);
        }

        newToDoList.setUser(list.getUser());


        return todolistrepos.save(newToDoList);

    }
}
