package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.GroupAPI;
import com.bjike.goddess.user.to.rbac.GroupTO;
import com.bjike.goddess.user.vo.rbac.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * 获取组树结构
     *
     * @param id id不为空时查询下层子节点,参数为空时查询最顶层
     * @des 逐层加载, 参考ztree
     * @version v1
     */
    @GetMapping("v1/treeData")
    public Result treeData(String id) throws ActException {
        try {
            List<GroupVO> vos = BeanTransform.copyProperties(groupAPI.treeData(id), GroupVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加组
     *
     * @param groupTO 新的组信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) GroupTO groupTO) throws ActException {
        try {
            GroupVO vo = BeanTransform.copyProperties(groupAPI.save(groupTO), GroupVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除组
     *
     * @param id 组唯一标示
     * @des 如该节点存在子节点, 先删除子节点
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            groupAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
