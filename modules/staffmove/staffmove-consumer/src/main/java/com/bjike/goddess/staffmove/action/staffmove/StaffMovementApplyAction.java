package com.bjike.goddess.staffmove.action.staffmove;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmove.api.StaffMovementApplyAPI;
import com.bjike.goddess.staffmove.bo.StaffMovementApplyBO;
import com.bjike.goddess.staffmove.dto.StaffMovementApplyDTO;
import com.bjike.goddess.staffmove.to.StaffMovementApplyTO;
import com.bjike.goddess.staffmove.vo.StaffMovementApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
    public Result list(StaffMovementApplyDTO staffMovementApplyDTO,  HttpServletRequest request) throws ActException {
        try {
            List<StaffMovementApplyVO> staffMovementApplyVOS = BeanTransform.copyProperties
                    (staffMovementApplyAPI.findListStaffMovementApply(staffMovementApplyDTO),StaffMovementApplyVO.class,request);
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
    public Result edit(@Validated StaffMovementApplyTO staffMovementApplyTO,BindingResult bindingResult) throws ActException {
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
     * 审核
     *
     * @param staffMovementApplyTO 人员调动申请数据to
     * @return class StaffMovementApplyVO
     * @des 审核人员调动申请
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(StaffMovementApplyTO staffMovementApplyTO,HttpServletRequest request) throws ActException {
        try {
            StaffMovementApplyBO staffMovementApplyBO = staffMovementApplyAPI.auditStaffMovementApply(staffMovementApplyTO);
            return ActResult.initialize(staffMovementApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}