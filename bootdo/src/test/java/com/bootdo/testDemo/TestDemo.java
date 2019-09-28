package com.bootdo.testDemo;

import com.bootdo.activiti.dao.CustomActivitiesDao;
import com.bootdo.activiti.domain.CustomActivitiesDO;
import com.bootdo.activiti.service.CustomActivitiesService;
import com.bootdo.activiti.service.ProcessService;
import net.bytebuddy.asm.Advice;
import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private RepositoryService repositoryService;
//
//    @Autowired
//    private ProcessService processService;
//
//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private HistoryService historyService;
//
//    @Autowired
//    private FormService formService;
//
//    @Autowired
//    private IdentityService identityService;
//
//    @Autowired
//    private ManagementService managementService;
//
//    @Autowired
//    private CustomActivitiesService customActivitiesService;
//
//    @Test
//    public void identityServiceTest(){
//        //创建一个用户对对象
//        User user = identityService.newUser("quxuan");
//        user.setFirstName("qu");
//        user.setLastName("xuan");
//        user.setEmail("quxuan@kyee.com.cn");
//        // 保存用户到数据库
//        identityService.saveUser(user);
//        //验证用户是否保存整个
//        User queryUser = identityService.createUserQuery().userId("quxuan").singleResult();
//        //删除用户
//        //identityService.deleteUser("quxuan");
//
//        Group group = identityService.newGroup("dept");
//        group.setName("部门领导");
//        group.setType("assignment"); //assignment 普通角色 security-role 安全角色
//        identityService.saveGroup(group);
//
//
//    }
//
//    @Test
//    public void testUserAndGroupMemebership(){
//        // 创建用户组
//        Group group = identityService.newGroup("deptLeader");
//        group.setName("部门领导");
//        group.setType("assignment"); //assignment 普通角色 security-role 安全角色
//        identityService.saveGroup(group);
//
//        //创建用户对象
//        User user = identityService.newUser("quxuan");
//        user.setFirstName("qu");
//        user.setLastName("xuan");
//        user.setEmail("quxuan@kyee.com.cn");
//        identityService.saveUser(user);
//
//        //把用户加到用户组中
//        identityService.createMembership("quxuan","deptLeader");
//
//        //查询属于用户组的用户
//        User userGroup = identityService.createUserQuery().memberOfGroup("deptLeader").singleResult();
//
//        //查询用户所在的用户组用户组
//        Group group1 = identityService.createGroupQuery().groupMember("quxuan").singleResult();
//    }
//
//
//    @After
//    public void afterInvokeTestMethod(){
//        identityService.deleteMembership("quxuan","deptLeader");
//        identityService.deleteUser("quxuan");
//        identityService.deleteGroup("deptLeader");
//    }
//    @Test
//    @Deployment(resources = {"D://jjj"})
//    public void testUserTeskWithGroupCantainsTwoUser(){
//        //创建第一个用户对对象
//        User user = identityService.newUser("quxuan1");
//        user.setFirstName("qu");
//        user.setLastName("xuan1");
//        user.setEmail("quxuan@kyee.com.cn");
//        identityService.saveUser(user);
//        //创建第2个用户对对象
//        User user2 = identityService.newUser("quxuan2");
//        user.setFirstName("qu");
//        user.setLastName("xuan2");
//        user.setEmail("quxuan@kyee.com.cn");
//        identityService.saveUser(user2);
//
//        //创建一个分组
//        Group group = identityService.newGroup("deptLeader");
//        group.setName("部门领导");
//        group.setType("assignment"); //assignment 普通角色 security-role 安全角色
//        identityService.saveGroup(group);
//
//        identityService.createMembership("quxuan1","deptLeader");
//        identityService.createMembership("quxuan2","deptLeader");
//
//        // 启动一个流程
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("userAndGroupInUserTask");
//        //quxuan1的任务
//        Task quxuan1Task = taskService.createTaskQuery().taskCandidateUser("quxuan1").singleResult();
//        //quxuan2的任务
//        Task quxuan2Task = taskService.createTaskQuery().taskCandidateUser("quxuan2").singleResult();
//
//        // quxuan1 先签收任务
//        taskService.claim(quxuan1Task.getId(),"quxuan1");
//
//        //再次查询quxuan2用户的任务时候，就已经不存在了
//        Task quxuan2TaskTwo = taskService.createTaskQuery().taskCandidateUser("quxuan2").singleResult();
//
//        // 办理任务
//        taskService.complete(quxuan1Task.getId());
//    }
}
