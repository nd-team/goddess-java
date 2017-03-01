package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.session.Session;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.service.DepartmentAPI;
import com.bjike.goddess.user.sto.DepartmentSTO;
import com.bjike.goddess.user.sto.DepartmentTreeSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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


    @GetMapping("treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            List<DepartmentTreeSTO> departments = departmentAPI.treeData(id);
            return ActResult.initialize(departments);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("add")
    public ActResult add(Department department) throws ActException {
        try {
            department.setCreateTime(LocalDateTime.now());
            return ActResult.initialize(departmentAPI.save(department));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            departmentAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}