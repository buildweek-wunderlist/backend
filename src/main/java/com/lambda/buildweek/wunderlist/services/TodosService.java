package com.lambda.buildweek.wunderlist.services;


import com.lambda.buildweek.wunderlist.models.Todo;

public interface TodosService
{
    Todo save(Todo todo);
    void deleteAll();
}
