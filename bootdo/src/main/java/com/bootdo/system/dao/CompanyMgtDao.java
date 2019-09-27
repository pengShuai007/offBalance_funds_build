package com.bootdo.system.dao;

import com.bootdo.system.domain.CompanyMgtDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 公司信息管理表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:01:03
 */
@Mapper
public interface CompanyMgtDao {

	CompanyMgtDO get(Integer id);
	
	List<CompanyMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyMgtDO companyMgt);
	
	int update(CompanyMgtDO companyMgt);
	
	int remove(Integer ID);
	
	int batchRemove(Integer[] ids);
}
