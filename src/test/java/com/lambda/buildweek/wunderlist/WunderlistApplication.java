package com.lambda.buildweek.wunderlist;

import com.lambda.buildweek.wunderlist.services.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WunderlistApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(WunderlistApplication.class,
            args);
    }

}
