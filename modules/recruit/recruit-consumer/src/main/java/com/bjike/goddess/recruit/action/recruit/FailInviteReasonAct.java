package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.FailInviteReasonAPI;
import com.bjike.goddess.recruit.bo.FailInviteReasonBO;
import com.bjike.goddess.recruit.dto.FailInviteReasonDTO;
import com.bjike.goddess.recruit.to.FailInviteReasonTO;
import com.bjike.goddess.recruit.vo.FailInviteReasonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 未邀约成功原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 14:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("failInviteReason")
public class FailInviteReasonAct {

    @Autowired
    private FailInviteReasonAPI failInviteReasonAPI;

    /**
     * 根据id查询未邀约成功原因
     *
     * @param id 未邀约成功原因唯一标识
     * @return class FailInviteReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/failinvitereason/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FailInviteReasonBO bo = failInviteReasonAPI.findById(id);
            FailInviteReasonVO vo = BeanTransform.copyProperties(bo, FailInviteReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 未邀约成功原因dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated FailInviteReasonDTO dto, BindingResult result) throws ActException {
        try {
            Long count = failInviteReasonAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 未邀约成功原因传输对象
     * @return class FailInviteReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FailInviteReasonDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FailInviteReasonBO> boList = failInviteReasonAPI.list(dto);
            List<FailInviteReasonVO> voList = BeanTransform.copyProperties(boList, FailInviteReasonVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加未邀约成功原因
     *
     * @param to 未邀约成功原因to信息
     * @return class FailInviteReasonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) FailInviteReasonTO to, HttpServletRequest request) throws ActException {
        try {
            FailInviteReasonBO bo = failInviteReasonAPI.save(to);
            FailInviteReasonVO vo = BeanTransform.copyProperties(bo, FailInviteReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除未邀约成功原因
     *
     * @param id 未邀约成功原因唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            failInviteReasonAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑未邀约成功原因
     *
     * @param to 未邀约成功原因to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) FailInviteReasonTO to) throws ActException {
        try {
            failInviteReasonAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
