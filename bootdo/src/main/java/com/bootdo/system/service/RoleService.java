package com.bootdo.system.service;

import com.bootdo.system.domain.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);

	List<RoleDO> listWithParam(Map<String, Object> params);
}
