package com.bootdo.system.service;

import com.bootdo.system.domain.AdminOrgMgtDO;

import java.util.List;
import java.util.Map;

/**
 * 行政组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:54:54
 */
public interface AdminOrgMgtService {
	
	AdminOrgMgtDO get(Integer id);
	
	List<AdminOrgMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AdminOrgMgtDO adminOrgMgt);
	
	int update(AdminOrgMgtDO adminOrgMgt);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
