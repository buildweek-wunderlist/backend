package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.WunderlistApplication;
import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WunderlistApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ToDoListServiceImplTest
{

    @Autowired
    private ToDoListService toDoListService;

    @Autowired
    private UserService userService;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        List<ToDoList> myList = toDoListService.findAll();
        for (ToDoList tl : myList)
        {
            System.out.println(tl.getTodolistid() + " " + tl.getTitle());

        }
    }

    @After
    public void tearDown() throws Exception
    {
    }


    @Test
    public void a_findAll()
    {
        assertEquals(2, toDoListService.findAll().size());
    }

    @Test
    public void b_findById()
    {
        assertEquals("admin list-test", toDoListService.findById(5).getTitle());
    }

    @Test
    public void c_save()
    {
        User u2 = new User("test-jamie", "12930821");
        u2 = userService.save(u2);

        ToDoList t1 = new ToDoList("jamie's list - test");
        t1.setUser(u2);

        ToDoList addList = toDoListService.save(t1);
        assertNotNull(addList);
        assertEquals("jamie's list - test", addList.getTitle());

    }

    @Test
    public void d_update()
    {
        ToDoList updateList = new ToDoList("changed the title!");
        ToDoList newList = toDoListService.update(updateList, 6);
        assertNotNull(newList);
        assertEquals("changed the title!", newList.getTitle());
    }

    @Test
    public void e_delete()
    {
        toDoListService.delete(6);
        assertEquals(2, toDoListService.findAll().size());

    }

    @Test
    public void f_deleteAll()
    {
        toDoListService.deleteAll();
        assertEquals(0, toDoListService.findAll().size());

    }
}