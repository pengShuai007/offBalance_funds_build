package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 团组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:58:54
 */
public class CommunistOrgMgtDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增主键
	private Integer id;
	//组织编码
	private Long orgCode;
	//组织名称
	private String orgName;
	//父级ID
	private Long parentId;
	//状态0：停用，1：启用
	private Integer communistState;
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
	 * 设置：组织编码
	 */
	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：组织编码
	 */
	public Long getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：组织名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：组织名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：父级ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级ID
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：状态0：停用，1：启用
	 */
	public void setCommunistState(Integer communistState) {
		this.communistState = communistState;
	}
	/**
	 * 获取：状态0：停用，1：启用
	 */
	public Integer getCommunistState() {
		return communistState;
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
