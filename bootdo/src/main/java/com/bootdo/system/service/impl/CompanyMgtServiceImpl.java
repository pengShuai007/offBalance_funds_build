package com.bootdo.system.service.impl;

import com.bootdo.system.dao.CompanyMgtDao;
import com.bootdo.system.domain.CompanyMgtDO;
import com.bootdo.system.service.CompanyMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class CompanyMgtServiceImpl implements CompanyMgtService {
	@Autowired
	private CompanyMgtDao companyMgtDao;
	
	@Override
	public CompanyMgtDO get(Integer id){
		return companyMgtDao.get(id);
	}
	
	@Override
	public List<CompanyMgtDO> list(Map<String, Object> map){
		return companyMgtDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return companyMgtDao.count(map);
	}
	
	@Override
	public int save(CompanyMgtDO companyMgt){
		return companyMgtDao.save(companyMgt);
	}
	
	@Override
	public int update(CompanyMgtDO companyMgt){
		return companyMgtDao.update(companyMgt);
	}
	
	@Override
	public int remove(Integer id){
		return companyMgtDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return companyMgtDao.batchRemove(ids);
	}
	
}
