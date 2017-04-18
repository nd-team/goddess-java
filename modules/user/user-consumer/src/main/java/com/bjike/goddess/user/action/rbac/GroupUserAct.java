package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.GroupUserAPI;
import com.bjike.goddess.user.bo.rbac.GroupUserBO;
import com.bjike.goddess.user.to.rbac.GroupUserTO;
import com.bjike.goddess.user.vo.rbac.GroupUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组用户操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("group-user")
public class GroupUserAct {

    @Autowired
    private GroupUserAPI groupUserAPI;

    /**
     * 添加组用户
     *
     * @param groupUserTO 组用户信息
     * @return class GroupUserVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) GroupUserTO groupUserTO) throws ActException {
        try {
            GroupUserBO groupUserBO = groupUserAPI.saveByTO(groupUserTO);
            return ActResult.initialize(BeanTransform.copyProperties(groupUserBO, GroupUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
