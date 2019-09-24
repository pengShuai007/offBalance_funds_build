package com.bootdo.union.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 工会支出记录表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
public class UnionFundsExpendRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	//支出类型
	private String type;
	//支出事由
	private String reason;
	//支出金额
	private BigDecimal amount;
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

	/**
	 * 设置：自增主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：自增主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：支出记录id
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取：支出记录id
	 */
	public String getRecordId() {
		return recordId;
	}
	/**
	 * 设置：支出公司id
	 */
	public void setOutCompanyId(String outCompanyId) {
		this.outCompanyId = outCompanyId;
	}
	/**
	 * 获取：支出公司id
	 */
	public String getOutCompanyId() {
		return outCompanyId;
	}
	/**
	 * 设置：支出公司名称
	 */
	public void setOutCompanyName(String outCompanyName) {
		this.outCompanyName = outCompanyName;
	}
	/**
	 * 获取：支出公司名称
	 */
	public String getOutCompanyName() {
		return outCompanyName;
	}
	/**
	 * 设置：收入公司id
	 */
	public void setInCompanyId(String inCompanyId) {
		this.inCompanyId = inCompanyId;
	}
	/**
	 * 获取：收入公司id
	 */
	public String getInCompanyId() {
		return inCompanyId;
	}
	/**
	 * 设置：收入公司名称
	 */
	public void setInCompanyName(String inCompanyName) {
		this.inCompanyName = inCompanyName;
	}
	/**
	 * 获取：收入公司名称
	 */
	public String getInCompanyName() {
		return inCompanyName;
	}
	/**
	 * 设置：申请人
	 */
	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	/**
	 * 获取：申请人
	 */
	public String getApplyPerson() {
		return applyPerson;
	}
	/**
	 * 设置：申请部门
	 */
	public void setApplyDepartment(String applyDepartment) {
		this.applyDepartment = applyDepartment;
	}
	/**
	 * 获取：申请部门
	 */
	public String getApplyDepartment() {
		return applyDepartment;
	}
	/**
	 * 设置：支出类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：支出类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：支出事由
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：支出事由
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：支出金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：支出金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：出账日期
	 */
	public void setExpenTime(Date expenTime) {
		this.expenTime = expenTime;
	}
	/**
	 * 获取：出账日期
	 */
	public Date getExpenTime() {
		return expenTime;
	}
	/**
	 * 设置：付款方式
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * 获取：付款方式
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * 设置：活动类型
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * 获取：活动类型
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * 设置：活动说明
	 */
	public void setActivityDirection(String activityDirection) {
		this.activityDirection = activityDirection;
	}
	/**
	 * 获取：活动说明
	 */
	public String getActivityDirection() {
		return activityDirection;
	}
	/**
	 * 设置：申报单位
	 */
	public void setActivityUnit(String activityUnit) {
		this.activityUnit = activityUnit;
	}
	/**
	 * 获取：申报单位
	 */
	public String getActivityUnit() {
		return activityUnit;
	}
	/**
	 * 设置：活动开始时间
	 */
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}
	/**
	 * 获取：活动开始时间
	 */
	public Date getActivityTime() {
		return activityTime;
	}
	/**
	 * 设置：活动参与人数
	 */
	public void setActivityPersonNum(Integer activityPersonNum) {
		this.activityPersonNum = activityPersonNum;
	}
	/**
	 * 获取：活动参与人数
	 */
	public Integer getActivityPersonNum() {
		return activityPersonNum;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
