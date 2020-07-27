package com.lambda.buildweek.wunderlist.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "usertodolist")
public class UserToDoList extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "lists", allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "todolistid")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private ToDoList todolist;


    public UserToDoList()
    {
    }

    public UserToDoList(
        User user,
        ToDoList todolist)
    {
        this.user = user;
        this.todolist = todolist;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public ToDoList getTodolist()
    {
        return todolist;
    }

    public void setTodolist(ToDoList todolist)
    {
        this.todolist = todolist;
    }
}

