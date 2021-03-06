package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.models.ToDoList;

import java.util.List;


public interface ToDoListService
{
    ToDoList save(ToDoList list);

    ToDoList findById(long id);

    ToDoList update(ToDoList list, long id);

    List<ToDoList> findAll();

    void delete(long id);

    void deleteAll();
}
