package com.fxd.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fxd.constant.MessageConstant;
import com.fxd.dao.CheckGroupDao;
import com.fxd.entity.PageResult;
import com.fxd.entity.QueryPageBean;
import com.fxd.pojo.CheckGroup;
import com.fxd.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 冯晓东
 * @date 2020/7/19  17:50
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组方法
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //先添加检查组，返回设置的id
        checkGroupDao.add(checkGroup);
        //根据拿到的检查组id将其关联的检查项设置到关联表中
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    //分页查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //设置分页参数
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //需要分页的语句
        Page<CheckGroup> checkGroupPage = checkGroupDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(checkGroupPage.getTotal(), checkGroupPage.getResult());

    }

    //根据id查找检查组
    @Override
    public CheckGroup findById(Integer groupId) {
        return checkGroupDao.findById(groupId);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer groupId) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(groupId);

    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //更新检查组表 update 语句
        checkGroupDao.edit(checkGroup);
        //先删除检查组关联的检查项记录 （中间表）
        checkGroupDao.deleteAssociation(checkGroup.getId());
        // 重新建立关联关系（插入中间表）
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    @Override
    public void deleteById(Integer groupId) {
        //若检查组有关联检查项则不能删除
        List<Integer> list = checkGroupDao.findCheckItemIdsByCheckGroupId(groupId);
        if (list != null && list.size() > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKGROUP_ITEM_FAIL);
        }
        //若检查项目有关联套餐则不能删除
        int count = checkGroupDao.findCountByCheckGroupIdT(groupId);
        if (count > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKGROUP_SETMEAL_FAIL);
        }
        //无关联，可以删除
        checkGroupDao.deleteById(groupId);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }


    //根据检查组id将其关联的检查项设置到关联表中
     /*
    往检查组和检查项中间表写记录(新增检查组、编辑检查组公共的代码)
     */
    public void setCheckGroupAndCheckItem(Integer groupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                //为了方便测试传入map对象
                Map<String, Integer> map = new HashMap<>();
                map.put("groupId", groupId);
                map.put("checkitemId", checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }


}
