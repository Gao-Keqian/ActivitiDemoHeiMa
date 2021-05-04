package com.example.activitidemoheima;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActivitidemoheimaApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    public void createActivitiTable(){
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
}
