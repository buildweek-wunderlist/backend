package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.exceptions.ResourceFoundException;
import com.lambda.buildweek.wunderlist.exceptions.ResourceNotFoundException;
import com.lambda.buildweek.wunderlist.models.Role;
import com.lambda.buildweek.wunderlist.repositories.RoleRepository;
import com.lambda.buildweek.wunderlist.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{

    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRespository userrepos;

    @Autowired
    private UserAuditing userAuditing;


    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        if (role.getUsers()
            .size() > 0)
        {
            throw new ResourceFoundException("User Roles are not updated through Role.");
        }

        return rolerepos.save(role);
    }

    @Override
    public Role findByName(String name)
    {
        return rolerepos.findByNameIgnoreCase(name);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        rolerepos.deleteAll();
    }

    @Transactional
    @Override
    public Role update(
        long id,
        Role role)
    {
        if (role.getName() == null)
        {
            throw new ResourceNotFoundException("No role name found to update!");
        }

        if (role.getUsers()
            .size() > 0)
        {
            throw new ResourceNotFoundException("User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        Role newRole = findRoleById(id); // see if id exists

        rolerepos.updateRoleName(userAuditing.getCurrentAuditor()
                .get(),
            id,
            role.getName());
        return findRoleById(id);
    }
}


