package com.bootdo.union.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.union.dao.UnionFundsIncomeDetailDao;
import com.bootdo.union.dao.UnionFundsIncomeRecordDao;
import com.bootdo.union.domain.IncomeRecordVO;
import com.bootdo.union.domain.UnionFundsIncomeDetailDO;
import com.bootdo.union.domain.UnionFundsIncomeRecordDO;
import com.bootdo.union.service.UnionFundsIncomeRecordService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UnionFundsIncomeRecordServiceImpl implements UnionFundsIncomeRecordService {
	@Autowired
	private UnionFundsIncomeRecordDao unionFundsIncomeRecordDao;
	@Autowired
	private UnionFundsIncomeDetailDao unionFundsIncomeDetailDao;
	
	@Override
	public IncomeRecordVO get(String recordId){
		IncomeRecordVO incomeRecordVO = unionFundsIncomeRecordDao.get(recordId);
		if(null != incomeRecordVO){
			List<UnionFundsIncomeDetailDO> incomeDetailDOS = unionFundsIncomeDetailDao.listByRecordIds(recordId);
			incomeRecordVO.setIncomeDetail(incomeDetailDOS);
		}
		return incomeRecordVO;
	}
	
	@Override
	public List<IncomeRecordVO> list(Map<String, Object> map){
		List<IncomeRecordVO> incomeRecordVOS = unionFundsIncomeRecordDao.list(map);
		if(CollectionUtils.isEmpty(incomeRecordVOS)){
			return new ArrayList<>();
		}
		StringBuilder recordIds = new StringBuilder("");
		incomeRecordVOS.stream().forEach(incomeRecordVO -> {
			if(StringUtils.isEmpty(recordIds.toString())){
				recordIds.append(incomeRecordVO.getRecordId());
			}else {
				recordIds.append(","+incomeRecordVO.getRecordId());
			}
		});
		List<UnionFundsIncomeDetailDO> incomeDetailDOS = unionFundsIncomeDetailDao.listByRecordIds(recordIds.toString());
		incomeRecordVOS.stream().forEach(incomeRecordVO -> {
			incomeRecordVO.setIncomeDetail(incomeDetailDOS.stream()
					.filter(incomeDetailDO -> incomeRecordVO.getRecordId().equals(incomeDetailDO.getRecordId()))
					.collect(Collectors.toList()));
		});
		return incomeRecordVOS;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return unionFundsIncomeRecordDao.count(map);
	}
	
	@Override
	public int save(UnionFundsIncomeRecordDO unionFundsIncomeRecord, UnionFundsIncomeDetailDO incomeDetailDO){
		// 保存收入记录
		unionFundsIncomeRecord.setRecordId(UUIDUtils.randomUUID());
		unionFundsIncomeRecord.setCreator(ShiroUtils.getUser().getUsername());
		unionFundsIncomeRecord.setCreateTime(new Date());
		int i = unionFundsIncomeRecordDao.save(unionFundsIncomeRecord);
		//保存收入记录明细
		incomeDetailDO.setIncomeId(UUIDUtils.randomUUID());
		incomeDetailDO.setRecordId(unionFundsIncomeRecord.getRecordId());
		int d =  unionFundsIncomeDetailDao.save(incomeDetailDO);
		return i;
	}
	
	@Override
	public int update(UnionFundsIncomeRecordDO recordDO, UnionFundsIncomeDetailDO detailDO){
		recordDO.setModifier(ShiroUtils.getUser().getUsername());
		recordDO.setModifyTime(new Date());
		int i = unionFundsIncomeRecordDao.update(recordDO);
		int d = unionFundsIncomeDetailDao.update(detailDO);
		return i;
	}
	
	@Override
	public int remove(String recordId){
		return unionFundsIncomeRecordDao.remove(recordId);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return unionFundsIncomeRecordDao.batchRemove(ids);
	}
	
}
