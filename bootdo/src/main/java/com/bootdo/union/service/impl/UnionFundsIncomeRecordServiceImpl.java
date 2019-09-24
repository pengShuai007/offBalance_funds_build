package com.bootdo.union.service.impl;

import com.bootdo.union.dao.UnionFundsIncomeRecordDao;
import com.bootdo.union.domain.UnionFundsIncomeRecordDO;
import com.bootdo.union.service.UnionFundsIncomeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UnionFundsIncomeRecordServiceImpl implements UnionFundsIncomeRecordService {
	@Autowired
	private UnionFundsIncomeRecordDao unionFundsIncomeRecordDao;
	
	@Override
	public UnionFundsIncomeRecordDO get(Long id){
		return unionFundsIncomeRecordDao.get(id);
	}
	
	@Override
	public List<UnionFundsIncomeRecordDO> list(Map<String, Object> map){
		return unionFundsIncomeRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return unionFundsIncomeRecordDao.count(map);
	}
	
	@Override
	public int save(UnionFundsIncomeRecordDO unionFundsIncomeRecord){
		return unionFundsIncomeRecordDao.save(unionFundsIncomeRecord);
	}
	
	@Override
	public int update(UnionFundsIncomeRecordDO unionFundsIncomeRecord){
		return unionFundsIncomeRecordDao.update(unionFundsIncomeRecord);
	}
	
	@Override
	public int remove(Long id){
		return unionFundsIncomeRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return unionFundsIncomeRecordDao.batchRemove(ids);
	}
	
}
