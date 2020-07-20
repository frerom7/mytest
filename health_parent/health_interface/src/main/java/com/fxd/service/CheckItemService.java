package com.fxd.service;

import com.fxd.entity.PageResult;
import com.fxd.entity.QueryPageBean;
import com.fxd.pojo.CheckItem;

import java.util.List;

/**
 * @author 冯晓东
 * @date 2020/7/18  17:03
 */
public interface CheckItemService {

    //分页查询
    PageResult findPage(QueryPageBean queryPageBean);

    void add(CheckItem checkItem);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();

}
