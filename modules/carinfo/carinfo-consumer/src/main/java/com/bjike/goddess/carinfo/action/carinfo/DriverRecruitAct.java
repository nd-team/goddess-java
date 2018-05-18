package com.bjike.goddess.carinfo.action.carinfo;

import com.bjike.goddess.carinfo.api.DriverRecruitAPI;
import com.bjike.goddess.carinfo.bo.DriverRecruitBO;
import com.bjike.goddess.carinfo.dto.DriverRecruitDTO;
import com.bjike.goddess.carinfo.to.DriverRecruitTO;
import com.bjike.goddess.carinfo.to.GuidePermissionTO;
import com.bjike.goddess.carinfo.vo.DriverRecruitVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 司机招聘信息
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 08:27 ]
 * @Description: [ 司机招聘信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("driverrecruit")
public class DriverRecruitAct {

    @Autowired
    private DriverRecruitAPI driverRecruitAPI;

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

            Boolean isHasPermission = driverRecruitAPI.guidePermission(guidePermissionTO);
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
     * 新增
     *
     * @param to 司机信息
     * @return class DriverRecruitVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DriverRecruitTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            driverRecruitAPI.save(to);
            return new ActResult("新增成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 司机信息
     * @return class DriverRecruitVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DriverRecruitTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DriverRecruitVO vo = BeanTransform.copyProperties(driverRecruitAPI.edit(to), DriverRecruitVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id Id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            driverRecruitAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DriverRecruitDTO dto) throws ActException {
        try {
            Long count = driverRecruitAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询
     *
     * @param id Id
     * @return class DriverRecruitVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DriverRecruitBO driverRecruitBO = driverRecruitAPI.findOne(id);
            DriverRecruitVO vo = BeanTransform.copyProperties(driverRecruitBO,DriverRecruitVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 查询条件或分页条件
     * @return class DriverRecruitVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(DriverRecruitDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DriverRecruitBO> boList = driverRecruitAPI.pageList(dto);
            List<DriverRecruitVO> voList = BeanTransform.copyProperties(boList, DriverRecruitVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param id      id
     * @param suggest 审核意见
     * @param audit   审核结果
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/audit/{id}")
    public Result audit(@PathVariable String id, @RequestParam String suggest, @RequestParam Boolean audit) throws ActException {
        try {
            driverRecruitAPI.audit(id, suggest, audit);
            return new ActResult("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}