package com.bootdo.system.service;

import com.bootdo.system.domain.CommunistOrgMgtDO;

import java.util.List;
import java.util.Map;

/**
 * 团组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:58:54
 */
public interface CommunistOrgMgtService {
	
	CommunistOrgMgtDO get(Integer id);
	
	List<CommunistOrgMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommunistOrgMgtDO communistOrgMgt);
	
	int update(CommunistOrgMgtDO communistOrgMgt);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
