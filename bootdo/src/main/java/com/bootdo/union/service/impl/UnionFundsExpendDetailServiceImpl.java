package com.bootdo.union.service.impl;

import com.bootdo.union.dao.UnionFundsExpendDetailDao;
import com.bootdo.union.domain.UnionFundsExpendDetailDO;
import com.bootdo.union.service.UnionFundsExpendDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UnionFundsExpendDetailServiceImpl implements UnionFundsExpendDetailService {
	@Autowired
	private UnionFundsExpendDetailDao unionFundsExpendDetailDao;
	
	@Override
	public UnionFundsExpendDetailDO get(Long id){
		return unionFundsExpendDetailDao.get(id);
	}
	
	@Override
	public List<UnionFundsExpendDetailDO> list(Map<String, Object> map){
		return unionFundsExpendDetailDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return unionFundsExpendDetailDao.count(map);
	}
	
	@Override
	public int save(UnionFundsExpendDetailDO unionFundsExpendDetail){
		return unionFundsExpendDetailDao.save(unionFundsExpendDetail);
	}
	
	@Override
	public int update(UnionFundsExpendDetailDO unionFundsExpendDetail){
		return unionFundsExpendDetailDao.update(unionFundsExpendDetail);
	}
	
	@Override
	public int remove(Long id){
		return unionFundsExpendDetailDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return unionFundsExpendDetailDao.batchRemove(ids);
	}
	
}
