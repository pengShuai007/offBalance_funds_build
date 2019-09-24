package com.bootdo.union.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 工会收入记录表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
public class UnionFundsIncomeRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//收入记录的id
	private String recordId;
	//收入公司id
	private String inCompanyId;
	//收入公司名称
	private String inCompanyName;
	//支出公司id
	private String outCompanyId;
	//支出公司名称
	private String outCompanyName;
	//收入总金额，两位小数
	private BigDecimal amount;
	//创建人
	private String creator;
	//创建时间
	private Date createTime;
	//修改人
	private String modifier;
	//修改时间
	private Date modifyTime;

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
	 * 设置：收入记录的id
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取：收入记录的id
	 */
	public String getRecordId() {
		return recordId;
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
	 * 设置：收入总金额，两位小数
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：收入总金额，两位小数
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreator() {
		return creator;
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
	/**
	 * 设置：修改人
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * 获取：修改人
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
}
