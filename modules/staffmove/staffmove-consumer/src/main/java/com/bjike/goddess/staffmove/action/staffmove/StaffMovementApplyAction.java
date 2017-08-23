package com.bjike.goddess.staffmove.action.staffmove;

import com.bjike.goddess.assemble.api.ModuleAPI;
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
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.staffmove.api.StaffMovementApplyAPI;
import com.bjike.goddess.staffmove.bo.StaffMovementApplyBO;
import com.bjike.goddess.staffmove.dto.StaffMovementApplyDTO;
import com.bjike.goddess.staffmove.excel.SonPermissionObject;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMovementApplyTO;
import com.bjike.goddess.staffmove.vo.StaffMovementApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 人员调动申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-22 04:40 ]
 * @Description: [ 人员调动申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffmovementapply")
public class StaffMovementApplyAction {
    @Autowired
    private StaffMovementApplyAPI staffMovementApplyAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private ModuleAPI moduleAPI;
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
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
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
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = staffMovementApplyAPI.sonPermission();
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

            Boolean isHasPermission = staffMovementApplyAPI.guidePermission(guidePermissionTO);
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
     * 人员调动申请列表总条数
     *
     * @param staffMovementApplyDTO 人员调动申请dto
     * @des 获取所有人员调动申请总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StaffMovementApplyDTO staffMovementApplyDTO) throws ActException {
        try {
            Long count = staffMovementApplyAPI.countStaffMovementApply(staffMovementApplyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个人员调动申请
     *
     * @param id
     * @return class StaffMovementApplyVO
     * @des 获取一个人员调动申请
     * @version v1
     */
    @GetMapping("v1/move/{id}")
    public Result move(@PathVariable String id) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(staffMovementApplyBO, StaffMovementApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 人员调动申请列表
     *
     * @param staffMovementApplyDTO 人员调动申请dto
     * @return class StaffMovementApplyVO
     * @des 获取所有人员调动申请
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(StaffMovementApplyDTO staffMovementApplyDTO, HttpServletRequest request) throws ActException {
        try {
            List<StaffMovementApplyVO> staffMovementApplyVOS = BeanTransform.copyProperties
                    (staffMovementApplyAPI.findListStaffMovementApply(staffMovementApplyDTO), StaffMovementApplyVO.class, request);
            return ActResult.initialize(staffMovementApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加人员调动申请
     *
     * @param staffMovementApplyTO 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 添加人员调动申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) StaffMovementApplyTO staffMovementApplyTO, BindingResult bindingResult) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.insertStaffMovementApply(staffMovementApplyTO);
            return ActResult.initialize(staffMovementApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑人员调动申请
     *
     * @param staffMovementApplyTO 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 编辑人员调动申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) StaffMovementApplyTO staffMovementApplyTO, BindingResult bindingResult) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.editStaffMovementApply(staffMovementApplyTO);
            return ActResult.initialize(staffMovementApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除人员调动申请
     *
     * @param id 用户id
     * @des 根据用户id删除人员调动申请记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            staffMovementApplyAPI.removeStaffMovementApply(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划模块审核
     *
     * @param to 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 规划模块审核
     * @version v1
     */
    @PostMapping("v1/planAudit")
    public Result planAudit(@Validated(StaffMovementApplyTO.planAudit.class) StaffMovementApplyTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.planAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(staffMovementApplyBO, StaffMovementApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预算模块审核
     *
     * @param to 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 预算模块审核
     * @version v1
     */
    @PostMapping("v1/budgetAudit")
    public Result budgetAudit(@Validated(StaffMovementApplyTO.budgetAudit.class) StaffMovementApplyTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.budgetAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(staffMovementApplyBO, StaffMovementApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 原决策层审核
     *
     * @param to 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 原决策层审核
     * @version v1
     */
    @PostMapping("v1/originalAudit")
    public Result originalAudit(@Validated(StaffMovementApplyTO.originalAudit.class) StaffMovementApplyTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.originalAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(staffMovementApplyBO, StaffMovementApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 调往决策层审核
     *
     * @param to 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 调往决策层审核
     * @version v1
     */
    @PostMapping("v1/transferAudit")
    public Result transferAudit(@Validated(StaffMovementApplyTO.transferAudit.class) StaffMovementApplyTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.transferAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(staffMovementApplyBO, StaffMovementApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param to 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 总经办审核
     * @version v1
     */
    @PostMapping("v1/generalAudit")
    public Result generalAudit(@Validated(StaffMovementApplyTO.generalAudit.class) StaffMovementApplyTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.generalAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(staffMovementApplyBO, StaffMovementApplyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询地区
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = departmentDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的岗位详细
     *
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/position")
    public Result position(HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = positionDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}