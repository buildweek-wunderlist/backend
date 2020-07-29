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


        User u1 = new User("admin",
            "password");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        userService.save(u1);


        User u2 = new User("barnbarn",
            "password");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        userService.save(u2);
















    }

}
