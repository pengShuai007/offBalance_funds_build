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

import com.bootdo.system.domain.PartyOrgMgtDO;
import com.bootdo.system.service.PartyOrgMgtService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 党组织管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:03:10
 */
 
@Controller
@RequestMapping("/system/partyOrgMgt")
public class PartyOrgMgtController {
	@Autowired
	private PartyOrgMgtService partyOrgMgtService;
	
	@GetMapping()
	@RequiresPermissions("system:partyOrgMgt:partyOrgMgt")
	String PartyOrgMgt(){
	    return "system/partyOrgMgt/partyOrgMgt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:partyOrgMgt:partyOrgMgt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PartyOrgMgtDO> partyOrgMgtList = partyOrgMgtService.list(query);
		int total = partyOrgMgtService.count(query);
		PageUtils pageUtils = new PageUtils(partyOrgMgtList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:partyOrgMgt:add")
	String add(){
	    return "system/partyOrgMgt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:partyOrgMgt:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PartyOrgMgtDO partyOrgMgt = partyOrgMgtService.get(id);
		model.addAttribute("partyOrgMgt", partyOrgMgt);
	    return "system/partyOrgMgt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:partyOrgMgt:add")
	public R save( PartyOrgMgtDO partyOrgMgt){
		if(partyOrgMgtService.save(partyOrgMgt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:partyOrgMgt:edit")
	public R update( PartyOrgMgtDO partyOrgMgt){
		partyOrgMgtService.update(partyOrgMgt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:partyOrgMgt:remove")
	public R remove( Integer id){
		if(partyOrgMgtService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:partyOrgMgt:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		partyOrgMgtService.batchRemove(ids);
		return R.ok();
	}
	
}
