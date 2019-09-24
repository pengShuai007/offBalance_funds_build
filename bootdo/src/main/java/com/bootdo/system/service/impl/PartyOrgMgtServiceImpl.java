package com.bootdo.system.service.impl;

import com.bootdo.system.dao.PartyOrgMgtDao;
import com.bootdo.system.domain.PartyOrgMgtDO;
import com.bootdo.system.service.PartyOrgMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
