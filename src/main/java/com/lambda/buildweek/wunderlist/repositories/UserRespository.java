package com.lambda.buildweek.wunderlist.repositories;

import com.lambda.buildweek.wunderlist.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRespository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
