package com.lambda.buildweek.wunderlist.controllers;

import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.Todo;
import com.lambda.buildweek.wunderlist.models.User;
import com.lambda.buildweek.wunderlist.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController
{
    @Autowired
    TodosService todosService;

    @GetMapping(value = "/all-todos",
        produces = "application/json")
    public ResponseEntity<?> listAllLists()
    {
        List<Todo> myList = todosService.findAll();
        return new ResponseEntity<>(myList,
            HttpStatus.OK);
    }

    @PostMapping(value = "/{listid}/new-todo",
        consumes = "application/json")
    public ResponseEntity<?> createNewToDo(@PathVariable
                                               long listid, @RequestBody Todo todo)
    {
         Todo newToDo = todosService.save(todo, listid);
        return new ResponseEntity<>(newToDo, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/complete/{todoid}")
    public ResponseEntity<?> completeTodo(@PathVariable long todoid)
    {
        todosService.markComplete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{todoid}")
    public ResponseEntity<?> updateToDo(@PathVariable long todoid, @RequestBody Todo todo)
    {
        Todo updatedTodo = todosService.update(todo, todoid);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{todoid}")
    public ResponseEntity<?> deleteToDo(@PathVariable long todoid)
    {
        todosService.delete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
