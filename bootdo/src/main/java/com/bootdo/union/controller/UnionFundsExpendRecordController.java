package com.bootdo.union.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.union.domain.UnionFundsExpendRecordDO;
import com.bootdo.union.service.UnionFundsExpendRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 工会支出记录表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
 
@Controller
@RequestMapping("/union/unionFundsExpendRecord")
public class UnionFundsExpendRecordController {
	@Autowired
	private UnionFundsExpendRecordService unionFundsExpendRecordService;
	
	@GetMapping()
	@RequiresPermissions("union:unionFundsExpendRecord:unionFundsExpendRecord")
	String UnionFundsExpendRecord(){
	    return "union/unionFundsExpendRecord/unionFundsExpendRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("union:unionFundsExpendRecord:unionFundsExpendRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UnionFundsExpendRecordDO> unionFundsExpendRecordList = unionFundsExpendRecordService.list(query);
		int total = unionFundsExpendRecordService.count(query);
		PageUtils pageUtils = new PageUtils(unionFundsExpendRecordList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("union:unionFundsExpendRecord:add")
	String add(){
	    return "union/unionFundsExpendRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("union:unionFundsExpendRecord:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UnionFundsExpendRecordDO unionFundsExpendRecord = unionFundsExpendRecordService.get(id);
		model.addAttribute("unionFundsExpendRecord", unionFundsExpendRecord);
	    return "union/unionFundsExpendRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("union:unionFundsExpendRecord:add")
	public R save( UnionFundsExpendRecordDO unionFundsExpendRecord){
		if(unionFundsExpendRecordService.save(unionFundsExpendRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//    @RequiresPermissions("oa:leave:edit")
	public R update(String taskId) {
		unionFundsExpendRecordService.update(null,taskId);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsExpendRecord:remove")
	public R remove( Long id){
		if(unionFundsExpendRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsExpendRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		unionFundsExpendRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
