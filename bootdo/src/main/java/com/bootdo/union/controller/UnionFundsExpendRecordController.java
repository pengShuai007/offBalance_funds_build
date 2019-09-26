package com.bootdo.union.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.union.domain.ExpendRecordVO;
import com.bootdo.union.domain.UnionFundsExpendDetailDO;
import com.bootdo.union.domain.UnionFundsExpendRecordDO;
import com.bootdo.union.service.UnionFundsExpendRecordService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/union/unionFundsExpendRecord")
public class UnionFundsExpendRecordController {
	@Autowired
	private UnionFundsExpendRecordService unionFundsExpendRecordService;


	/** 
	* @Description: 查询支出记录列表 
	* @Param:  
	* @return:  
	* @Author: quxuan 
	* @Date: 2019/9/25 
	*/
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
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
		List<ExpendRecordVO> unionFundsExpendRecordList = unionFundsExpendRecordService.list(query);
		int total = unionFundsExpendRecordService.count(query);
		PageUtils pageUtils = new PageUtils(unionFundsExpendRecordList, total);
		return pageUtils;
	}

	/** 
	* @Description: 保存支持记录，如果是向下级工会拨付的，需要走工作流
	* @Param:  
	* @return:  
	* @Author: quxuan 
	* @Date: 2019/9/25 
	*/
	@ResponseBody
	@PostMapping("/save")
	public R save(UnionFundsExpendRecordDO unionFundsExpendRecord, UnionFundsExpendDetailDO detailDO){
		if(unionFundsExpendRecordService.save(unionFundsExpendRecord,detailDO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	* @Description: 办理待办任务任务
	* @Param:
	* @return:
	* @Author: quxuan
	* @Date: 2019/9/25
	*/
	@ResponseBody
	@RequestMapping("/update")
	public R update( UnionFundsExpendRecordDO unionFundsExpendRecord) {
		unionFundsExpendRecordService.update(unionFundsExpendRecord);
		return R.ok();
	}

	/**
	 * @Description: 查询工会资金的代办列表
	 * @Param:
	 * @return:
	 * @Author: quxuan
	 * @Date: 2019/9/25
	 */
	@GetMapping("/todoList")
	PageUtils todoList() {
		List<ExpendRecordVO> resultExpendVOS = unionFundsExpendRecordService.todoList();
		PageUtils pageUtils = new PageUtils(resultExpendVOS, resultExpendVOS.size());
		return pageUtils;
	}
	/**
	 * 已办任务（历史任务）
	 */
	@GetMapping("/historyTask")
	PageUtils historyTask() {
		List<ExpendRecordVO> resultExpendVOS = unionFundsExpendRecordService.historyTask();
		PageUtils pageUtils = new PageUtils(resultExpendVOS, resultExpendVOS.size());
		return pageUtils;
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String recordId){
		if(unionFundsExpendRecordService.remove(recordId)>0){
			return R.ok();
		}
		return R.error();
	}


	/** 
	* @Description: 编辑支出管理接口
	* @Param:  
	* @return:  
	* @Author: quxuan 
	* @Date: 2019/9/25 
	*/
	@ResponseBody
	@PostMapping("/edit")
	public R edit(UnionFundsExpendRecordDO unionFundsExpendRecord, UnionFundsExpendDetailDO detailDO){
		if(unionFundsExpendRecordService.edit(unionFundsExpendRecord,detailDO)){
			return R.ok();
		}
		return R.error();
	}
	
	/** 
	* @Description:  根据recordId 查询支出信息
	* @Param:  
	* @return:  
	* @Author: quxuan 
	* @Date: 2019/9/25 
	*/
	@ResponseBody
	@GetMapping("/get")
	HashedMap get(String recordId){
		HashedMap result = new HashedMap();
		result.put("success",true);
		result.put("code","0000");
		ExpendRecordVO expendRecordVO = unionFundsExpendRecordService.get(recordId);
		if(null != expendRecordVO){
			result.put("data",expendRecordVO);
		}else {
			result.put("success",false);
			result.put("code","0001");
			result.put("data",null);
		}

		return result;
	}
	
}
