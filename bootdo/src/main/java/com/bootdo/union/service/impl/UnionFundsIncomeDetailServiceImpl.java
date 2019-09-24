package com.bootdo.union.service.impl;

import com.bootdo.union.dao.UnionFundsIncomeDetailDao;
import com.bootdo.union.domain.UnionFundsIncomeDetailDO;
import com.bootdo.union.service.UnionFundsIncomeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UnionFundsIncomeDetailServiceImpl implements UnionFundsIncomeDetailService {
	@Autowired
	private UnionFundsIncomeDetailDao unionFundsIncomeDetailDao;
	
	@Override
	public UnionFundsIncomeDetailDO get(Long id){
		return unionFundsIncomeDetailDao.get(id);
	}
	
	@Override
	public List<UnionFundsIncomeDetailDO> list(Map<String, Object> map){
		return unionFundsIncomeDetailDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return unionFundsIncomeDetailDao.count(map);
	}
	
	@Override
	public int save(UnionFundsIncomeDetailDO unionFundsIncomeDetail){
		return unionFundsIncomeDetailDao.save(unionFundsIncomeDetail);
	}
	
	@Override
	public int update(UnionFundsIncomeDetailDO unionFundsIncomeDetail){
		return unionFundsIncomeDetailDao.update(unionFundsIncomeDetail);
	}
	
	@Override
	public int remove(Long id){
		return unionFundsIncomeDetailDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return unionFundsIncomeDetailDao.batchRemove(ids);
	}
	
}
