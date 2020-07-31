package com.lambda.buildweek.wunderlist.services;

import com.lambda.buildweek.wunderlist.WunderlistApplication;
import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.models.Todo;
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
public class ToDoServiceImplTest
{

    @Autowired
    private TodosService todosService;

    @Autowired
    private ToDoListService toDoListService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        List<Todo> myList = todosService.findAll();

        for (Todo t : myList)
        {
            System.out.println(t.getTodoid() + " " + t.getDescription());
        }

    }

    @After
    public void tearDown() throws Exception
    {
    }



    @Test
    public void a_findAll()
    {
        assertEquals(2, todosService.findAll().size());
    }

    @Test
    public void b_delete()
    {
        todosService.delete(7);
        assertEquals(1, todosService.findAll().size());
    }

    @Test
    public void c_save()
    {
        User u1 = new User("jamie", "12312");
        u1 = userService.save(u1);

        ToDoList td1 = new ToDoList();
        td1.setTitle("test-list");
        td1.setUser(u1);
        td1 = toDoListService.save(td1);

        Todo t1 = new Todo();
        t1.setDescription("finish writing tests!");

        Todo addTodo = todosService.save(t1, td1.getTodolistid());
        assertNotNull(addTodo);
        assertEquals("finish writing tests!", addTodo.getDescription());
    }

    @Test
    public void d_markComplete()
    {
        User u2 = new User("bobby", "12312");
        u2 = userService.save(u2);


        ToDoList td1 = new ToDoList();
        td1.setTitle("another-test-list");
        td1.setUser(u2);
        td1 = toDoListService.save(td1);


        Todo test = new Todo();
        test.setDescription("complete this task");
        test = todosService.save(test, td1.getTodolistid());
        test = todosService.markComplete(test.getTodoid());

        assertTrue(test.isCompleted());
    }

    @Test
    public void e_update()
    {
        User u3 = new User("sally", "12312");
        u3 = userService.save(u3);

        ToDoList td1 = new ToDoList();
        td1.setTitle("test-list-test");
        td1.setUser(u3);
        td1 = toDoListService.save(td1);

        Todo t1 = new Todo();
        t1.setDescription("finish writing tests!");

        Todo updateTodo = todosService.save(t1, td1.getTodolistid());
        updateTodo.setDescription("now updated the description!");
        Todo updatedTodo = todosService.update(updateTodo, updateTodo.getTodoid());

        assertEquals("now updated the description!", updatedTodo.getDescription());

    }


    @Test
    public void f_deleteAll()
    {
        todosService.deleteAll();
        assertEquals(0, todosService.findAll().size());
    }
}