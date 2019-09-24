package com.bootdo.system.service;

import com.bootdo.system.domain.PartyOrgMgtDO;

import java.util.List;
import java.util.Map;

/**
 * 党组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:03:10
 */
public interface PartyOrgMgtService {
	
	PartyOrgMgtDO get(Integer id);
	
	List<PartyOrgMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PartyOrgMgtDO partyOrgMgt);
	
	int update(PartyOrgMgtDO partyOrgMgt);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
