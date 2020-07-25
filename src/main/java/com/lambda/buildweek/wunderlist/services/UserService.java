package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.models.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();


    User findUserById(long id);

    User findByName(String name);


    void delete(long id);


    User save(User user);


    User update(
        User user,
        long id);


    public void deleteAll();

}
