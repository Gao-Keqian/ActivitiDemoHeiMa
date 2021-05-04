package com.example.activitidemoheima;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

public class ActivitiDemo {
    @Test
    public void testDeployment(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .disableSchemaValidation()
                .name("出差申请")
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .category("bpmn")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void selectProcess(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Deployment> bpmn = repositoryService.createDeploymentQuery()
                .deploymentCategory("bpmn")
                .list();
        for (Deployment deployment : bpmn) {
            System.out.println(deployment.getCategory());
        }
    }


    @Test
    public void startProcess(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("myEvection");
        System.out.println("Id"+myEvection.getProcessDefinitionId()) ;
        System.out.println(myEvection.getId());
        System.out.println(myEvection.getActivityId());
    }


    @Test
    public void listAllTask(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee("jerry")
                .list();
        for (Task task: list) {
            System.out.println(task.getId());
            System.out.println( task.getProcessInstanceId());
                System.out.println(task.getAssignee());
                System.out.println(task.getName());
            }
        }
    @Test
    public void completeTask(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.complete("37502");
    }

    @Test
    public void queryProcessDefinition(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> myEvections = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition myEvection:myEvections) {
            System.out.println(myEvection.getId());
            System.out.println(myEvection.getName());
            System.out.println(myEvection.getKey());
            System.out.println(myEvection.getVersion());
            System.out.println(myEvection.getDeploymentId());
        }

    }
    @Test
    public void deleteProcess(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("47501");
    }

    @Test
    public void getDeployment(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition myEvection = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();
        String deploymentId = myEvection.getDeploymentId();
        InputStream png = repositoryService.getResourceAsStream(deploymentId, myEvection.getResourceName());
        InputStream diagram = repositoryService.getResourceAsStream(deploymentId, myEvection.getDiagramResourceName());
    }

    @Test
    public void selectHistory(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.processInstanceId("10001")
                .orderByHistoricActivityInstanceEndTime()
                .desc()
                .list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println(historicActivityInstance.getActivityId());
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getAssignee());
        }
    }
}
