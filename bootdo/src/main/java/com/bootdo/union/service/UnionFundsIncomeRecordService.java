package com.bootdo.union.service;

import com.bootdo.union.dao.UnionFundsIncomeDetailDao;
import com.bootdo.union.domain.IncomeRecordVO;
import com.bootdo.union.domain.UnionFundsIncomeDetailDO;
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

	IncomeRecordVO get(String recordId);
	
	List<IncomeRecordVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsIncomeRecordDO unionFundsIncomeRecord, UnionFundsIncomeDetailDO incomeDetailDO);
	
	int update(UnionFundsIncomeRecordDO unionFundsIncomeRecord, UnionFundsIncomeDetailDO detailDao);
	
	int remove(String id);
	
	int batchRemove(Long[] ids);
}
