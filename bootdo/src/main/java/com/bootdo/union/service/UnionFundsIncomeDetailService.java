package com.bootdo.union.service;

import com.bootdo.union.domain.UnionFundsIncomeDetailDO;

import java.util.List;
import java.util.Map;

/**
 * 工会收入明细表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
public interface UnionFundsIncomeDetailService {
	
	UnionFundsIncomeDetailDO get(Long id);
	
	List<UnionFundsIncomeDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsIncomeDetailDO unionFundsIncomeDetail);
	
	int update(UnionFundsIncomeDetailDO unionFundsIncomeDetail);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
