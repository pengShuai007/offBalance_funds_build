package com.bootdo.union.dao;

import com.bootdo.union.domain.IncomeRecordVO;
import com.bootdo.union.domain.UnionFundsIncomeRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 工会收入记录表
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
@Mapper
public interface UnionFundsIncomeRecordDao {

	IncomeRecordVO get(String recordId);
	
	List<IncomeRecordVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsIncomeRecordDO unionFundsIncomeRecord);
	
	int update(UnionFundsIncomeRecordDO unionFundsIncomeRecord);
	
	int remove(String id);
	
	int batchRemove(Long[] ids);
}
