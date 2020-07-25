package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.models.Role;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public interface RoleService
{

    List<Role> findAll();

    Role findRoleById(long id);

    Role save(Role role);

    Role findByName(String name);

    public void deleteAll();

    Role update(
        long id,
        Role role);
}