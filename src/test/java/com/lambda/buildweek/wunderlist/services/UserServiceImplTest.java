package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.WunderlistApplication;
import com.lambda.buildweek.wunderlist.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WunderlistApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest
{
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {

        MockitoAnnotations.initMocks(this);

        List<User> myList = userService.findAll();

        for (User u : myList)
        {
            System.out.println(u.getUserid() + " " + u.getUsername());
        }

    }


    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void a_findAll()
    {
        assertEquals(2, userService.findAll().size());

    }

    @Test
    public void b_findUserById()
    {
        assertEquals("barnbarn-test", userService.findUserById(4).getUsername());
    }

    @Test
    public void c_delete()
    {
        userService.delete(3);
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void d_saveNewUser()
    {
    }

    @Test
    public void e_save()
    {
        User u1 = new User("jamie", "123908");
        User addUser = userService.save(u1);
        assertNotNull(addUser);
        assertEquals("jamie", addUser.getUsername());
    }

    @Test
    public void f_update()
    {
        User u2 = new User("sally", "1203-921093");
        User addUser = userService.save(u2);
        assertNotNull(addUser);
        User updated = new User();
        updated.setUsername("sally-2");
        User updatedUser = userService.update(updated, addUser.getUserid());
        assertNotNull(updatedUser);
        assertEquals("sally-2", updatedUser.getUsername());
    }

    @Test
    public void g_deleteAll()
    {
        userService.deleteAll();
        assertEquals(0, userService.findAll().size());
    }
}