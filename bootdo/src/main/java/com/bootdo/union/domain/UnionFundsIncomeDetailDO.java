package com.bootdo.union.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 工会收入明细表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
public class UnionFundsIncomeDetailDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//收入管理id
	private String incomeId;
	//收入记录id
	private String recordId;
	//收入类型
	private String type;
	//收入描述
	private String description;
	//登记日期
	private Date enterDate;
	//入账日期
	private Date acountDate;
	//收入金额
	private BigDecimal amount;
	//凭证
	private String voucher;
	//备注
	private String remarks;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：收入管理id
	 */
	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}
	/**
	 * 获取：收入管理id
	 */
	public String getIncomeId() {
		return incomeId;
	}
	/**
	 * 设置：收入记录id
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取：收入记录id
	 */
	public String getRecordId() {
		return recordId;
	}
	/**
	 * 设置：收入类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：收入类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：收入描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：收入描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：登记日期
	 */
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	/**
	 * 获取：登记日期
	 */
	public Date getEnterDate() {
		return enterDate;
	}
	/**
	 * 设置：入账日期
	 */
	public void setAcountDate(Date acountDate) {
		this.acountDate = acountDate;
	}
	/**
	 * 获取：入账日期
	 */
	public Date getAcountDate() {
		return acountDate;
	}
	/**
	 * 设置：收入金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：收入金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：凭证
	 */
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	/**
	 * 获取：凭证
	 */
	public String getVoucher() {
		return voucher;
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
