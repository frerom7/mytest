package com.fxd.dao;

import com.fxd.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author 冯晓东
 * @date 2020/7/18  17:05
 */
public interface CheckItemDao {
    //分页查询
    Page<CheckItem> selectByCondition(String queryString);

    void add(CheckItem checkItem);

    int getCountByCheckItemId(Integer id);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();

}
