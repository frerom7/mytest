package com.fxd.service;

import com.fxd.entity.PageResult;
import com.fxd.entity.QueryPageBean;
import com.fxd.pojo.CheckGroup;

import java.util.HashMap;
import java.util.List;

/**
 * @author 冯晓东
 * @date 2020/7/19  17:48
 */
public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer groupId);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);


}
