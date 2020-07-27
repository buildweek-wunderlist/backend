package com.lambda.buildweek.wunderlist.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "todolists")
public class ToDoList extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todolistid;

    @Column
    private String name;

    @OneToMany(mappedBy = "todolist",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "toDoList")
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "todolist",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "todolist", allowSetters = true)
    private Set<UserToDoList> users = new HashSet<>();

    public ToDoList()
    {
    }

    public ToDoList(String name)
    {
        this.name = name;
    }

    public ToDoList(
        String name,
        List<Todo> todos,
        Set<UserToDoList> users)
    {
        this.name = name;
        this.todos = todos;
        this.users = users;
    }

    public long getTodolistid()
    {
        return todolistid;
    }

    public void setTodolistid(long todolistid)
    {
        this.todolistid = todolistid;
    }

    public List<Todo> getTodos()
    {
        return todos;
    }

    public void setTodos(List<Todo> todos)
    {
        this.todos = todos;
    }

    public Set<UserToDoList> getUsers()
    {
        return users;
    }

    public void setUsers(Set<UserToDoList> users)
    {
        this.users = users;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
