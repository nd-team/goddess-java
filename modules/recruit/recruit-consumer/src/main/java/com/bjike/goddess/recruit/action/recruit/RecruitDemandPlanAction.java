package com.bjike.goddess.recruit.action.recruit;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.recruit.api.RecruitDemandPlanAPI;
import com.bjike.goddess.recruit.bo.RecruitDemandPlanBO;
import com.bjike.goddess.recruit.dto.RecruitDemandPlanDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandPlanTO;
import com.bjike.goddess.recruit.vo.RecruitDemandPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 招聘需求与计划
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘需求与计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recruitdemandplan")
public class RecruitDemandPlanAction {
    @Autowired
    private RecruitDemandPlanAPI recruitDemandPlanAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private ModuleAPI moduleAPI;

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

            Boolean isHasPermission = recruitDemandPlanAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询招聘需求与计划
     *
     * @param id 招聘需求与计划唯一标识
     * @return class RecruitDemandPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/duty/{id}")
    public Result duty(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecruitDemandPlanBO bo = recruitDemandPlanAPI.getId(id);
            RecruitDemandPlanVO vo = BeanTransform.copyProperties(bo, RecruitDemandPlanVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 招聘需求与计划dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RecruitDemandPlanDTO dto, BindingResult result) throws ActException {
        try {
            Long count = recruitDemandPlanAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 招聘需求与计划dto
     * @return class RecruitDemandPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RecruitDemandPlanDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RecruitDemandPlanBO> boList = recruitDemandPlanAPI.list(dto);
            List<RecruitDemandPlanVO> voList = BeanTransform.copyProperties(boList, RecruitDemandPlanVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招聘需求与计划
     *
     * @param to 招聘需求与计划o信息
     * @return class RecruitDemandPlanVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RecruitDemandPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RecruitDemandPlanBO bo = recruitDemandPlanAPI.save(to);
            RecruitDemandPlanVO vo = BeanTransform.copyProperties(bo, RecruitDemandPlanVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招聘需求与计划
     *
     * @param to 招聘需求与计划to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RecruitDemandPlanTO to, BindingResult result) throws ActException {
        try {
            RecruitDemandPlanBO bo = recruitDemandPlanAPI.update(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RecruitDemandPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 制定招聘计划
     *
     * @param to 制定招聘计划
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/makePlan")
    public Result makePlan(@Validated(value = {EDIT.class}) RecruitDemandPlanTO to, BindingResult result) throws ActException {
        try {
            RecruitDemandPlanBO bo = recruitDemandPlanAPI.makePlan(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RecruitDemandPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招聘需求与计划
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/remove/{id}")
    public Result remove(@PathVariable String id) throws ActException {
        try {
            recruitDemandPlanAPI.remove(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有地区
     *
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有部门项目组
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDepartment")
    public Result findDepartment(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有岗位
     *
     * @return class PositionDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findPosition")
    public Result findPosition(HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = positionDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}