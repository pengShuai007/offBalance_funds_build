package com.bootdo.system.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDO implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Long userId;
    // 用户名
    private String username;
    // 用户真实姓名
    private String name;
    // 密码
    private String password;
    // 部门
    private Long deptId;
    private String deptName;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 状态 0:禁用，1:正常
    private Integer status;
    // 创建用户id
    private Long userIdCreate;
    // 创建时间
    private Date gmtCreate;
    // 修改时间
    private Date gmtModified;
    //角色
    private List<Long> roleIds;

    //角色标识
    private List<String> roleSigns;
    //性别
    private Long sex;
    //出身日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    //图片ID
    private Long picId;
    //现居住地
    private String liveAddress;
    //爱好
    private String hobby;
    //省份
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;
    //行政组织编码
    private String adminOrgCode;
    //行政组织
    private String adminOrgName;
    //党组织编码
    private String partyOrgCode;
    //党组织名称
    private String partyOrgName;
    //团组织编码
    private String communistOrgCode;
    //团组织名称
    private String communistOrgName;
    //工会组织编码
    private String tradeUnionCode;
    // 工会组织名称
    private String tradeUnionName;
    //职务名称
    private String postName;
    //办公室电话
    private String officeTel;
    //直系领导ID
    private Long directLeaderId;
    //直系领导
    private String directLeaderId;
    //直系领导
    private String directLeaderName;
    //创建人
    private String creator;
    //更新人
    private String updater;
    //公司ID
    private int companyId;
    //协同系统用户ID
    private Long XtUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<String> getRoleSigns() {
        return roleSigns;
    }

    public void setRoleSigns(List<String> roleSigns) {
        this.roleSigns = roleSigns;
    }

    public String getAdminOrgCode() {
        return adminOrgCode;
    }

    public void setAdminOrgCode(String adminOrgCode) {
        this.adminOrgCode = adminOrgCode;
    }

    public String getAdminOrgName() {
        return adminOrgName;
    }

    public void setAdminOrgName(String adminOrgName) {
        this.adminOrgName = adminOrgName;
    }

    public String getPartyOrgCode() {
        return partyOrgCode;
    }

    public void setPartyOrgCode(String partyOrgCode) {
        this.partyOrgCode = partyOrgCode;
    }

    public String getPartyOrgName() {
        return partyOrgName;
    }

    public void setPartyOrgName(String partyOrgName) {
        this.partyOrgName = partyOrgName;
    }

    public String getCommunistOrgCode() {
        return communistOrgCode;
    }

    public void setCommunistOrgCode(String communistOrgCode) {
        this.communistOrgCode = communistOrgCode;
    }

    public String getCommunistOrgName() {
        return communistOrgName;
    }

    public void setCommunistOrgName(String communistOrgName) {
        this.communistOrgName = communistOrgName;
    }

    public String getTradeUnionCode() {
        return tradeUnionCode;
    }

    public void setTradeUnionCode(String tradeUnionCode) {
        this.tradeUnionCode = tradeUnionCode;
    }

    public String getTradeUnionName() {
        return tradeUnionName;
    }

    public void setTradeUnionName(String tradeUnionName) {
        this.tradeUnionName = tradeUnionName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public Long getDirectLeaderId() {
        return directLeaderId;
    }

    public void setDirectLeaderId(Long directLeaderId) {
        this.directLeaderId = directLeaderId;
    }

    public String getDirectLeaderName() {
        return directLeaderName;
    }

    public void setDirectLeaderName(String directLeaderName) {
        this.directLeaderName = directLeaderName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDirectLeaderId() {
        return directLeaderId;
    }

    public void setDirectLeaderId(String directLeaderId) {
        this.directLeaderId = directLeaderId;
    }

    public Long getXtUserId() {
        return XtUserId;
    }

    public void setXtUserId(Long xtUserId) {
        XtUserId = xtUserId;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", roleIds=" + roleIds +
                ", sex=" + sex +
                ", birth=" + birth +
                ", picId=" + picId +
                ", liveAddress='" + liveAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", adminOrgCode='" + adminOrgCode + '\'' +
                ", adminOrgName='" + adminOrgName + '\'' +
                ", partyOrgCode='" + partyOrgCode + '\'' +
                ", partyOrgName='" + partyOrgName + '\'' +
                ", communistOrgCode='" + communistOrgCode + '\'' +
                ", communistOrgName='" + communistOrgName + '\'' +
                ", tradeUnionCode='" + tradeUnionCode + '\'' +
                ", tradeUnionName='" + tradeUnionName + '\'' +
                ", postName='" + postName + '\'' +
                ", officeTel='" + officeTel + '\'' +
                ", directLeaderId='" + directLeaderId + '\'' +
                ", directLeaderName='" + directLeaderName + '\'' +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                ", companyId='" + companyId + '\'' +
                ", XtUserId='" + XtUserId + '\'' +
                '}';
    }
}
