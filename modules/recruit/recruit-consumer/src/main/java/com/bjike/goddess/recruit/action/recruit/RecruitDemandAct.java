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
import com.bjike.goddess.recruit.api.RecruitDemandAPI;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandTO;
import com.bjike.goddess.recruit.vo.RecruitDemandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 16:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruitDemand")
public class RecruitDemandAct {

    @Autowired
    private RecruitDemandAPI recruitDemandAPI;
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

            Boolean isHasPermission = recruitDemandAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询招聘需求
     *
     * @param id 招聘需求唯一标识
     * @return class RecruitDemandVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/recruitDemand/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecruitDemandBO bo = recruitDemandAPI.findById(id);
            RecruitDemandVO vo = BeanTransform.copyProperties(bo, RecruitDemandVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 招聘需求dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RecruitDemandDTO dto, BindingResult result) throws ActException {
        try {
            Long count = recruitDemandAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 招聘需求dto
     * @return class RecruitDemandVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated RecruitDemandDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RecruitDemandBO> boList = recruitDemandAPI.list(dto);
            List<RecruitDemandVO> voList = BeanTransform.copyProperties(boList, RecruitDemandVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招聘需求
     *
     * @param to 招聘需求to信息
     * @return class RecruitDemandVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RecruitDemandTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RecruitDemandBO bo = recruitDemandAPI.save(to);
            RecruitDemandVO vo = BeanTransform.copyProperties(bo, RecruitDemandVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除招聘需求
     *
     * @param id 招聘需求唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recruitDemandAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招聘需求
     *
     * @param to 招聘需求to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RecruitDemandTO to, BindingResult result) throws ActException {
        try {
            recruitDemandAPI.update(to);
            return new ActResult("edit success!");
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
