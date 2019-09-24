package com.bootdo.system.dao;

import com.bootdo.system.domain.PartyOrgMgtDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 党组织管理表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:03:10
 */
@Mapper
public interface PartyOrgMgtDao {

	PartyOrgMgtDO get(Integer id);
	
	List<PartyOrgMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PartyOrgMgtDO partyOrgMgt);
	
	int update(PartyOrgMgtDO partyOrgMgt);
	
	int remove(Integer ID);
	
	int batchRemove(Integer[] ids);
}
