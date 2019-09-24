package com.bootdo.union.service;

import com.bootdo.union.domain.UnionFundsIncomeRecordDO;

import java.util.List;
import java.util.Map;

/**
 * 工会收入记录表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
public interface UnionFundsIncomeRecordService {
	
	UnionFundsIncomeRecordDO get(Long id);
	
	List<UnionFundsIncomeRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsIncomeRecordDO unionFundsIncomeRecord);
	
	int update(UnionFundsIncomeRecordDO unionFundsIncomeRecord);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
