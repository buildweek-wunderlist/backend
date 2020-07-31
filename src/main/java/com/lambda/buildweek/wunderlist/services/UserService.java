package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.User;
import com.lambda.buildweek.wunderlist.models.UserMinimum;

import java.util.List;

public interface UserService
{
    List<User> findAll();


    User findUserById(long id);


    void delete(long id);

    User save(User user);


    void saveNewUser(User user);


    User update(
        User user,
        long id);


    public void deleteAll();

}
