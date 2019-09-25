package com.bootdo.union.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.union.dao.UnionFundsExpendDetailDao;
import com.bootdo.union.dao.UnionFundsExpendRecordDao;
import com.bootdo.union.dao.UnionFundsIncomeDetailDao;
import com.bootdo.union.dao.UnionFundsIncomeRecordDao;
import com.bootdo.union.domain.*;
import com.bootdo.union.service.UnionFundsExpendRecordService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UnionFundsExpendRecordServiceImpl implements UnionFundsExpendRecordService {


	@Autowired
	HistoryService historyService;

	@Autowired
	TaskService taskService;

	@Autowired
	ActTaskService actTaskService;

	@Autowired
	ActivitiUtils activitiUtils;


	@Autowired
	private UnionFundsExpendRecordDao unionFundsExpendRecordDao;

	@Autowired
	private UnionFundsExpendDetailDao unionFundsExpendDetailDao;

	@Autowired
	private UnionFundsIncomeDetailDao incomeDetailDao;

	@Autowired
	private UnionFundsIncomeRecordDao incomeRecordDao;
	
	@Override
	public ExpendRecordVO get(String recordId){
		ExpendRecordVO expendRecordVO = unionFundsExpendRecordDao.get(recordId);
		if(null != expendRecordVO){
			List<UnionFundsExpendDetailDO> expendDetailDOS = unionFundsExpendDetailDao.listByRecords(expendRecordVO.getRecordId());
			expendRecordVO.setDetailList(expendDetailDOS);
		}
		return expendRecordVO;
	}
	
	@Override
	public List<ExpendRecordVO> list(Map<String, Object> map){
		List<ExpendRecordVO> expendRecordDOList = unionFundsExpendRecordDao.list(map);
		if(CollectionUtils.isEmpty(expendRecordDOList)){
			return new ArrayList<>();
		}
		StringBuilder recordIds = new StringBuilder("");
		expendRecordDOList.stream().forEach(expendRecordDO -> {
			if(StringUtils.isEmpty(recordIds.toString())){
				recordIds.append(expendRecordDO.getRecordId());
			}else {
				recordIds.append(","+expendRecordDO.getRecordId());
			}
		});
		List<UnionFundsExpendDetailDO> expendDetailDOList = unionFundsExpendDetailDao.listByRecords(recordIds.toString());
		expendRecordDOList.stream().forEach(record ->{
			record.setDetailList(expendDetailDOList.stream().filter(detailDO -> detailDO.getRecordId().equals(record.getRecordId())).collect(Collectors.toList()));
		});
		return expendRecordDOList;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return unionFundsExpendRecordDao.count(map);
	}
	
	@Override
	public int save(UnionFundsExpendRecordDO unionFundsExpendRecord, UnionFundsExpendDetailDO detailDO){

		//保存支持记录
		unionFundsExpendRecord.setRecordId(UUIDUtils.randomUUID());
		unionFundsExpendRecord.setApplyTime(new Date());
		unionFundsExpendRecord.setCreateTime(new Date());
		int r = unionFundsExpendRecordDao.save(unionFundsExpendRecord);
		//保存支出明细
		detailDO.setExpentId(UUIDUtils.randomUUID());
		detailDO.setRecordId(unionFundsExpendRecord.getRecordId());
		detailDO.setCreateTime(new Date());
		int d = unionFundsExpendDetailDao.save(detailDO);

		// 向下级工会拨付走工作流，
		if(unionFundsExpendRecord.getType().equals("向下级工会拨付")){
			Map vars = new HashMap();
			String title = ShiroUtils.getUser().getName() + "发起工会资金支出申请";
			vars.put("title", title);
			//开启工作流
			ProcessInstance processInstance = actTaskService.startProcess(ActivitiConstant.ACTIVITI_PROCESS_UNION, unionFundsExpendRecord.getRecordId(), title,
					vars);
			//更新代办状态
			List<Task> nextTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(ActivitiConstant.ACTIVITI_PROCESS_UNION_DEPARTMENT_REVIEW).list();
			unionFundsExpendRecord.setActivitiStatus(nextTask.get(0).getTaskDefinitionKey());
			unionFundsExpendRecord.setProcessInstanceId(processInstance.getId());
			unionFundsExpendRecordDao.update(unionFundsExpendRecord);
		}
		return r;
	}



	@Override
	public void update(UnionFundsExpendRecordDO unionFundsExpendRecord){
		List<Task> currentTask = taskService.createTaskQuery().processInstanceBusinessKey(unionFundsExpendRecord.getRecordId()).taskDefinitionKey(unionFundsExpendRecord.getActivitiStatus()).list();
		if(currentTask.size() < 1){
			return;
		}
		switch (unionFundsExpendRecord.getActivitiStatus()) {
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_DEPARTMENT_REVIEW:
				unionFundsExpendRecord.setActivitiStatus(ActivitiConstant.ACTIVITI_PROCESS_UNION_SUPERVISOR_APPROVAL);
				if(StringUtils.isEmpty(unionFundsExpendRecord.getDepartmentReview())){
					unionFundsExpendRecord.setDepartmentReview("部门领导审核通过");
				}
				break;
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_SUPERVISOR_APPROVAL:
				unionFundsExpendRecord.setActivitiStatus(ActivitiConstant.ACTIVITI_PROCESS_UNION_FINANCE_REVIEW);
				if(StringUtils.isEmpty(unionFundsExpendRecord.getSupervisorApproval())){
					unionFundsExpendRecord.setSupervisorApproval("主管领导审批通过");
				}
				break;
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_FINANCE_REVIEW:
				unionFundsExpendRecord.setActivitiStatus(ActivitiConstant.ACTIVITI_PROCESS_UNION_CASHIER_CONFIRM);
				if(StringUtils.isEmpty(unionFundsExpendRecord.getFinanceReview())){
					unionFundsExpendRecord.setFinanceReview("财务审核通过");
				}
				break;
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_CASHIER_CONFIRM:
				unionFundsExpendRecord.setActivitiStatus("union_funds_end");
				if(StringUtils.isEmpty(unionFundsExpendRecord.getCashierConfirm())){
					unionFundsExpendRecord.setCashierConfirm("出纳已确认");
				}
				//工作流结束，创建分公司收入记录
				createUnionFundsIncomeRecord(unionFundsExpendRecord);
				break;
            default:
        }
        // 更新操作下一个状态
        int r = unionFundsExpendRecordDao.update(unionFundsExpendRecord);
		// 办理任务
		actTaskService.complete(currentTask.get(0).getId(), new HashMap<String, Object>() {{
			put("pass", true);
		}});
	}

	/**
	* @Description: 查询代办任务列表
	* @Param:
	* @return:
	* @Author: quxuan
	* @Date: 2019/9/25
	*/
	@Override
	public List<ExpendRecordVO> todoList() {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(ShiroUtils.getUserId().toString()).list();
		StringBuilder processInstanceIds = new StringBuilder("");
		if(tasks.size() < 1){
			return new ArrayList<>();
		}else {
			tasks.stream().filter(task -> !task.getTaskDefinitionKey().equals(ActivitiConstant.ACTIVITI_PROCESS_UNION_END)).forEach(task -> {
				if(StringUtils.isNotEmpty(processInstanceIds)){
					processInstanceIds.append(","+task.getProcessInstanceId());
				}else {
					processInstanceIds.append(task.getProcessInstanceId());
				}
			});
			Map paramsMap = new HashMap();
			paramsMap.put("processInstanceIds",processInstanceIds);
			if(StringUtils.isEmpty(processInstanceIds)){
				return new ArrayList<>();
			}

			//支出记录，如果为空直接返回
			List<ExpendRecordVO> expendRecordDOList = unionFundsExpendRecordDao.todoList(processInstanceIds.toString()).stream()
					.filter(record->!record.getActivitiStatus().equals(ActivitiConstant.ACTIVITI_PROCESS_UNION_END)).collect(Collectors.toList());

			if(CollectionUtils.isEmpty(expendRecordDOList)){
				return new ArrayList<>();
			}
			// 查询支出激励详情
			StringBuilder recordIds = new StringBuilder("");
			expendRecordDOList.stream()
					.forEach(expendRecordDO -> {
						if(StringUtils.isEmpty(recordIds.toString())){
							recordIds.append(expendRecordDO.getRecordId());
						}else {
							recordIds.append(","+expendRecordDO.getRecordId());
						}
			});
			List<UnionFundsExpendDetailDO> expendDetailDOList = unionFundsExpendDetailDao.listByRecords(recordIds.toString());
			expendRecordDOList.stream().forEach(record ->{
				record.setDetailList(expendDetailDOList.stream().filter(detailDO -> detailDO.getRecordId().equals(record.getRecordId())).collect(Collectors.toList()));
			});
			return expendRecordDOList;
		}
	}

	/**
	* @Description: 查询已办任务列表
	* @Param:
	* @return:
	* @Author: quxuan
	* @Date: 2019/9/25
	*/
	@Override
	public List<ExpendRecordVO> historyTask() {
		List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery().
				taskAssignee(ShiroUtils.getUserId().toString()).list();
		StringBuilder processInstanceIds = new StringBuilder("");
		if(tasks.size() < 1){
			return new ArrayList<>();
		}else {
			tasks.stream().filter(task -> !task.getTaskDefinitionKey().equals(ActivitiConstant.ACTIVITI_PROCESS_UNION_END)).forEach(task -> {
				if(StringUtils.isNotEmpty(processInstanceIds)){
					processInstanceIds.append(","+task.getProcessInstanceId());
				}else {
					processInstanceIds.append(task.getProcessInstanceId());
				}
			});
			Map paramsMap = new HashMap();
			paramsMap.put("processInstanceIds",processInstanceIds);
			if(StringUtils.isEmpty(processInstanceIds)){
				return new ArrayList<>();
			}
			//支出记录，如果为空直接返回
			List<ExpendRecordVO> expendRecordDOList = unionFundsExpendRecordDao.todoList(processInstanceIds.toString()).stream()
					.filter(record->record.getActivitiStatus().equals(ActivitiConstant.ACTIVITI_PROCESS_UNION_END)).collect(Collectors.toList());

			if(CollectionUtils.isEmpty(expendRecordDOList)){
				return new ArrayList<>();
			}
			// 查询支出详情
			StringBuilder recordIds = new StringBuilder("");
			expendRecordDOList.stream()
					.forEach(expendRecordDO -> {
						if(StringUtils.isEmpty(recordIds.toString())){
							recordIds.append(expendRecordDO.getRecordId());
						}else {
							recordIds.append(","+expendRecordDO.getRecordId());
						}
			});
			List<UnionFundsExpendDetailDO> expendDetailDOList = unionFundsExpendDetailDao.listByRecords(recordIds.toString());
			expendRecordDOList.stream().forEach(record ->{
				record.setDetailList(expendDetailDOList.stream().filter(detailDO -> detailDO.getRecordId().equals(record.getRecordId())).collect(Collectors.toList()));
			});
			return expendRecordDOList;
		}
	}

	@Override
	public boolean edit(UnionFundsExpendRecordDO expendRecordDO, UnionFundsExpendDetailDO detailDO) {
		expendRecordDO.setModifier(ShiroUtils.getUser().getUsername());
		expendRecordDO.setModifyTime(new Date());
		unionFundsExpendRecordDao.update(expendRecordDO);
		unionFundsExpendDetailDao.update(detailDO);
		return true;
	}

	/**
	* @Description:  如果流程结束，自动创建分公司的转入记录
	* @Param:
	* @return:
	* @Author: quxuan
	* @Date: 2019/9/25
	*/
	private void createUnionFundsIncomeRecord(UnionFundsExpendRecordDO unionFundsExpendRecord) {
		// 查询支出记录信息
		ExpendRecordVO fundsExpendRecordDO = unionFundsExpendRecordDao.get(unionFundsExpendRecord.getRecordId());

		// 创建收入记录
		UnionFundsIncomeRecordDO incomeRecordDO = new UnionFundsIncomeRecordDO();
		incomeRecordDO.setRecordId(fundsExpendRecordDO.getRecordId());
		incomeRecordDO.setAllAmount(fundsExpendRecordDO.getAllAmount());
		incomeRecordDO.setInCompanyId(fundsExpendRecordDO.getInCompanyId());
		incomeRecordDO.setInCompanyName(fundsExpendRecordDO.getInCompanyName());
		incomeRecordDO.setOutCompanyId(fundsExpendRecordDO.getOutCompanyId());
		incomeRecordDO.setOutCompanyName(fundsExpendRecordDO.getOutCompanyName());
		incomeRecordDO.setCreateTime(new Date());
		incomeRecordDO.setCreator(ShiroUtils.getUser().getUsername());
		incomeRecordDao.save(incomeRecordDO);
		// 创建明细
		UnionFundsIncomeDetailDO incomeDetailDO = new UnionFundsIncomeDetailDO();
		incomeDetailDO.setIncomeId(UUIDUtils.randomUUID());
		incomeDetailDO.setRecordId(fundsExpendRecordDO.getRecordId());
		incomeDetailDO.setAcountDate(new Date());
		incomeDetailDO.setAmount(fundsExpendRecordDO.getAllAmount());
		incomeDetailDO.setDescription(fundsExpendRecordDO.getOutCompanyName()+"拨付款项");
		incomeDetailDO.setRemarks(fundsExpendRecordDO.getOutCompanyName()+"拨付款项");
		incomeDetailDO.setType("上级工会拨付");
		incomeDetailDao.save(incomeDetailDO);
	}


	@Override
	public int remove(String id){
		return unionFundsExpendRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return unionFundsExpendRecordDao.batchRemove(ids);
	}
	
}
