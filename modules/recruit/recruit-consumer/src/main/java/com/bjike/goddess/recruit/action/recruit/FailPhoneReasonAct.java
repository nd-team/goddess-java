package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.FailPhoneReasonAPI;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.vo.FailPhoneReasonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 14:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("failPhoneReason")
public class FailPhoneReasonAct {

    @Autowired
    private FailPhoneReasonAPI failPhoneReasonAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = failPhoneReasonAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id查询未成功通话原因
     *
     * @param id 未成功通话原因唯一标识
     * @return class FailPhoneReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/failPhoneReason/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FailPhoneReasonBO bo = failPhoneReasonAPI.findById(id);
            FailPhoneReasonVO vo = BeanTransform.copyProperties(bo, FailPhoneReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 未成功通话原因dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated FailPhoneReasonDTO dto, BindingResult result) throws ActException {
        try {
            Long count = failPhoneReasonAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 未成功通话原因dto
     * @return class FailPhoneReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated FailPhoneReasonDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<FailPhoneReasonBO> boList = failPhoneReasonAPI.list(dto);
            List<FailPhoneReasonVO> voList = BeanTransform.copyProperties(boList, FailPhoneReasonVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加未成功通话原因
     *
     * @param to 未成功通话原因to信息
     * @return class FailPhoneReasonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) FailPhoneReasonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            FailPhoneReasonBO bo = failPhoneReasonAPI.save(to);
            FailPhoneReasonVO vo = BeanTransform.copyProperties(bo, FailPhoneReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除未成功通话原因
     *
     * @param id 未成功通话原因唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            failPhoneReasonAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑未成功通话原因
     *
     * @param to 未成功通话原因to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) FailPhoneReasonTO to, BindingResult result) throws ActException {
        try {
            failPhoneReasonAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有未成功通话原因
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allReason")
    public Result allReason() throws ActException {
        try {
            return ActResult.initialize(failPhoneReasonAPI.allReason());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
}
