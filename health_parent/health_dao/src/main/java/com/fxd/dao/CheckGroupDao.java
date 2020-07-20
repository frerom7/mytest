package com.fxd.dao;

import com.fxd.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 冯晓东
 * @date 2020/7/19  17:52
 */
public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(@Param("checkgroupId") Integer checkgroupId, @Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer groupId);

    void edit(CheckGroup checkGroup);

    void deleteAssociation(Integer groupId);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    int findCountByCheckGroupIdT(Integer groupId);

    void deleteById(Integer groupId);
}
