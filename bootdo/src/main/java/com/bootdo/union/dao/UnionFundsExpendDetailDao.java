package com.bootdo.union.dao;

import com.bootdo.union.domain.UnionFundsExpendDetailDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 工会支出明细表
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
@Mapper
public interface UnionFundsExpendDetailDao {

	UnionFundsExpendDetailDO get(Long id);
	
	List<UnionFundsExpendDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UnionFundsExpendDetailDO unionFundsExpendDetail);
	
	int update(UnionFundsExpendDetailDO unionFundsExpendDetail);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}