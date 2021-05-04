package com.example.activitidemoheima;

import com.example.activitidemoheima.pojo.Participant;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitiTest {

    @Test
    public void deploy(){
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .disableSchemaValidation()
                .category("bpmn")
                .name("流程审批")
                .addClasspathResource("bpmn/BdapDiagram.bpmn")
                .deploy();

    }

    @Test
    public void startProcess(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> map=new HashMap<>();
        Participant participant=new Participant();
        participant.setApplicant("Tom2");
        participant.setDepManager("zhangsan");
        participant.setDptManager("zhangsan");

        participant.setTechDeveloper("lisi");
        participant.setTechManager("wangwu");
        map.put("Participant",participant);
        runtimeService.startProcessInstanceByKey("bdap3",map);
    }

    @Test
    public void CompleteProcess01(){
       ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        Task task = taskQuery.processDefinitionKey("bdap3")
                .taskAssignee("Tom2")
                .singleResult();
//        62501


        taskService.complete(task.getId());
    }

    @Test
    public void CompleteProcess02(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.processDefinitionKey("bdap2")
                .taskAssignee("zhangsan")
                .list();
//        62501
        Map<String,Object> map=new HashMap<>();

        Participant participant=new Participant();

        participant.setDptManagerCheck("false");
        map.put("Participant",participant);

        taskService.complete(list.get(0).getId(),map);
    }
}
