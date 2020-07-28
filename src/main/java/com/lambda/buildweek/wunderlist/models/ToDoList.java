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
    private String title;

    @OneToMany(mappedBy = "todolist",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "toDoList")
    private List<Todo> todos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userid",
    nullable = false)
    private User user;

    public ToDoList()
    {
    }

    public ToDoList(String title)
    {
        this.title = title;
    }

    public ToDoList(
        String title,
        User user)
    {
        this.title = title;
        this.user = user;
    }

    public long getTodolistid()
    {
        return todolistid;
    }

    public void setTodolistid(long todolistid)
    {
        this.todolistid = todolistid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<Todo> getTodos()
    {
        return todos;
    }

    public void setTodos(List<Todo> todos)
    {
        this.todos = todos;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
