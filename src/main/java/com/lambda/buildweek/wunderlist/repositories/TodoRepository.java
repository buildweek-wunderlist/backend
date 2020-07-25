package com.lambda.buildweek.wunderlist.repositories;

import com.lambda.buildweek.wunderlist.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
}
