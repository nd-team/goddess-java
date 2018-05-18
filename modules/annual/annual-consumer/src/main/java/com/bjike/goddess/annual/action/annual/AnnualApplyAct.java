package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualApplyAPI;
import com.bjike.goddess.annual.dto.AnnualApplyDTO;
import com.bjike.goddess.annual.to.AnnualApplyAuditTo;
import com.bjike.goddess.annual.to.AnnualApplyTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.annual.vo.AnnualApplyVO;
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

/**
 * 年假申请
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("annualapply")
public class AnnualApplyAct {
    @Autowired
    private AnnualApplyAPI annualApplyAPI;

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

            Boolean isHasPermission = annualApplyAPI.guidePermission(guidePermissionTO);
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
     * 年假申请
     *
     * @param to 年假申请传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AnnualApplyTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.save(to), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除年假申请实体数据
     *
     * @param to 年假申请传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(AnnualApplyTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.delete(to), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 年假申请审核传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/audit/{id}")
    public Result audit(@Validated(EDIT.class) AnnualApplyAuditTo to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.audit(to), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取指定用户的申请记录
     *
     * @return class AnnualApplyVO
     * @version v1
     */
    @GetMapping("v1/findByUsername")
    public Result findByUsername(String username) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.findByUsername(username), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看年假申请记录
     *
     * @param id 年假信息ID
     * @return class AnnualApplyVO
     * @version v1
     */
    @GetMapping("v1/findByInfo/{id}")
    public Result findByInfo(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.findByInfo(id), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年假申请记录
     *
     * @param dto 年假申请数据传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AnnualApplyDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.maps(dto), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取年假申请数据
     *
     * @param id 年假申请数据id
     * @return class AnnualApplyVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.getById(id), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(annualApplyAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取申请开始时间
     *
     * @version v1
     */
    @GetMapping("v1/getStartTime")
    public Result getStartTime() throws ActException {
        try {
            return ActResult.initialize(annualApplyAPI.getStartTime());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 获取申请结束时间
//     *
//     * @version v1
//     */
//    @GetMapping("v1/getStartTime")
//    public Result getEndTime() throws ActException {
//        try {
//            if (moduleAPI.isCheck("assistance")) {
//                String userToken = (String) RpcContext.getContext().get("userToken");
//                UserBO userBO = userAPI.currentUser();
//                return ActResult.initialize(ageAssistAPI.getJobAge(userBO.getUsername()).toString());
//            } else {
//                return ActResult.initialize(null);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


}