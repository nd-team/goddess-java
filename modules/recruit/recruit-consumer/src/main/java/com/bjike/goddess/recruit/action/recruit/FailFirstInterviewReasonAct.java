package com.bjike.goddess.recruit.action.recruit;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.recruit.api.FailFirstInterviewReasonAPI;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.vo.FailFirstInterviewReasonVO;
import com.bjike.goddess.recruit.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 未应约初试原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 10:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("failFirstInterviewReason")
public class FailFirstInterviewReasonAct {

    @Autowired
    private FailFirstInterviewReasonAPI failFirstInterviewReasonAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i(HttpServletRequest request) throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
                if (!isHasPermission) {
                    //int code, String msg
                    obj.setFlag(false);
                } else {
                    obj.setFlag(true);
                }
                list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission(HttpServletRequest request) throws ActException {
        try {
            List<SonPermissionObject> hasPermissionList = failFirstInterviewReasonAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = failFirstInterviewReasonAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询未应约初试原因
     *
     * @param id 未应约初试原因唯一标识
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/failFirstInterviewReason/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FailFirstInterviewReasonBO bo = failFirstInterviewReasonAPI.findById(id);
            FailFirstInterviewReasonVO vo = BeanTransform.copyProperties(bo, FailFirstInterviewReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 未应约初试原因dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated FailFirstInterviewReasonDTO dto, BindingResult result) throws ActException {
        try {
            Long count = failFirstInterviewReasonAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 未应约初试原因dto
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated FailFirstInterviewReasonDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<FailFirstInterviewReasonBO> boList = failFirstInterviewReasonAPI.list(dto);
            List<FailFirstInterviewReasonVO> voList = BeanTransform.copyProperties(boList, FailFirstInterviewReasonVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加未应约初试原因
     *
     * @param to 未应约初试原因to信息
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) FailFirstInterviewReasonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            FailFirstInterviewReasonBO bo = failFirstInterviewReasonAPI.save(to);
            FailFirstInterviewReasonVO vo = BeanTransform.copyProperties(bo, FailFirstInterviewReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除未应约初试原因
     *
     * @param id 未应约初试原因唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            failFirstInterviewReasonAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑未应约初试原因
     *
     * @param to 未应约初试原因to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) FailFirstInterviewReasonTO to, BindingResult result) throws ActException {
        try {
            failFirstInterviewReasonAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有未应约初试原因
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allReason")
    public Result allReason() throws ActException {
        try {
            return ActResult.initialize(failFirstInterviewReasonAPI.allReason());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
