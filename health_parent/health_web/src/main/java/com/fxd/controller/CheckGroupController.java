package com.fxd.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fxd.constant.MessageConstant;
import com.fxd.entity.PageResult;
import com.fxd.entity.QueryPageBean;
import com.fxd.entity.Result;
import com.fxd.pojo.CheckGroup;
import com.fxd.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author 冯晓东
 * @date 2020/7/19  17:46
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkgroupService;

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            //修改检查组数据
            checkgroupService.edit(checkGroup,checkitemIds);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }



    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer groupId) {
        try {
            List<Integer> checkItemIds = checkgroupService.findCheckItemIdsByCheckGroupId(groupId);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //根据id查找检查组
    @RequestMapping("/findById")
    public Result findById(Integer groupId) {
        try {
            CheckGroup checkGroup = checkgroupService.findById(groupId);
            return  new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    //新增检查组
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        /*Integer[] checkitemIds = (Integer[]) map.get("checkitemIds");
        CheckGroup checkGroup = (CheckGroup) map.get("checkGroup");*/
        try {
            checkgroupService.add(checkGroup, checkitemIds);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    //分页查询
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = checkgroupService.findPage(queryPageBean);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
