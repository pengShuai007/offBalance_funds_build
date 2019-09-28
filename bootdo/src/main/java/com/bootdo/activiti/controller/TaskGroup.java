package com.bootdo.activiti.controller;


import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequestMapping("activiti/taskGroup")
@RestController
public class TaskGroup {

    @Autowired
    private IdentityService identityService;

    /**
     * 添加用户组,也是候选人用户组
     * 用户组的生成规则。companyId+"@"+groupName
     * @param companyId
     * @param groupName
     * @return
     */
    @ResponseBody
    @PostMapping("/add")
    public R add(String companyId, String groupCode,String groupName) {
        if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(groupCode) || StringUtils.isEmpty(groupName)){
            return  R.error(1,"companyId 和 groupCode 和 groupName 都不能为空 ");
        }

        // 新的用户组id
        String groupId = companyId+"@"+groupCode;

        // 查询用户组是否存在
        Group queryGroup = identityService.createGroupQuery().groupId(groupId).singleResult();
        if(null != queryGroup){
            return  R.error(2,groupId +":用户组已经存在");
        }
        // 创建一个用户组
        Group group = identityService.newGroup(groupId);
        group.setName(groupName);
        group.setType("assignment");

        // 保存用户组
        identityService.saveGroup(group);

        return  R.error(0,groupId+":用户组新增成功！");
    }

    /**
    * @Description: 删除用户组，必须通过用户组id
    * @Param:
    * @return:
    * @Author: quxuan
    * @Date: 2019/9/27
    */
    @ResponseBody
    @PostMapping("/delete")
    public R delete(String companyId , String groupCode ) {
        if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(groupCode) ){
            return  R.error(1,"companyId 和 groupCode 都不能为空 ");
        }

        // 新的用户组id
        String groupId = companyId+"@"+groupCode;

        // 查询用户组是否存在
        Group queryGroup = identityService.createGroupQuery().groupId(groupId).singleResult();
        if(null != queryGroup){
            identityService.deleteGroup(groupId);
            return  R.error(0,groupId +":用户组删除成功");
        }else {
            return  R.error(2,groupId +":用户组不存在，不需要删除");
        }
    }


    /**
    * @Description: 查询公司下面的用户组
    * @Param:
    * @return:
    * @Author: quxuan
    * @Date: 2019/9/27
    */
    @ResponseBody
    @GetMapping("/getList")
    public R getList(String companyId) {

        List<Group> groupList =  identityService.createGroupQuery()
                .orderByGroupId()
                .list()
                .stream()
                .filter(group -> group.getId().contains(companyId+"@"))
                .collect(Collectors.toList());

        if(groupList.size() > 0){
            Map result = new HashMap();
            result.put("code",0);
            result.put("data",groupList);
            return R.ok(result);
        }else {
            return R.error(1,"该公司不存在用户组");
        }
    }
}
