package com.bootdo.system.dao;

import com.bootdo.system.domain.CommunistOrgMgtDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 团组织管理表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:58:54
 */
@Mapper
public interface CommunistOrgMgtDao {

	CommunistOrgMgtDO get(Integer id);
	
	List<CommunistOrgMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommunistOrgMgtDO communistOrgMgt);
	
	int update(CommunistOrgMgtDO communistOrgMgt);
	
	int remove(Integer ID);
	
	int batchRemove(Integer[] ids);
}
