package com.bootdo.union.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.union.domain.UnionFundsExpendDetailDO;
import com.bootdo.union.service.UnionFundsExpendDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 工会支出明细表
 * 
 * @author quxuan
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:56:00
 */
 
@Controller
@RequestMapping("/union/unionFundsExpendDetail")
public class UnionFundsExpendDetailController {
	@Autowired
	private UnionFundsExpendDetailService unionFundsExpendDetailService;
	
	@GetMapping()
	@RequiresPermissions("union:unionFundsExpendDetail:unionFundsExpendDetail")
	String UnionFundsExpendDetail(){
	    return "union/unionFundsExpendDetail/unionFundsExpendDetail";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("union:unionFundsExpendDetail:unionFundsExpendDetail")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UnionFundsExpendDetailDO> unionFundsExpendDetailList = unionFundsExpendDetailService.list(query);
		int total = unionFundsExpendDetailService.count(query);
		PageUtils pageUtils = new PageUtils(unionFundsExpendDetailList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("union:unionFundsExpendDetail:add")
	String add(){
	    return "union/unionFundsExpendDetail/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("union:unionFundsExpendDetail:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UnionFundsExpendDetailDO unionFundsExpendDetail = unionFundsExpendDetailService.get(id);
		model.addAttribute("unionFundsExpendDetail", unionFundsExpendDetail);
	    return "union/unionFundsExpendDetail/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("union:unionFundsExpendDetail:add")
	public R save( UnionFundsExpendDetailDO unionFundsExpendDetail){
		if(unionFundsExpendDetailService.save(unionFundsExpendDetail)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("union:unionFundsExpendDetail:edit")
	public R update( UnionFundsExpendDetailDO unionFundsExpendDetail){
		unionFundsExpendDetailService.update(unionFundsExpendDetail);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsExpendDetail:remove")
	public R remove( Long id){
		if(unionFundsExpendDetailService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("union:unionFundsExpendDetail:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		unionFundsExpendDetailService.batchRemove(ids);
		return R.ok();
	}
	
}
