package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.exceptions.ResourceNotFoundException;
import com.lambda.buildweek.wunderlist.models.*;
import com.lambda.buildweek.wunderlist.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRespository userrepos;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ToDoListService toDoListService;

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();

        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id)
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found"));
    }

 /*   @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }*/

    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);

    }


    @Transactional
    @Override
    public void saveNewUser(User user)
    {
        User newUser = new User();

        newUser.setUserid(user.getUserid());

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());


        Role newRole = roleService.findByName("user");
        newUser.getRoles().add(new UserRoles(newUser, newRole));


         userrepos.save(newUser);
    }

  /*  @Transactional
    @Override
    public void addNewUserToDoList(long id, String title)
    {
        ToDoList newList = new ToDoList();
        newList = toDoListService.save(newList);

        User user = userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find user!"));

        //user.getLists().add(new UserToDoList(user, newList));
        userrepos.save(user);
    }*/


    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException ("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername()
            .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());

        newUser.getRoles().clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            newUser.getRoles()
                .add(new UserRoles(newUser, addRole));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
        User user,
        long id)
    {

        User currentUser = userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User " + id + "not found!"));


        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername()
                .toLowerCase());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }


        if (user.getRoles()
            .size() > 0)
        {
            currentUser.getRoles().clear();
            for (UserRoles ur : user.getRoles())
                {
                    Role addRole = roleService.findRoleById(ur.getRole()
                        .getRoleid());

                    currentUser.getRoles()
                        .add(new UserRoles(currentUser, addRole));
                }
        }


        return userrepos.save(currentUser);
    }

    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }
}
