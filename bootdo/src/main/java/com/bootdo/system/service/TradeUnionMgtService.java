package com.bootdo.system.service;

import com.bootdo.system.domain.TradeUnionMgtDO;

import java.util.List;
import java.util.Map;

/**
 * 工会组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:05:55
 */
public interface TradeUnionMgtService {
	
	TradeUnionMgtDO get(Integer id);
	
	List<TradeUnionMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TradeUnionMgtDO tradeUnionMgt);
	
	int update(TradeUnionMgtDO tradeUnionMgt);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
