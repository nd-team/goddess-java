package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.NotEntryReasonAPI;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import com.bjike.goddess.recruit.vo.FailFirstInterviewReasonVO;
import com.bjike.goddess.recruit.vo.NotEntryReasonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 16:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("notEntryReason")
public class NotEntryReasonAct {

    @Autowired
    private NotEntryReasonAPI notEntryReasonAPI;

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

            Boolean isHasPermission = notEntryReasonAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询未入职原因
     *
     * @param id 未入职原因唯一标识
     * @return class NotEntryReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/notEntryReason/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            NotEntryReasonBO bo = notEntryReasonAPI.findById(id);
            NotEntryReasonVO vo = BeanTransform.copyProperties(bo, NotEntryReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 未入职原因dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated NotEntryReasonDTO dto, BindingResult result) throws ActException {
        try {
            Long count = notEntryReasonAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 未入职原因dto
     * @return class NotEntryReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated NotEntryReasonDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<NotEntryReasonBO> boList = notEntryReasonAPI.list(dto);
            List<NotEntryReasonVO> voList = BeanTransform.copyProperties(boList, NotEntryReasonVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加未入职原因
     *
     * @param to 未入职原因to信息
     * @return class NotEntryReasonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) NotEntryReasonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            NotEntryReasonBO bo = notEntryReasonAPI.save(to);
            NotEntryReasonVO vo = BeanTransform.copyProperties(bo, NotEntryReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除未入职原因
     *
     * @param id 未入职原因唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            notEntryReasonAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑未入职原因
     *
     * @param to 未入职原因to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) NotEntryReasonTO to, BindingResult result) throws ActException {
        try {
            notEntryReasonAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有未入职原因
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allReason")
    public Result allReason() throws ActException {
        try {
            return ActResult.initialize(notEntryReasonAPI.allReason());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
