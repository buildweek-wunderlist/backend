package com.lambda.buildweek.wunderlist;


import com.lambda.buildweek.wunderlist.models.*;
import com.lambda.buildweek.wunderlist.services.RoleService;
import com.lambda.buildweek.wunderlist.services.ToDoListService;
import com.lambda.buildweek.wunderlist.services.TodosService;
import com.lambda.buildweek.wunderlist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    TodosService todosService;

    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        toDoListService.deleteAll();
        todosService.deleteAll();


        Role r1 = new Role("admin");
        Role r2 = new Role("user");


        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        ToDoList u1List = new ToDoList();
        ToDoList u2List = new ToDoList();

        u1List.getTodos().add(new Todo("make coffee", "June 25", u1List));
        u2List.getTodos().add(new Todo("have a snack", "August 25", u2List));
        u2List.getTodos().add(new Todo("write a blog", "August 11", u2List));

        toDoListService.save(u1List);
        toDoListService.save(u2List);


        User u1 = new User("admin",
            "password");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getLists().add(new UserToDoList(u1, u1List));
        u1.getLists().add(new UserToDoList(u1, u2List));
        userService.save(u1);


        User u2 = new User("barnbarn",
            "password");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getLists().add(new UserToDoList(u2, u2List));

        userService.save(u2);

        User u3 = new User("jamie", "sullivan");

        userService.save(u3);












    }

}
