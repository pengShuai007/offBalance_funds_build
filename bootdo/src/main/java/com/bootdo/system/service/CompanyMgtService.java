package com.bootdo.system.service;

import com.bootdo.system.domain.CompanyMgtDO;

import java.util.List;
import java.util.Map;

/**
 * 公司信息管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:01:03
 */
public interface CompanyMgtService {
	
	CompanyMgtDO get(Integer id);
	
	List<CompanyMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyMgtDO companyMgt);
	
	int update(CompanyMgtDO companyMgt);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
