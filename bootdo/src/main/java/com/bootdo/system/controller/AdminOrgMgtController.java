package com.bootdo.system.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.AdminOrgMgtDO;
import com.bootdo.system.service.AdminOrgMgtService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 行政组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:54:54
 */
 
@Controller
@RequestMapping("/system/adminOrgMgt")
public class AdminOrgMgtController {
	@Autowired
	private AdminOrgMgtService adminOrgMgtService;
	
	@GetMapping()
	@RequiresPermissions("system:adminOrgMgt:adminOrgMgt")
	String AdminOrgMgt(){
	    return "system/adminOrgMgt/adminOrgMgt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:adminOrgMgt:adminOrgMgt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AdminOrgMgtDO> adminOrgMgtList = adminOrgMgtService.list(query);
		int total = adminOrgMgtService.count(query);
		PageUtils pageUtils = new PageUtils(adminOrgMgtList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:adminOrgMgt:add")
	String add(){
	    return "system/adminOrgMgt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:adminOrgMgt:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AdminOrgMgtDO adminOrgMgt = adminOrgMgtService.get(id);
		model.addAttribute("adminOrgMgt", adminOrgMgt);
	    return "system/adminOrgMgt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:adminOrgMgt:add")
	public R save( AdminOrgMgtDO adminOrgMgt){
		if(adminOrgMgtService.save(adminOrgMgt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:adminOrgMgt:edit")
	public R update( AdminOrgMgtDO adminOrgMgt){
		adminOrgMgtService.update(adminOrgMgt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:adminOrgMgt:remove")
	public R remove( Integer id){
		if(adminOrgMgtService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:adminOrgMgt:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		adminOrgMgtService.batchRemove(ids);
		return R.ok();
	}
	
}
