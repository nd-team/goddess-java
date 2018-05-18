package com.bjike.goddess.staffmove.action.staffmove;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmove.api.StaffMoveImplementAAPI;
import com.bjike.goddess.staffmove.bo.StaffMoveImplementABO;
import com.bjike.goddess.staffmove.bo.StaffMoveImplementBBO;
import com.bjike.goddess.staffmove.bo.StaffMoveIntendCaseBO;
import com.bjike.goddess.staffmove.dto.StaffMoveImplementADTO;
import com.bjike.goddess.staffmove.dto.StaffMoveIntendCaseDTO;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementATO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementBTO;
import com.bjike.goddess.staffmove.to.StaffMoveIntendCaseTO;
import com.bjike.goddess.staffmove.vo.StaffMoveImplementAVO;
import com.bjike.goddess.staffmove.vo.StaffMoveIntendCaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 人员调动实施
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:39 ]
 * @Description: [ 人员调动实施 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffmoveimplementa")
public class StaffMoveImplementAAction {
    @Autowired
    private StaffMoveImplementAAPI staffMoveImplementAAPI;
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

            Boolean isHasPermission = staffMoveImplementAAPI.guidePermission(guidePermissionTO);
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
     * 人员调动实施列表总条数
     *
     * @param dto 人员调动实施dto
     * @des 获取所有人员调动实施总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StaffMoveImplementADTO dto) throws ActException {
        try {
            Long count = staffMoveImplementAAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 人员调动实施A id
     *
     * @param id id
     * @des 人员调动实施Aid
     * @version v1
     */
    @GetMapping("v1/staffA/{id}")
    public Result staffA(@PathVariable String id) throws ActException {
        try {
            StaffMoveImplementABO abo = staffMoveImplementAAPI.getOneA(id);
            return ActResult.initialize(abo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 人员调动实施B id
     *
     * @param id id
     * @des 人员调动实施B id
     * @version v1
     */
    @GetMapping("v1/staffB/{id}")
    public Result staffB(@PathVariable String id) throws ActException {
        try {
            StaffMoveImplementBBO bbo = staffMoveImplementAAPI.getOneB(id);
            return ActResult.initialize(bbo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员调动实施列表
     *
     * @param dto 人员调动实施dto
     * @return class StaffMoveImplementAVO
     * @des 获取所有人员调动实施
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(StaffMoveImplementADTO dto, HttpServletRequest request) throws ActException {
        try {
            List<StaffMoveImplementAVO> caseVOS = BeanTransform.copyProperties
                    (staffMoveImplementAAPI.list(dto), StaffMoveImplementAVO.class, request);
            return ActResult.initialize(caseVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 添加人员调动实施
//     *
//     * @param to 人员调动实施数据to
//     * @return class StaffMoveImplementAVO
//     * @des 添加人员调动实施
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(@Validated(ADD.class) StaffMoveImplementATO to, BindingResult bindingResult) throws ActException {
//        try {
//            StaffMoveImplementABO bo = staffMoveImplementAAPI.insert(to);
//            return ActResult.initialize(BeanTransform.copyProperties(bo, StaffMoveImplementAVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 编辑人员调动实施
     *
     * @param to 人员调动实施数据to
     * @return class StaffMoveImplementAVO
     * @des 编辑人员调动实施
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(StaffMoveImplementATO.TESTEDIT.class) StaffMoveImplementATO to, BindingResult bindingResult) throws ActException {
        try {
            staffMoveImplementAAPI.edit(to);
            return ActResult.initialize("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 人员调动
     *
     * @param to 人员调动实施数据to
     * @return class StaffMoveImplementAVO
     * @des 人员调动
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/staffMove")
    public Result staffMove(@Validated(EDIT.class) StaffMoveImplementATO to, BindingResult bindingResult) throws ActException {
        try {
            staffMoveImplementAAPI.staffMove(to);
            return ActResult.initialize("staffMove success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 原决策层审核
     *
     * @param to 人员调动实施数据to
     * @return class StaffMoveImplementAVO
     * @des 原决策层审核
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/originalAudit")
    public Result originalAudit(@Validated(StaffMoveImplementBTO.ORIGINAL.class) StaffMoveImplementBTO to, BindingResult bindingResult) throws ActException {
        try {
            staffMoveImplementAAPI.originalAudit(to);
            return ActResult.initialize("originalAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 调往决策层审核
     *
     * @param to 人员调动实施数据to
     * @return class StaffMoveImplementAVO
     * @des 调往决策层审核
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/transferAudit")
    public Result transferAudit(@Validated(StaffMoveImplementBTO.TRANSFER.class) StaffMoveImplementBTO to, BindingResult bindingResult) throws ActException {
        try {
            staffMoveImplementAAPI.transferAudit(to);
            return ActResult.initialize("transferAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 总经办审核
     *
     * @param to 人员调动实施数据to
     * @return class StaffMoveImplementAVO
     * @des 总经办审核
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/generalAudit")
    public Result generalAudit(@Validated(StaffMoveImplementBTO.GENERAL.class) StaffMoveImplementBTO to, BindingResult bindingResult) throws ActException {
        try {
            staffMoveImplementAAPI.generalAudit(to);
            return new ActResult("generalAudit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 是否解决需求
     *
     * @param to 人员调动实施数据to
     * @return class StaffMoveImplementAVO
     * @des 是否解决需求
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/solve")
    public Result solve(@Validated(StaffMoveImplementBTO.SOLVE.class) StaffMoveImplementBTO to, BindingResult bindingResult) throws ActException {
        try {
            staffMoveImplementAAPI.solve(to);
            return ActResult.initialize("solve success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除人员调动实施
     *
     * @param id 用户id
     * @des 根据用户id删除人员调动实施记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            staffMoveImplementAAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}