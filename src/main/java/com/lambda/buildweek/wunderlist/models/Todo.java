package com.lambda.buildweek.wunderlist.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "todos")
public class Todo extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    String description;

    @Column
    String month;

    @Column
    int day;

    @Column
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "todolistid", nullable = false)
    @JsonIgnoreProperties(value = "todos")
    private ToDoList todolist;

    public Todo()
    {
    }

    public Todo(
        String description,
        String month,
        int day)
    {
        this.description = description;
        this.month = month;
        this.day = day;
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

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
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
