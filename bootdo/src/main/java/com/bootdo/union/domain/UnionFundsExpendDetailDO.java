package com.bootdo.union.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 工会支出明细表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
public class UnionFundsExpendDetailDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增主键
	private Long id;
	//支出明细id
	private String expentId;
	//支出记录id
	private String recordId;
	//分公司名称
	private String branchOffice;
	//金额
	private BigDecimal amount;
	//入账时间
	private Date acountDate;
	//项目名称
	private String itemName;
	//数量
	private Integer number;
	//创建时间
	private Date createTime;

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
	 * 设置：支出明细id
	 */
	public void setExpentId(String expentId) {
		this.expentId = expentId;
	}
	/**
	 * 获取：支出明细id
	 */
	public String getExpentId() {
		return expentId;
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
	 * 设置：分公司名称
	 */
	public void setBranchOffice(String branchOffice) {
		this.branchOffice = branchOffice;
	}
	/**
	 * 获取：分公司名称
	 */
	public String getBranchOffice() {
		return branchOffice;
	}
	/**
	 * 设置：金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：入账时间
	 */
	public void setAcountDate(Date acountDate) {
		this.acountDate = acountDate;
	}
	/**
	 * 获取：入账时间
	 */
	public Date getAcountDate() {
		return acountDate;
	}
	/**
	 * 设置：项目名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * 设置：数量
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：数量
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
