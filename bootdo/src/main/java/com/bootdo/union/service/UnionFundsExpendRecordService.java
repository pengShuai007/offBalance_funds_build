package com.bootdo.union.service;

import com.bootdo.union.domain.UnionFundsExpendRecordDO;

import java.util.List;
import java.util.Map;

/**
 * 工会支出记录表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
public interface UnionFundsExpendRecordService {
	
	UnionFundsExpendRecordDO get(Long id);
	
	List<UnionFundsExpendRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsExpendRecordDO unionFundsExpendRecord);
	
	int update(UnionFundsExpendRecordDO unionFundsExpendRecord, String taskId);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
