package com.bootdo.union.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpendRecordVO {
    //自增主键
    private Long id;
    //支出记录id
    private String recordId;
    //支出公司id
    private String outCompanyId;
    //支出公司名称
    private String outCompanyName;
    //收入公司id
    private String inCompanyId;
    //收入公司名称
    private String inCompanyName;
    //申请人
    private String applyPerson;
    //申请部门
    private String applyDepartment;
    //申请时间
    private Date applyTime;
    //支出类型
    private String type;
    //支出事由
    private String reason;
    //支出金额
    private BigDecimal allAmount;
    //出账日期
    private Date expenTime;
    //付款方式
    private String paymentType;
    //活动类型
    private String activityType;
    //活动说明
    private String activityDirection;
    //申报单位
    private String activityUnit;
    //活动开始时间
    private Date activityTime;
    //活动参与人数
    private Integer activityPersonNum;
    //备注
    private String remarks;
    //部门领导审核意见
    private String departmentReview;
    //主管领导审核意见
    private String supervisorApproval;
    //财务审核
    private String financeReview;
    //出纳确认
    private String cashierConfirm;
    //工作流状态
    private String activitiStatus;

    //创建时间
    private Date createTime;
    //修改人
    private String modifier;
    //修改时间
    private Date modifyTime;
    //扎帐记录id
    private String settleAccountsId;

    //流程id
    private String processInstanceId;

    private  List<UnionFundsExpendDetailDO> detailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getOutCompanyId() {
        return outCompanyId;
    }

    public void setOutCompanyId(String outCompanyId) {
        this.outCompanyId = outCompanyId;
    }

    public String getOutCompanyName() {
        return outCompanyName;
    }

    public void setOutCompanyName(String outCompanyName) {
        this.outCompanyName = outCompanyName;
    }

    public String getInCompanyId() {
        return inCompanyId;
    }

    public void setInCompanyId(String inCompanyId) {
        this.inCompanyId = inCompanyId;
    }

    public String getInCompanyName() {
        return inCompanyName;
    }

    public void setInCompanyName(String inCompanyName) {
        this.inCompanyName = inCompanyName;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    public String getApplyDepartment() {
        return applyDepartment;
    }

    public void setApplyDepartment(String applyDepartment) {
        this.applyDepartment = applyDepartment;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(BigDecimal allAmount) {
        this.allAmount = allAmount;
    }

    public Date getExpenTime() {
        return expenTime;
    }

    public void setExpenTime(Date expenTime) {
        this.expenTime = expenTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityDirection() {
        return activityDirection;
    }

    public void setActivityDirection(String activityDirection) {
        this.activityDirection = activityDirection;
    }

    public String getActivityUnit() {
        return activityUnit;
    }

    public void setActivityUnit(String activityUnit) {
        this.activityUnit = activityUnit;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getActivityPersonNum() {
        return activityPersonNum;
    }

    public void setActivityPersonNum(Integer activityPersonNum) {
        this.activityPersonNum = activityPersonNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDepartmentReview() {
        return departmentReview;
    }

    public void setDepartmentReview(String departmentReview) {
        this.departmentReview = departmentReview;
    }

    public String getSupervisorApproval() {
        return supervisorApproval;
    }

    public void setSupervisorApproval(String supervisorApproval) {
        this.supervisorApproval = supervisorApproval;
    }

    public String getFinanceReview() {
        return financeReview;
    }

    public void setFinanceReview(String financeReview) {
        this.financeReview = financeReview;
    }

    public String getCashierConfirm() {
        return cashierConfirm;
    }

    public void setCashierConfirm(String cashierConfirm) {
        this.cashierConfirm = cashierConfirm;
    }

    public String getActivitiStatus() {
        return activitiStatus;
    }

    public void setActivitiStatus(String activitiStatus) {
        this.activitiStatus = activitiStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSettleAccountsId() {
        return settleAccountsId;
    }

    public void setSettleAccountsId(String settleAccountsId) {
        this.settleAccountsId = settleAccountsId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<UnionFundsExpendDetailDO> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<UnionFundsExpendDetailDO> detailList) {
        this.detailList = detailList;
    }
}
