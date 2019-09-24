package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 公司信息管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:01:03
 */
public class CompanyMgtDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增主键
	private Integer id;
	//公司名称
	private String companyName;
	//公司级别,0:总公司；1:分公司
	private Integer companyLevel;
	//备注
	private String comments;
	//创建时间
	private Date createTime;
	//创建人
	private String creator;
	//更新时间
	private Date updateTime;
	//更新人
	private String updater;

	/**
	 * 设置：自增主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：自增主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：公司级别,0:总公司；1:分公司
	 */
	public void setCompanyLevel(Integer companyLevel) {
		this.companyLevel = companyLevel;
	}
	/**
	 * 获取：公司级别,0:总公司；1:分公司
	 */
	public Integer getCompanyLevel() {
		return companyLevel;
	}
	/**
	 * 设置：备注
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * 获取：备注
	 */
	public String getComments() {
		return comments;
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
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdater() {
		return updater;
	}
}
