package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.rbac.GroupBO;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.service.rbac.GroupAPI;
import com.bjike.goddess.user.vo.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 异步获取组树结构,逐层加载,参考ztree
     *
     * @param id 通过自身id查询下层子节点,参数为空时查询最顶层
     * @return 树结构数据
     * @throws ActException
     */
    @GetMapping("treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            List<GroupVO> vos =  BeanTransform.copyProperties(groupAPI.treeData(id),GroupVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加组
     *
     * @param bo 新的组信息
     * @return 持久化的组信息
     * @throws ActException
     */
    @PostMapping("add")
    public ActResult add(GroupBO bo) throws ActException {
        try {
            return ActResult.initialize(groupAPI.saveByBO(bo));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除组(如该节点存在子节点,先删除子节点)
     *
     * @param id 组唯一标示
     * @return
     * @throws ActException
     */
    @DeleteMapping("delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            groupAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
