package com.bootdo.system.service.impl;

import com.bootdo.system.dao.CommunistOrgMgtDao;
import com.bootdo.system.domain.CommunistOrgMgtDO;
import com.bootdo.system.service.CommunistOrgMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
