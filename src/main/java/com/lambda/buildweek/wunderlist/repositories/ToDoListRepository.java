package com.lambda.buildweek.wunderlist.repositories;

import com.lambda.buildweek.wunderlist.models.ToDoList;
import org.springframework.data.repository.CrudRepository;

public interface ToDoListRepository extends CrudRepository<ToDoList, Long>
{
}
