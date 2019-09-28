package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.CompanyMgtDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.CompanyMgtService;
import com.bootdo.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao sysDeptMapper;

    @Autowired
    private CompanyMgtService companyMgtService;

    @Override
    public DeptDO get(Long deptId) {
        return sysDeptMapper.get(deptId);
    }

    @Override
    public List<DeptDO> list(Map<String, Object> map) {
        return sysDeptMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysDeptMapper.count(map);
    }

    @Override
    public int save(DeptDO sysDept) {
        return sysDeptMapper.save(sysDept);
    }

    @Override
    public int update(DeptDO sysDept) {
        return sysDeptMapper.update(sysDept);
    }

    @Override
    public int remove(Long deptId) {
        return sysDeptMapper.remove(deptId);
    }

    @Override
    public int batchRemove(Long[] deptIds) {
        return sysDeptMapper.batchRemove(deptIds);
    }

    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String, Object>(16));
        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public boolean checkDeptHasUser(Long deptId) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        int result = sysDeptMapper.getDeptUserNumber(deptId);
        return result == 0;
    }

    @Override
    public List<Long> listChildrenIds(Long parentId) {
        List<DeptDO> deptDOS = list(null);
        return treeMenuList(deptDOS, parentId);
    }

    List<Long> treeMenuList(List<DeptDO> menuList, long pid) {
        List<Long> childIds = new ArrayList<>();
        for (DeptDO mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId() == pid) {
                //递归遍历下一级
                treeMenuList(menuList, mu.getDeptId());
                childIds.add(mu.getDeptId());
            }
        }
        return childIds;
    }

  /*  @Override
    public Tree<DeptDO> getCompanyAndDeptTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String, Object>());
        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees,"顶级节点");
        return t;
    }*/


    @Override
    public Tree<DeptDO> getCompanyAndDeptTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        String currentUser = ShiroUtils.getUser().getUsername();
        if("admin".equals(currentUser)){
            //超级管理员
            Map<String, Object> param = new HashMap<String, Object>();
            List<CompanyMgtDO> companyList = companyMgtService.list(param);

            List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String, Object>());

            for(CompanyMgtDO companyMgtDOTemp :companyList){
                String strCompanyId = companyMgtDOTemp.getId().toString();
                Tree<DeptDO> tree = new Tree<DeptDO>();
                tree.setId(companyMgtDOTemp.getId().toString());
                tree.setParentId("0");
                tree.setText(companyMgtDOTemp.getCompanyName());
                Map<String, Object> state = new HashMap<>(16);
                state.put("opened", true);
                tree.setState(state);
                Map<String, Object> attributes = new HashMap<String, Object> ();
                attributes.put("nodeType","copmany");;
                tree.setAttributes(attributes);
                trees.add(tree);

                for(DeptDO deptDo : sysDepts){
                    if(strCompanyId.equals(deptDo.getCompanyId()+"")){
                        Tree<DeptDO> deptTree = new Tree<DeptDO>();
                        //deptTree.setId(strCompanyId + deptDo.getDeptId().toString());
                        deptTree.setId(deptDo.getDeptId().toString());
                        if("0".equals(deptDo.getParentId().toString())){
                            deptTree.setParentId(strCompanyId);
                        }else{
                            deptTree.setParentId(deptDo.getParentId().toString());
                        }
                        deptTree.setText(deptDo.getName());
                        Map<String, Object> deptState = new HashMap<>(16);
                        deptState.put("opened", true);
                        deptTree.setState(deptState);

                        Map<String, Object> deptAttributes = new HashMap<String, Object> ();
                        deptAttributes.put("nodeType","dept");
                        deptAttributes.put("companyId",strCompanyId);
                        deptAttributes.put("companyName",companyMgtDOTemp.getCompanyName());
                        deptAttributes.put("companyLevel",companyMgtDOTemp.getCompanyLevel());
                        deptTree.setAttributes(deptAttributes);

                        trees.add(deptTree);
                    }
                }
            }

        }else{
            Long companyId = ShiroUtils.getUser().getCompanyId();
            CompanyMgtDO companyMgtDO = companyMgtService.get(companyId.intValue());
            int level = companyMgtDO.getCompanyLevel();
            String strCompanyId = companyMgtDO.getId() + "";
            List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String, Object>());
            if("0".equals(level + "")){
                //总公司
                Map<String, Object> param = new HashMap<String, Object>();
                List<CompanyMgtDO> companyList = companyMgtService.list(param);


                for(CompanyMgtDO companyMgtDOTemp :companyList){
                    String strCompanyIdTemp = companyMgtDOTemp.getId().toString();
                    Tree<DeptDO> tree = new Tree<DeptDO>();
                    tree.setId(companyMgtDOTemp.getId().toString());
                    tree.setParentId("0");
                    tree.setText(companyMgtDOTemp.getCompanyName());
                    Map<String, Object> state = new HashMap<>(16);
                    state.put("opened", true);
                    tree.setState(state);
                    Map<String, Object> attributes = new HashMap<String, Object> ();
                    attributes.put("nodeType","copmany");
                    tree.setAttributes(attributes);
                    trees.add(tree);

                    for(DeptDO deptDo : sysDepts){
                        if(strCompanyIdTemp.equals(deptDo.getCompanyId()+"")){
                            Tree<DeptDO> deptTree = new Tree<DeptDO>();
                            deptTree.setId(deptDo.getDeptId().toString());
                            if("0".equals(deptDo.getParentId().toString())){
                                deptTree.setParentId(strCompanyIdTemp);
                            }else{
                                deptTree.setParentId(deptDo.getParentId().toString());
                            }
                            deptTree.setText(deptDo.getName());
                            Map<String, Object> deptState = new HashMap<>(16);
                            deptState.put("opened", true);
                            deptTree.setState(deptState);

                            Map<String, Object> deptAttributes = new HashMap<String, Object> ();
                            deptAttributes.put("nodeType","dept");
                            deptAttributes.put("companyId",strCompanyId);
                            deptAttributes.put("companyName",companyMgtDOTemp.getCompanyName());
                            deptAttributes.put("companyLevel",companyMgtDOTemp.getCompanyLevel());
                            deptTree.setAttributes(deptAttributes);

                            trees.add(deptTree);
                        }
                    }
                }
            }else{
                //分公司

                Tree<DeptDO> tree = new Tree<DeptDO>();
                tree.setId(companyMgtDO.getId().toString());
                tree.setParentId("0");
                tree.setText(companyMgtDO.getCompanyName());
                Map<String, Object> state = new HashMap<>(16);
                state.put("opened", true);
                tree.setState(state);
                Map<String, Object> attributes = new HashMap<String, Object> ();
                attributes.put("nodeType","copmany");
                tree.setAttributes(attributes);
                trees.add(tree);

                for(DeptDO deptDo : sysDepts){
                    if(strCompanyId.equals(deptDo.getCompanyId()+"")){
                        Tree<DeptDO> deptTree = new Tree<DeptDO>();
                        deptTree.setId(deptDo.getDeptId().toString());
                        if("0".equals(deptDo.getParentId().toString())){
                            deptTree.setParentId(strCompanyId);
                        }else{
                            deptTree.setParentId(deptDo.getParentId().toString());
                        }
                        deptTree.setText(deptDo.getName());
                        Map<String, Object> deptState = new HashMap<>(16);
                        deptState.put("opened", true);
                        deptTree.setState(deptState);

                        Map<String, Object> deptAttributes = new HashMap<String, Object> ();
                        deptAttributes.put("nodeType","dept");
                        deptAttributes.put("companyId",strCompanyId);
                        deptAttributes.put("companyName",companyMgtDO.getCompanyName());
                        deptAttributes.put("companyLevel",companyMgtDO.getCompanyLevel());
                        deptTree.setAttributes(deptAttributes);

                        trees.add(deptTree);
                    }
                }
            }
        }


        /*List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String, Object>());
        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整*/
        Tree<DeptDO> t = BuildTree.build(trees,"顶级节点");
        return t;
    }
}
