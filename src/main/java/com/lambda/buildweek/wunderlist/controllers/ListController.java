package com.lambda.buildweek.wunderlist.controllers;

import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.User;
import com.lambda.buildweek.wunderlist.models.UserToDoList;
import com.lambda.buildweek.wunderlist.services.ToDoListService;
import com.lambda.buildweek.wunderlist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListController
{
    @Autowired
    ToDoListService toDoListService;

    @Autowired
    UserService userService;


    @GetMapping(value = "/my-lists",
        produces = "application/json")
    public ResponseEntity<?> listAllLists()
    {
        List<ToDoList> myLists = toDoListService.findAll();
        return new ResponseEntity<>(myLists,
            HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/new-list",
    consumes = "application/json")
    public ResponseEntity<?> createNewList(@PathVariable long id, String name)
    {
            ToDoList newList = new ToDoList();
            newList.setName(name);
            toDoListService.save(newList);

            User myUser = userService.findUserById(id);
            myUser.getLists().add(new UserToDoList(myUser, newList));
            userService.update(myUser, id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
