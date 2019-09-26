package com.bootdo.union.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.union.dao.UnionFundsIncomeDetailDao;
import com.bootdo.union.domain.ExpendRecordVO;
import com.bootdo.union.domain.IncomeRecordVO;
import com.bootdo.union.domain.UnionFundsIncomeDetailDO;
import com.bootdo.union.domain.UnionFundsIncomeRecordDO;
import com.bootdo.union.service.UnionFundsIncomeRecordService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 工会收入记录表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
 
@Controller
@RequestMapping("/union/unionFundsIncomeRecord")
public class UnionFundsIncomeRecordController {
	@Autowired
	private UnionFundsIncomeRecordService unionFundsIncomeRecordService;
	

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		//查询列表数据
		if(!params.containsKey("offset")){
			params.put("offset",0);
		}else if(params.containsKey("offset") && StringUtils.isEmpty((String) params.get("offset"))){
			params.put("offset",0);
		}
		if(!params.containsKey("limit")){
			params.put("limit",20);
		}else if(params.containsKey("limit") && StringUtils.isEmpty((String) params.get("limit"))){
			params.put("limit",20);
		}
        Query query = new Query(params);
		List<IncomeRecordVO> incomeRecordVOS = unionFundsIncomeRecordService.list(query);
		int total = unionFundsIncomeRecordService.count(query);
		PageUtils pageUtils = new PageUtils(incomeRecordVOS, total);
		return pageUtils;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save(UnionFundsIncomeRecordDO incomeRecordDO, UnionFundsIncomeDetailDO incomeDetailDO){
		if(unionFundsIncomeRecordService.save(incomeRecordDO,incomeDetailDO)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String  recordId){
		if(unionFundsIncomeRecordService.remove(recordId)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(UnionFundsIncomeRecordDO recordDO, UnionFundsIncomeDetailDO detailDO){
		unionFundsIncomeRecordService.update(recordDO,detailDO);
		return R.ok();
	}


	@ResponseBody
	@GetMapping("/get")
	HashedMap get( String recordId){
		HashedMap result = new HashedMap();
		result.put("success",true);
		result.put("code","0000");
		IncomeRecordVO incomeRecordVO = unionFundsIncomeRecordService.get(recordId);
		if(null != incomeRecordVO){
			result.put("data",incomeRecordVO);
		}else {
			result.put("success",false);
			result.put("code","0001");
			result.put("data",null);
		}

		return result;
	}

	
}
