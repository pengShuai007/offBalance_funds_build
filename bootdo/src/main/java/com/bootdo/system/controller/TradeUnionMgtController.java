package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.TradeUnionMgtDO;
import com.bootdo.system.service.TradeUnionMgtService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 工会组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:05:55
 */
 
@Controller
@RequestMapping("/system/tradeUnionMgt")
public class TradeUnionMgtController {
	@Autowired
	private TradeUnionMgtService tradeUnionMgtService;
	
	@GetMapping()
	@RequiresPermissions("system:tradeUnionMgt:tradeUnionMgt")
	String TradeUnionMgt(){
	    return "system/tradeUnionMgt/tradeUnionMgt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:tradeUnionMgt:tradeUnionMgt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TradeUnionMgtDO> tradeUnionMgtList = tradeUnionMgtService.list(query);
		int total = tradeUnionMgtService.count(query);
		PageUtils pageUtils = new PageUtils(tradeUnionMgtList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:tradeUnionMgt:add")
	String add(){
	    return "system/tradeUnionMgt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:tradeUnionMgt:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TradeUnionMgtDO tradeUnionMgt = tradeUnionMgtService.get(id);
		model.addAttribute("tradeUnionMgt", tradeUnionMgt);
	    return "system/tradeUnionMgt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:tradeUnionMgt:add")
	public R save( TradeUnionMgtDO tradeUnionMgt){
		if(tradeUnionMgtService.save(tradeUnionMgt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:tradeUnionMgt:edit")
	public R update( TradeUnionMgtDO tradeUnionMgt){
		tradeUnionMgtService.update(tradeUnionMgt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:tradeUnionMgt:remove")
	public R remove( Integer id){
		if(tradeUnionMgtService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:tradeUnionMgt:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tradeUnionMgtService.batchRemove(ids);
		return R.ok();
	}
	
}
