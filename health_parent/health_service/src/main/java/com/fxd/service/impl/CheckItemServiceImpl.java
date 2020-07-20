package com.fxd.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fxd.constant.MessageConstant;
import com.fxd.dao.CheckItemDao;
import com.fxd.entity.PageResult;
import com.fxd.entity.QueryPageBean;
import com.fxd.pojo.CheckItem;
import com.fxd.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 冯晓东
 * @date 2020/7/18  17:02
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;


    //分页查询，企业一直再用pageHelper
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //设置分页参数
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //需要分页的语句
        Page<CheckItem> checkItemPage = checkItemDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(checkItemPage.getTotal(),checkItemPage.getResult());

    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public void deleteById(Integer id) {
        //判断改检查项是否有关联
        int total = checkItemDao.getCountByCheckItemId(id);
        //若有关联项目，则不能删除
        if (total > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_GROUP_FAIL);
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

//    根据Id查找检查项
    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);

    }

    //查询所有检查项
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
