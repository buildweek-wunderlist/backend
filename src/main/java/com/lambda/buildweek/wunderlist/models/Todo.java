package com.lambda.buildweek.wunderlist.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todos")
public class Todo extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,
        unique = true)
    private long todoid;

    @Column(nullable = false)
    String description;

    @Column
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "todolistid"
        , nullable = false)
    @JsonIgnoreProperties(value = "todos", allowSetters = true)
    private ToDoList todolist;

    public Todo()
    {
    }

    public Todo( ToDoList todolist,
                 String description
    )
    {
        this.description = description;
        this.todolist = todolist;
        this.completed = false;
    }

    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public ToDoList getToDoList()
    {
        return todolist;
    }

    public void setToDoList(ToDoList todolist)
    {
        this.todolist = todolist;
    }
}
