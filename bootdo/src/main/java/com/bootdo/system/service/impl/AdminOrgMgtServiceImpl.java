package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.AdminOrgMgtDao;
import com.bootdo.system.domain.AdminOrgMgtDO;
import com.bootdo.system.service.AdminOrgMgtService;



@Service
public class AdminOrgMgtServiceImpl implements AdminOrgMgtService {
	@Autowired
	private AdminOrgMgtDao adminOrgMgtDao;
	
	@Override
	public AdminOrgMgtDO get(Integer id){
		return adminOrgMgtDao.get(id);
	}
	
	@Override
	public List<AdminOrgMgtDO> list(Map<String, Object> map){
		return adminOrgMgtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return adminOrgMgtDao.count(map);
	}
	
	@Override
	public int save(AdminOrgMgtDO adminOrgMgt){
		return adminOrgMgtDao.save(adminOrgMgt);
	}
	
	@Override
	public int update(AdminOrgMgtDO adminOrgMgt){
		return adminOrgMgtDao.update(adminOrgMgt);
	}
	
	@Override
	public int remove(Integer id){
		return adminOrgMgtDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return adminOrgMgtDao.batchRemove(ids);
	}
	
}
