package com.bootdo.activiti.utils;

import com.bootdo.common.utils.BDException;
import com.bootdo.common.utils.ShiroUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ActivitiUtils {
    /**
     * 根据taskId查找businessKey
     */
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;

    public String getBusinessKeyByTaskId(String taskId) {
        try {
            Task task = taskService
                    .createTaskQuery()
                    .taskId(taskId)
                    .singleResult();
            ProcessInstance pi = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .singleResult();
            return pi.getBusinessKey();
        } catch (Exception e) {
            throw new BDException("任务已经被处理");
        }
    }

    public Task getTaskByTaskId(String taskId) {
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return task;
    }

    // 给任务增加用户组
    public void addGroupToTask(Task task){
        taskService.addCandidateGroup(task.getId(), ShiroUtils.getUser().getCompanyId()+"@"+task.getTaskDefinitionKey());
    }

    // 过去用户的id
    public String getActivitiUserId(){
        return ShiroUtils.getUser().getCompanyId()+"@"+ShiroUtils.getUser().getUserId();
    }
}
