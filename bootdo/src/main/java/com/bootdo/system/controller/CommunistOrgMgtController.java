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

import com.bootdo.system.domain.CommunistOrgMgtDO;
import com.bootdo.system.service.CommunistOrgMgtService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 团组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 16:58:54
 */
 
@Controller
@RequestMapping("/system/communistOrgMgt")
public class CommunistOrgMgtController {
	@Autowired
	private CommunistOrgMgtService communistOrgMgtService;
	
	@GetMapping()
	@RequiresPermissions("system:communistOrgMgt:communistOrgMgt")
	String CommunistOrgMgt(){
	    return "system/communistOrgMgt/communistOrgMgt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:communistOrgMgt:communistOrgMgt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CommunistOrgMgtDO> communistOrgMgtList = communistOrgMgtService.list(query);
		int total = communistOrgMgtService.count(query);
		PageUtils pageUtils = new PageUtils(communistOrgMgtList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:communistOrgMgt:add")
	String add(){
	    return "system/communistOrgMgt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:communistOrgMgt:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CommunistOrgMgtDO communistOrgMgt = communistOrgMgtService.get(id);
		model.addAttribute("communistOrgMgt", communistOrgMgt);
	    return "system/communistOrgMgt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:communistOrgMgt:add")
	public R save( CommunistOrgMgtDO communistOrgMgt){
		if(communistOrgMgtService.save(communistOrgMgt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:communistOrgMgt:edit")
	public R update( CommunistOrgMgtDO communistOrgMgt){
		communistOrgMgtService.update(communistOrgMgt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:communistOrgMgt:remove")
	public R remove( Integer id){
		if(communistOrgMgtService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:communistOrgMgt:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		communistOrgMgtService.batchRemove(ids);
		return R.ok();
	}
	
}
