package com.bootdo.union.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.union.dao.UnionFundsExpendRecordDao;
import com.bootdo.union.domain.UnionFundsExpendRecordDO;
import com.bootdo.union.service.UnionFundsExpendRecordService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UnionFundsExpendRecordServiceImpl implements UnionFundsExpendRecordService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private IdentityService identityService;

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
	
	@Override
	public UnionFundsExpendRecordDO get(Long id){
		return unionFundsExpendRecordDao.get(id);
	}
	
	@Override
	public List<UnionFundsExpendRecordDO> list(Map<String, Object> map){
		return unionFundsExpendRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return unionFundsExpendRecordDao.count(map);
	}
	
	@Override
	public int save(UnionFundsExpendRecordDO unionFundsExpendRecord){
		//生成支出记录id
		unionFundsExpendRecord.setRecordId(UUIDUtils.randomUUID());
		int r = unionFundsExpendRecordDao.save(unionFundsExpendRecord);

		//用来设置启动流程的人员ID 引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(ShiroUtils.getUserId().toString());

		Map vars = new HashMap();
		String title = ShiroUtils.getUser().getName() + "发起工会资金支出申请";
		vars.put("title", title);
		ProcessInstance processInstance = actTaskService.startProcess(ActivitiConstant.ACTIVITI_PROCESS_UNION, unionFundsExpendRecord.getRecordId(), title,
				vars);
		return r;
	}
	
	@Override
	public int update(UnionFundsExpendRecordDO unionFundsExpendRecord, String taskId){
//		String taskKey = taskService.createTaskQuery().taskId(taskId).singleResult().getTaskDefinitionKey();
//        LeaveDO leaveDO = new LeaveDO();
//        switch (taskKey) {
//            case ActivitiConstant.ACTIVITI_PROCESS_UNION_DEPARTMENT_REVIEW:
//                leaveDO.setDirectOpinion(leave.getUserOpinion());
//            case ActivitiConstant.ACTIVITI_PROCESS_UNION_SUPERVISOR_APPROVAL:
//                leaveDO.setLeaderOpinion(leave.getUserOpinion());
//            case ActivitiConstant.ACTIVITI_PROCESS_UNION_FINANCE_REVIEW:
//                leaveDO.setHrOpinion(leave.getUserOpinion());
//            case ActivitiConstant.ACTIVITI_PROCESS_UNION_CASHIER_CONFIRM:
//                leaveDO.setHrOpinion(leave.getUserOpinion());
//            default:
//        }
//        int r = leaveDao.update(leaveDO);
		actTaskService.complete(taskId, new HashMap<String, Object>() {{
			put("pass", true);
		}});
		return 1;
	}
	
	@Override
	public int remove(Long id){
		return unionFundsExpendRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return unionFundsExpendRecordDao.batchRemove(ids);
	}
	
}
