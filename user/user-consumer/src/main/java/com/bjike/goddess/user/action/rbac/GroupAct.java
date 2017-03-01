package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.service.rbac.GroupAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 组操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 10:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("rbac/group")
public class GroupAct {
    @Autowired
    private GroupAPI groupAPI;

    @GetMapping("treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            return ActResult.initialize(groupAPI.treeData(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("add")
    public ActResult add(Group group) throws ActException {
        try {
            return ActResult.initialize(groupAPI.save(group));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            groupAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
