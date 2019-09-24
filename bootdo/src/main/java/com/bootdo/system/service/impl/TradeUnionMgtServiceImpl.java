package com.bootdo.system.service.impl;

import com.bootdo.system.dao.TradeUnionMgtDao;
import com.bootdo.system.domain.TradeUnionMgtDO;
import com.bootdo.system.service.TradeUnionMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class TradeUnionMgtServiceImpl implements TradeUnionMgtService {
	@Autowired
	private TradeUnionMgtDao tradeUnionMgtDao;
	
	@Override
	public TradeUnionMgtDO get(Integer id){
		return tradeUnionMgtDao.get(id);
	}
	
	@Override
	public List<TradeUnionMgtDO> list(Map<String, Object> map){
		return tradeUnionMgtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tradeUnionMgtDao.count(map);
	}
	
	@Override
	public int save(TradeUnionMgtDO tradeUnionMgt){
		return tradeUnionMgtDao.save(tradeUnionMgt);
	}
	
	@Override
	public int update(TradeUnionMgtDO tradeUnionMgt){
		return tradeUnionMgtDao.update(tradeUnionMgt);
	}
	
	@Override
	public int remove(Integer id){
		return tradeUnionMgtDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tradeUnionMgtDao.batchRemove(ids);
	}
	
}
