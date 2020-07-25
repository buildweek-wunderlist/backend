package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.exceptions.ResourceNotFoundException;
import com.lambda.buildweek.wunderlist.models.Role;
import com.lambda.buildweek.wunderlist.models.User;
import com.lambda.buildweek.wunderlist.models.UserRoles;
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
    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);

    }

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

        newUser.getRoles()
            .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            newUser.getRoles()
                .add(new UserRoles(newUser, addRole));
        }

        return userrepos.save(newUser);
    }

    @Override
    public User update(
        User user,
        long id)
    {
        return null;
    }

    @Override
    public void deleteAll()
    {

    }
}
