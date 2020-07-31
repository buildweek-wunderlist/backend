package com.lambda.buildweek.wunderlist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.buildweek.wunderlist.models.ToDoList;
import com.lambda.buildweek.wunderlist.services.SecurityUserServiceImpl;
import com.lambda.buildweek.wunderlist.services.ToDoListService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ListController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoListService toDoListService;

    @MockBean
    private SecurityUserServiceImpl securityUserService;

    private List<ToDoList> toDoListList;

    @Before
    public void setUp() throws Exception
    {
        toDoListList = new ArrayList<>();

        ToDoList t1 = new ToDoList("test-list-1");
        ToDoList t2 = new ToDoList("test-list-2");
        ToDoList t3 = new ToDoList("test-list-3");
        ToDoList t4 = new ToDoList("test-list-4");

        toDoListList.add(t1);
        toDoListList.add(t2);
        toDoListList.add(t3);
        toDoListList.add(t4);


    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllLists() throws Exception
    {
        String apiURL = "/lists/my-lists";
        Mockito.when(toDoListService.findAll()).thenReturn(toDoListList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiURL).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(toDoListList);

        assertEquals(er, tr);
    }

    @Test
    public void createNewList()
    {
    }

    @Test
    public void updateList()
    {
    }

    @Test
    public void deleteList()
    {
    }
}