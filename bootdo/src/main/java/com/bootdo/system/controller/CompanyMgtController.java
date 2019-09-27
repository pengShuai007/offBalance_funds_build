package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.dao.CompanyMgtDao;
import com.bootdo.system.domain.CompanyMgtDO;
import com.bootdo.system.service.CompanyMgtService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 公司信息管理表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-24 17:01:03
 */
 
@Controller
@RequestMapping("/system/companyMgt")
public class CompanyMgtController extends BaseController {
	@Autowired
	private CompanyMgtService companyMgtService;

	@Autowired
	private CompanyMgtDao companyMgtDao;

	@GetMapping()
	@RequiresPermissions("system:companyMgt:companyMgt")
	String CompanyMgt(){
	    return "system/companyMgt/companyMgt";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:companyMgt:companyMgt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CompanyMgtDO> companyMgtList = companyMgtService.list(query);
		int total = companyMgtService.count(query);
		PageUtils pageUtils = new PageUtils(companyMgtList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:companyMgt:add")
	String add(){
	    return "system/companyMgt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:companyMgt:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CompanyMgtDO companyMgt = companyMgtService.get(id);
		model.addAttribute("companyMgt", companyMgt);
	    return "system/companyMgt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:companyMgt:add")
	public R save( CompanyMgtDO companyMgt){
		if(companyMgtService.save(companyMgt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:companyMgt:edit")
	public R update( CompanyMgtDO companyMgt){
		companyMgtService.update(companyMgt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:companyMgt:remove")
	public R remove( Integer id){
		if(companyMgtService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:companyMgt:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		companyMgtService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping( "/getCurrentUserCompanyInfo")
	@ResponseBody
	public CompanyMgtDO getCurrentUserCompanyInfo(){
		CompanyMgtDO companyMgtDO = companyMgtDao.get(getUserId().intValue());
		return companyMgtDO;
	}
}
