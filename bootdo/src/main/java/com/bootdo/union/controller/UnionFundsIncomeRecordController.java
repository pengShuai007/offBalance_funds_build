package com.bootdo.union.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.union.domain.UnionFundsIncomeRecordDO;
import com.bootdo.union.service.UnionFundsIncomeRecordService;
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
	
	@GetMapping()
	@RequiresPermissions("union:unionFundsIncomeRecord:unionFundsIncomeRecord")
	String UnionFundsIncomeRecord(){
	    return "union/unionFundsIncomeRecord/unionFundsIncomeRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("union:unionFundsIncomeRecord:unionFundsIncomeRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UnionFundsIncomeRecordDO> unionFundsIncomeRecordList = unionFundsIncomeRecordService.list(query);
		int total = unionFundsIncomeRecordService.count(query);
		PageUtils pageUtils = new PageUtils(unionFundsIncomeRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("union:unionFundsIncomeRecord:add")
	String add(){
	    return "union/unionFundsIncomeRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("union:unionFundsIncomeRecord:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UnionFundsIncomeRecordDO unionFundsIncomeRecord = unionFundsIncomeRecordService.get(id);
		model.addAttribute("unionFundsIncomeRecord", unionFundsIncomeRecord);
	    return "union/unionFundsIncomeRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("union:unionFundsIncomeRecord:add")
	public R save( UnionFundsIncomeRecordDO unionFundsIncomeRecord){
		if(unionFundsIncomeRecordService.save(unionFundsIncomeRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("union:unionFundsIncomeRecord:edit")
	public R update( UnionFundsIncomeRecordDO unionFundsIncomeRecord){
		unionFundsIncomeRecordService.update(unionFundsIncomeRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsIncomeRecord:remove")
	public R remove( Long id){
		if(unionFundsIncomeRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsIncomeRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		unionFundsIncomeRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
