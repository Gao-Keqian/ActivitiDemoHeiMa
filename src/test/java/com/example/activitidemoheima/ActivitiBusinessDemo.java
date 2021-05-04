package com.example.activitidemoheima;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ActivitiBusinessDemo {
    @Test
    public void addBusinessKey(){
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("myEvection", "1001");
        System.out.println(myEvection.getBusinessKey());
    }


    @Test
    public void testDeployment(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .disableSchemaValidation()
                .name("出差申请")
                .addClasspathResource("bpmn/myEvection1.bpmn")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void startProcess(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> map=new HashMap<>();
        map.put("assignee0","zhangsan");
        map.put("assignee1","lisi");
        map.put("assignee2","wangwu");
        ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("myEvection1",map);
        System.out.println("Id"+myEvection.getProcessDefinitionId()) ;
        System.out.println(myEvection.getId());
        System.out.println(myEvection.getActivityId());
    }
}
