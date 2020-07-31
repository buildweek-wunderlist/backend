package com.lambda.buildweek.wunderlist.controllers;

import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.User;
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

    @PostMapping(value = "/{userid}/new-list",
    consumes = "application/json")
    public ResponseEntity<?> createNewList(@PathVariable long userid, @RequestBody ToDoList list)
    {
        ToDoList newList = new ToDoList(list.getTitle());
        User user = userService.findUserById(userid);
        newList.setUser(user);
        newList = toDoListService.save(newList);

        return new ResponseEntity<>(newList, HttpStatus.CREATED);
    }


    @PutMapping(value = "/update-list/{listid}",
        consumes = "application/json")
    public ResponseEntity<?> updateList (@PathVariable long listid, @RequestBody ToDoList list)
    {
        toDoListService.update(list, listid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{listid}")
    public ResponseEntity<?> deleteList (@PathVariable long listid)
    {
        toDoListService.delete(listid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
