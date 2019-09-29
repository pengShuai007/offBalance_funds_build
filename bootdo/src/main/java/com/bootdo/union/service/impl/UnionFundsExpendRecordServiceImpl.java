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

		// 判断之前操作的流程
		if(StringUtils.isEmpty(unionFundsExpendRecord.getRecordId())){
			Map vars = new HashMap();
			String title = ShiroUtils.getUser().getName() + "发起工会资金支出申请";
			vars.put("title", title);
			//开启工作流  传入流程的标识 , 业务主键 , 标题
			ProcessInstance processInstance = actTaskService.startProcess(ActivitiConstant.ACTIVITI_PROCESS_UNION, unionFundsExpendRecord.getRecordId(), title,
					vars);
			// 办理支出申请任务
			Task applyTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
			applyTask.setAssignee(activitiUtils.getActivitiUserId());
			taskService.complete(applyTask.getId());

			// 分配用户组
			Task nextTask = taskService.createTaskQuery()
					.processInstanceId(processInstance.getId())
					.singleResult();
			activitiUtils.addGroupToTask(nextTask);

			//保存支持记录
			unionFundsExpendRecord.setRecordId(UUIDUtils.randomUUID());
			unionFundsExpendRecord.setApplyTime(new Date());
			unionFundsExpendRecord.setCreateTime(new Date());
			unionFundsExpendRecord.setActivitiStatus(nextTask.getTaskDefinitionKey());
			unionFundsExpendRecord.setProcessInstanceId(processInstance.getId());
			if(StringUtils.isEmpty(unionFundsExpendRecord.getOutCompanyId())){
				unionFundsExpendRecord.setOutCompanyId(String.valueOf(ShiroUtils.getUser().getCompanyId()));
			}
			int r = unionFundsExpendRecordDao.save(unionFundsExpendRecord);
			//保存支出明细
			detailDO.setExpentId(UUIDUtils.randomUUID());
			detailDO.setRecordId(unionFundsExpendRecord.getRecordId());
			detailDO.setCreateTime(new Date());
			int d = unionFundsExpendDetailDao.save(detailDO);

			return r;
		}else {
			// 如果是操作的流程，判断是否有流程定义，没有进行查询
			if(StringUtils.isEmpty(unionFundsExpendRecord.getProcessInstanceId())){
				ExpendRecordVO  old = unionFundsExpendRecordDao.get(unionFundsExpendRecord.getRecordId());
				// 如果还没有查到流程定义直接返回
				if(StringUtils.isEmpty(old.getProcessInstanceId())){
					return 0;
				}
				unionFundsExpendRecord.setProcessInstanceId(old.getProcessInstanceId());
			}
			// 直接办理任务
			Task applyTask = taskService.createTaskQuery().processInstanceId(unionFundsExpendRecord.getProcessInstanceId()).singleResult();
			applyTask.setAssignee(activitiUtils.getActivitiUserId());
			taskService.complete(applyTask.getId());

			// 分配用户组
			Task nextTask = taskService.createTaskQuery()
					.processInstanceId(unionFundsExpendRecord.getProcessInstanceId())
					.singleResult();
			activitiUtils.addGroupToTask(nextTask);
			unionFundsExpendRecord.setActivitiStatus(nextTask.getTaskDefinitionKey());
			unionFundsExpendRecordDao.update(unionFundsExpendRecord);
			return 1;
		}

	}



	@Override
	public void update(UnionFundsExpendRecordDO unionFundsExpendRecord, String operateStatus){

		Map var = new HashMap();
		Task currentTask = taskService.createTaskQuery().processInstanceId(unionFundsExpendRecord.getProcessInstanceId()).taskCandidateUser(activitiUtils.getActivitiUserId()).singleResult();
		if(null == currentTask){
			return ;
		}
		taskService.claim(currentTask.getId(),activitiUtils.getActivitiUserId());
		switch (unionFundsExpendRecord.getActivitiStatus()) {
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_DEPARTMENT_REVIEW:
				if(StringUtils.isEmpty(unionFundsExpendRecord.getDepartmentReview())){
					unionFundsExpendRecord.setDepartmentReview(operateStatus);
				}
				var.put("deptartmentOP",operateStatus);
				break;
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_SUPERVISOR_APPROVAL:
				if(StringUtils.isEmpty(unionFundsExpendRecord.getSupervisorApproval())){
					unionFundsExpendRecord.setSupervisorApproval(operateStatus);
				}
				var.put("supervisorOP",operateStatus);
				break;
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_FINANCE_REVIEW:
				if(StringUtils.isEmpty(unionFundsExpendRecord.getFinanceReview())){
					unionFundsExpendRecord.setFinanceReview(operateStatus);
				}
				var.put("financeOP",operateStatus);
				break;
            case ActivitiConstant.ACTIVITI_PROCESS_UNION_CASHIER_CONFIRM:
				if(StringUtils.isEmpty(unionFundsExpendRecord.getCashierConfirm())){
					unionFundsExpendRecord.setCashierConfirm(operateStatus);
				}

				unionFundsExpendRecord.setActivitiStatus(ActivitiConstant.ACTIVITI_PROCESS_UNION_END);
				unionFundsExpendRecordDao.update(unionFundsExpendRecord);

				//工作流结束，创建分公司收入记录
				ExpendRecordVO expendRecordVO= unionFundsExpendRecordDao.get(unionFundsExpendRecord.getRecordId());

				if(StringUtils.isNotEmpty(expendRecordVO.getOutCompanyId())){
					createUnionFundsIncomeRecord(expendRecordVO);
				}
				break;
            default:
        }

		// 办理任务
		taskService.complete(currentTask.getId(), var);

		// 下一个任务,并分配用户组
		Task nextTask = taskService.createTaskQuery().processInstanceId(currentTask.getProcessInstanceId()).singleResult();
		if(null != nextTask){
			activitiUtils.addGroupToTask(nextTask);

			//更新数据库状态
			unionFundsExpendRecord.setActivitiStatus(nextTask.getTaskDefinitionKey());
			unionFundsExpendRecordDao.update(unionFundsExpendRecord);
		}

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
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(activitiUtils.getActivitiUserId()).list();
		List<Task> taskuser = taskService.createTaskQuery().taskCandidateUser(activitiUtils.getActivitiUserId()).list();
		tasks.addAll(taskuser);
		StringBuilder processInstanceIds = new StringBuilder("");
		if(tasks.size() < 1){
			return new ArrayList<>();
		}else {
			tasks.stream().forEach(task -> {
				if(StringUtils.isNotEmpty(processInstanceIds)){
					processInstanceIds.append(","+task.getProcessInstanceId());
				}else {
					processInstanceIds.append(task.getProcessInstanceId());
				}
			});
			if(StringUtils.isEmpty(processInstanceIds)){
				return new ArrayList<>();
			}

			Map query = new HashMap();
			query.put("processInstanceIds",processInstanceIds.toString());
			query.put("outCompanyId",ShiroUtils.getUser().getCompanyId()); // 当前用户id

			//支出记录，如果为空直接返回
			List<ExpendRecordVO> expendRecordDOList = unionFundsExpendRecordDao.todoList(query).stream()
					.collect(Collectors.toList());

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
		List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(activitiUtils.getActivitiUserId()).list();
		StringBuilder processInstanceIds = new StringBuilder("");
		if(tasks.size() < 1){
			return new ArrayList<>();
		}else {
			tasks.stream().forEach(task -> {
				if(StringUtils.isNotEmpty(processInstanceIds)){
					processInstanceIds.append(","+task.getProcessInstanceId());
				}else {
					processInstanceIds.append(task.getProcessInstanceId());
				}
			});

			if(StringUtils.isEmpty(processInstanceIds)){
				return new ArrayList<>();
			}

			Map query = new HashMap();
			query.put("processInstanceIds",processInstanceIds.toString());
			query.put("outCompanyId",ShiroUtils.getUser().getCompanyId()); // 当前用户id

			//支出记录，如果为空直接返回
			List<ExpendRecordVO> expendRecordDOList = unionFundsExpendRecordDao.todoList(query).stream()
					.collect(Collectors.toList());

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
	private void createUnionFundsIncomeRecord(ExpendRecordVO unionFundsExpendRecord) {
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
