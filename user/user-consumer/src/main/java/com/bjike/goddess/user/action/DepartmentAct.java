package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.DepartmentBO;
import com.bjike.goddess.user.service.DepartmentAPI;
import com.bjike.goddess.user.vo.DepartmentVO;
import com.dounine.japi.common.springmvc.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("{version}/user/department")
public class DepartmentAct {

    @Autowired
    private DepartmentAPI departmentAPI;


    /**
     * 异步获取部门树结构,逐层加载,参考ztree
     *
     * @param id 通过自身id查询下层子节点,参数为空时查询最顶层
     * @return 树结构数据
     */
    @ApiVersion(1)
    @GetMapping("treeData")
    public Result treeData(String id) throws ActException {
        try {
            List<DepartmentVO> vos = BeanTransform.copyProperties(departmentAPI.treeData(id), DepartmentVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加部门
     *
     * @param bo 部门bo信息
     * @return 持久化的部门信息
     */
    @ApiVersion(1)
    @PostMapping("add")
    public Result add(DepartmentBO bo) throws ActException {
        try {
            return ActResult.initialize(departmentAPI.saveByBO(bo));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除部门(如该节点存在子节点,先删除子节点)
     *
     * @param id 部门唯一标示
     * @return
     */
    @ApiVersion(1)
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            departmentAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}