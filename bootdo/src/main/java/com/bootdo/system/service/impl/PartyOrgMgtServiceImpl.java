package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.PartyOrgMgtDao;
import com.bootdo.system.domain.PartyOrgMgtDO;
import com.bootdo.system.service.PartyOrgMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class PartyOrgMgtServiceImpl implements PartyOrgMgtService {
	@Autowired
	private PartyOrgMgtDao partyOrgMgtDao;
	
	@Override
	public PartyOrgMgtDO get(Integer id){
		return partyOrgMgtDao.get(id);
	}
	
	@Override
	public List<PartyOrgMgtDO> list(Map<String, Object> map){
		return partyOrgMgtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return partyOrgMgtDao.count(map);
	}
	
	@Override
	public int save(PartyOrgMgtDO partyOrgMgt){
		return partyOrgMgtDao.save(partyOrgMgt);
	}
	
	@Override
	public int update(PartyOrgMgtDO partyOrgMgt){
		return partyOrgMgtDao.update(partyOrgMgt);
	}
	
	@Override
	public int remove(Integer id){
		return partyOrgMgtDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return partyOrgMgtDao.batchRemove(ids);
	}

	@Override
	public Tree<PartyOrgMgtDO> getTree(){
		List<Tree<PartyOrgMgtDO>> trees = new ArrayList<Tree<PartyOrgMgtDO>>();
		List<PartyOrgMgtDO> partyList = partyOrgMgtDao.list(new HashMap());
		for(PartyOrgMgtDO party : partyList){
			Tree<PartyOrgMgtDO> tree = new Tree<>();
			tree.setId(party.getId().toString());
			tree.setParentId(party.getParentId().toString());
			tree.setText(party.getOrgName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<PartyOrgMgtDO> t = BuildTree.build(trees);
		return t;
	}
}
