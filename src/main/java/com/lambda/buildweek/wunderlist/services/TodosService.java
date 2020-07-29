package com.lambda.buildweek.wunderlist.services;


import com.lambda.buildweek.wunderlist.models.Todo;

import java.util.List;

public interface TodosService
{
    Todo save(Todo todo, long listid);

    void markComplete(long todoid);

    Todo update(Todo todo, long todoid);

    List<Todo> findAll();

    void deleteAll();

    void delete(long todoid);
}
