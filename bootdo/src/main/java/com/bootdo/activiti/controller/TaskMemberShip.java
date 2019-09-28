package com.bootdo.activiti.controller;

import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
* @Description: 增加用户组和用户的关系
* @Param:
* @return:
* @Author: quxuan
* @Date: 2019/9/27
*/
@RequestMapping("activiti/memberShip")
@RestController
public class TaskMemberShip {

    @Autowired
    private IdentityService identityService;

    @ResponseBody
    @PostMapping("/add")
    public R add(String companyId, String groupCode, String userCode) {

        if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(groupCode) || StringUtils.isEmpty(userCode)){
            return  R.error(1,"companyId 和 groupCode 和 userCode 都不能为空 ");
        }

        // 用户id
        String userId = companyId+"@"+userCode;
        // 判断用户是否存在
        User querUser = identityService.createUserQuery().userId(userId).singleResult();
        if(null == querUser){
            return R.error(1,userId+" 用户不存在");
        }
        // 用户组id
        String groupId = companyId+"@"+groupCode;
        // 判断用户组是否存在
        Group queryGroup = identityService.createGroupQuery().groupId(groupId).singleResult();
        if(null == queryGroup){
            return R.error(2,userId+" 用户组不存在");
        }

        //创建关系
        identityService.createMembership(userId,groupId);

        //查询是否增加成功
        User userInGroup = identityService.createUserQuery().memberOfGroup(groupId).userId(userId).singleResult();
        if(null != userInGroup){
            Map result = new HashMap();
            result.put("code",0);
            result.put("message",userId +" input "+groupId +" success");
            return R.ok(result);
        }
        return R.error(1,userId +" input "+groupId +" fail");
    }

    @ResponseBody
    @PostMapping("/delete")
    public R delete(String companyId, String groupCode, String userCode) {

        if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(groupCode) || StringUtils.isEmpty(userCode)){
            return  R.error(1,"companyId 和 groupCode 和 userCode 都不能为空 ");
        }

        // 用户id
        String userId = companyId+"@"+userCode;
        // 判断用户是否存在
        User querUser = identityService.createUserQuery().userId(userId).singleResult();
        if(null == querUser){
            return R.error(1,userId+" 用户不存在");
        }
        // 用户组id
        String groupId = companyId+"@"+groupCode;
        // 判断用户组是否存在
        Group queryGroup = identityService.createGroupQuery().groupId(groupId).singleResult();
        if(null == queryGroup){
            return R.error(2,userId+" 用户组不存在");
        }

        //查询是否增加成功
        User userInGroup = identityService.createUserQuery().memberOfGroup(groupId).userId(userId).singleResult();
        if(null != userInGroup){
           identityService.deleteMembership(userId,groupId);
           Map result = new HashMap();
           result.put("code",0);
           result.put("message",userId +" delete "+groupId +" success");
           return R.ok();
        }
        return R.error(1,"删除用户与用户关系失败");
    }
}
