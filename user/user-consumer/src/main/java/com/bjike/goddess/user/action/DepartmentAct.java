package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.DepartmentAPI;
import com.bjike.goddess.user.bo.DepartmentBO;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.service.DepartmentSer;
import com.bjike.goddess.user.to.DepartmentTO;
import com.bjike.goddess.user.vo.DepartmentVO;
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
@RequestMapping("user/department")
public class DepartmentAct {

    @Autowired
    private DepartmentAPI departmentAPI;


    /**
     * 异步获取部门树结构,逐层加载,参考ztree
     *
     * @param id 通过自身id查询下层子节点,参数为空时查询最顶层
     * @return 树结构数据
     * @version v1
     */
    @GetMapping("v1/treeData")
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
     * @param departmentTO 部门bo信息
     * @return 持久化的部门信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(DepartmentTO departmentTO) throws ActException {
        try {
            DepartmentVO vo = BeanTransform.copyProperties(departmentAPI.save(departmentTO),DepartmentVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除部门(如该节点存在子节点,先删除子节点)
     *
     * @param id 部门唯一标示
     * @return
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            departmentAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 编辑部门信息
     *
     * @return
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(DepartmentTO departmentTO) throws ActException {
        try {
            departmentAPI.update(departmentTO);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}