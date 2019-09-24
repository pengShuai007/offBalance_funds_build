package com.bootdo.system.dao;

import com.bootdo.system.domain.TradeUnionMgtDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 工会组织管理表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:05:55
 */
@Mapper
public interface TradeUnionMgtDao {

	TradeUnionMgtDO get(Integer id);
	
	List<TradeUnionMgtDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TradeUnionMgtDO tradeUnionMgt);
	
	int update(TradeUnionMgtDO tradeUnionMgt);
	
	int remove(Integer ID);
	
	int batchRemove(Integer[] ids);
}
