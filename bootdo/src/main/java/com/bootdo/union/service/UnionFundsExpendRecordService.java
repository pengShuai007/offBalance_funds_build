package com.bootdo.union.service;

import com.bootdo.union.domain.ExpendRecordVO;
import com.bootdo.union.domain.UnionFundsExpendDetailDO;
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

	ExpendRecordVO get(String recordId);
	
	List<ExpendRecordVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsExpendRecordDO unionFundsExpendRecord, UnionFundsExpendDetailDO detailDO);

	int remove(String id);
	
	int batchRemove(String[] ids);

    void update(UnionFundsExpendRecordDO unionFundsExpendRecord, String operateStatus);

	List<ExpendRecordVO> todoList();

	List<ExpendRecordVO> historyTask();

	boolean edit(UnionFundsExpendRecordDO unionFundsExpendRecord, UnionFundsExpendDetailDO detailDO);
}
