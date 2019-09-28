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

@RequestMapping("activiti/taskUser")
@RestController
public class TaskUser {
    @Autowired
    private IdentityService identityService;

    /**
     * @Description: 增加用户的
     * @Param:
     * @return:
     * @Author: quxuan
     * @Date: 2019/9/27
     */
    @ResponseBody
    @PostMapping("/add")
    public R add(String companyId, String userCode, String userName) {

        if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(userCode) || StringUtils.isEmpty(userName)){
            return  R.error(1,"companyId 和 userCode 和 userName 都不能为空 ");
        }

        // 新的用户组id
        String userId = companyId+"@"+userCode;

        // 查询用户组是否存在
        User queryUser = identityService.createUserQuery().userId(userId).singleResult();
        if(null != queryUser){
            return  R.error(2,userId +":用户已经存在");
        }

        // 创建一个用户组
        User user = identityService.newUser(userId);
        user.setLastName(userName);

        // 保存用户组
        identityService.saveUser(user);

        User checkUser = identityService.createUserQuery().userId(userId).singleResult();
        return  R.error(0,userId+":用户新增成功！");
    }

    /**
     * @Description: 增加用户的
     * @Param:
     * @return:
     * @Author: quxuan
     * @Date: 2019/9/27
     */
    @ResponseBody
    @PostMapping("/delete")
    public R delete(String companyId, String userCode) {

        if(StringUtils.isEmpty(companyId) || StringUtils.isEmpty(userCode)){
            return  R.error(1,"companyId 和 userCode 不能为空 ");
        }

        // 新的用户组id
        String userId = companyId+"@"+userCode;

        // 查询用户组是否存在
        User queryUser = identityService.createUserQuery().userId(userId).singleResult();
        if(null != queryUser){
            identityService.deleteUser(userId);
            return  R.error(0,userId +":用户删除成功");
        }else {
            return  R.error(2,userId +":用户不存在不需要删除");
        }
    }
}
