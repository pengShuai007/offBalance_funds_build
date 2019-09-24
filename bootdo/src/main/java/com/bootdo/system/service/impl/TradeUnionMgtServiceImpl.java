package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.TradeUnionMgtDao;
import com.bootdo.system.domain.TradeUnionMgtDO;
import com.bootdo.system.service.TradeUnionMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

	@Override
	public Tree<TradeUnionMgtDO> getTree(){
		List<Tree<TradeUnionMgtDO>> trees = new ArrayList<Tree<TradeUnionMgtDO>>();
		List<TradeUnionMgtDO> tradeList = tradeUnionMgtDao.list(new HashMap());
		for(TradeUnionMgtDO trade : tradeList){
			Tree<TradeUnionMgtDO> tree = new Tree<>();
			tree.setId(trade.getId().toString());
			tree.setParentId(trade.getParentId().toString());
			tree.setText(trade.getOrgName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<TradeUnionMgtDO> t = BuildTree.build(trees);
		return t;
	}
}
