package com.bootdo.union.dao;


import com.bootdo.union.domain.ExpendRecordVO;
import com.bootdo.union.domain.UnionFundsExpendRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 工会支出记录表
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
@Mapper
public interface UnionFundsExpendRecordDao {

	ExpendRecordVO get(String id);
	
	List<ExpendRecordVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsExpendRecordDO unionFundsExpendRecord);
	
	int update(UnionFundsExpendRecordDO unionFundsExpendRecord);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List<ExpendRecordVO> todoList(Map value);
}
