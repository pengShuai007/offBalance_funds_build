package com.bootdo.activiti.config;

/**
 * @author lichunguang
 */
public class ActivitiConstant {
    public static final String[] ACTIVITI_SALARY = new String[]{"salary", "salary"};
    //请假流程
    public static final String ACTIVITI_PROCESS_LEAVE = "process-leave";
    public static final String ACTIVITI_PROCESS_LEAVE_LEADER = "leader";
    public static final String ACTIVITI_PROCESS_LEAVE_DIRECT = "direct";
    public static final String ACTIVITI_PROCESS_LEAVE_HR = "hr";

    //节点类型
    public static final String ACTIVITY_TYPE_USER_TASK = "userTask";

    /**
     *工会资金流程
     */
    //流程标识processKey
    public static final String ACTIVITI_PROCESS_UNION = "process-inion-funds";
    //部门领导审核
    public static final String ACTIVITI_PROCESS_UNION_DEPARTMENT_REVIEW = "department_review";
    //主管领导审批
    public static final String ACTIVITI_PROCESS_UNION_SUPERVISOR_APPROVAL = "supervisor_approval";
    //财务审核
    public static final String ACTIVITI_PROCESS_UNION_FINANCE_REVIEW = "finance_review";
    //出纳确认
    public static final String ACTIVITI_PROCESS_UNION_CASHIER_CONFIRM = "cashier_confirm";

    public static final String ACTIVITI_PROCESS_UNION_END = "end";
}
