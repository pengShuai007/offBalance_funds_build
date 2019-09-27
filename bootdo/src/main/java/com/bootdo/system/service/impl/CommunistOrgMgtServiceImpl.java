package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.CommunistOrgMgtDao;
import com.bootdo.system.domain.CommunistOrgMgtDO;
import com.bootdo.system.service.CommunistOrgMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class CommunistOrgMgtServiceImpl implements CommunistOrgMgtService {
	@Autowired
	private CommunistOrgMgtDao communistOrgMgtDao;
	
	@Override
	public CommunistOrgMgtDO get(Integer id){
		return communistOrgMgtDao.get(id);
	}
	
	@Override
	public List<CommunistOrgMgtDO> list(Map<String, Object> map){
		return communistOrgMgtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return communistOrgMgtDao.count(map);
	}
	
	@Override
	public int save(CommunistOrgMgtDO communistOrgMgt){
		return communistOrgMgtDao.save(communistOrgMgt);
	}
	
	@Override
	public int update(CommunistOrgMgtDO communistOrgMgt){
		return communistOrgMgtDao.update(communistOrgMgt);
	}
	
	@Override
	public int remove(Integer id){
		return communistOrgMgtDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return communistOrgMgtDao.batchRemove(ids);
	}

	@Override
	public Tree<CommunistOrgMgtDO> getTree(){
		List<Tree<CommunistOrgMgtDO>> trees = new ArrayList<Tree<CommunistOrgMgtDO>>();
		List<CommunistOrgMgtDO> communistList = communistOrgMgtDao.list(new HashMap());
		for(CommunistOrgMgtDO communist : communistList){
			Tree<CommunistOrgMgtDO> tree = new Tree<>();
			tree.setId(communist.getId().toString());
			tree.setParentId(communist.getParentId().toString());
			tree.setText(communist.getOrgName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<CommunistOrgMgtDO> t = BuildTree.build(trees);
		return t;
	}
}
