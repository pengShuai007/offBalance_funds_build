package com.bootdo.union.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.union.domain.UnionFundsIncomeDetailDO;
import com.bootdo.union.service.UnionFundsIncomeDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 工会收入明细表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
 
@Controller
@RequestMapping("/union/unionFundsIncomeDetail")
public class UnionFundsIncomeDetailController {
	@Autowired
	private UnionFundsIncomeDetailService unionFundsIncomeDetailService;
	
	@GetMapping()
	@RequiresPermissions("union:unionFundsIncomeDetail:unionFundsIncomeDetail")
	String UnionFundsIncomeDetail(){
	    return "union/unionFundsIncomeDetail/unionFundsIncomeDetail";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("union:unionFundsIncomeDetail:unionFundsIncomeDetail")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UnionFundsIncomeDetailDO> unionFundsIncomeDetailList = unionFundsIncomeDetailService.list(query);
		int total = unionFundsIncomeDetailService.count(query);
		PageUtils pageUtils = new PageUtils(unionFundsIncomeDetailList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("union:unionFundsIncomeDetail:add")
	String add(){
	    return "union/unionFundsIncomeDetail/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("union:unionFundsIncomeDetail:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UnionFundsIncomeDetailDO unionFundsIncomeDetail = unionFundsIncomeDetailService.get(id);
		model.addAttribute("unionFundsIncomeDetail", unionFundsIncomeDetail);
	    return "union/unionFundsIncomeDetail/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("union:unionFundsIncomeDetail:add")
	public R save( UnionFundsIncomeDetailDO unionFundsIncomeDetail){
		if(unionFundsIncomeDetailService.save(unionFundsIncomeDetail)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("union:unionFundsIncomeDetail:edit")
	public R update( UnionFundsIncomeDetailDO unionFundsIncomeDetail){
		unionFundsIncomeDetailService.update(unionFundsIncomeDetail);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsIncomeDetail:remove")
	public R remove( Long id){
		if(unionFundsIncomeDetailService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsIncomeDetail:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		unionFundsIncomeDetailService.batchRemove(ids);
		return R.ok();
	}
	
}
