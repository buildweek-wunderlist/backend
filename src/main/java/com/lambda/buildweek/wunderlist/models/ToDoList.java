package com.lambda.buildweek.wunderlist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TODOLISTS")
public class ToDoList extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todolistid;

    @OneToMany(mappedBy = "todolist",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "todolists")
    private List<Todo> todos = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties("todolists")
    private Set<User> users = new HashSet<>();

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

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public ToDoList(
        Set<User> users)
    {
        this.users = users;
    }

    public ToDoList()
    {
    }
}
