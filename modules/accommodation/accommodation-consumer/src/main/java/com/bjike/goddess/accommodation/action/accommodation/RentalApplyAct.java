
package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.RentalApplyAPI;
import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.entity.RentalApply;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.accommodation.vo.RentalApplyVO;
import com.bjike.goddess.accommodation.vo.RentalPreceptVO;
import com.bjike.goddess.accommodation.vo.RentalVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 租房申请业务
 *
 * @Author: [xiazhili]
 * @Date: [17-3-14]
 * @Description: [租房申请业务]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */


@RestController
@RequestMapping("rentalApply")
public class RentalApplyAct extends BaseFileAction{
    @Autowired
    private RentalApplyAPI rentalApplyAPI;
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

            Boolean isHasPermission = rentalApplyAPI.guidePermission(guidePermissionTO);
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
     * 租房申请列表总条数
     *
     * @param rentalApplyDTO 租房申请记录dto
     * @des 获取所有租房申请
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RentalApplyDTO rentalApplyDTO) throws ActException {
        try {
            Long count = rentalApplyAPI.count(rentalApplyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个租房申请
     *
     * @param id
     * @return class RentalApplyVO
     * @des 获取一个租房申请
     * @version v1
     */
    @GetMapping("v1/apply/{id}")
    public Result apply(@PathVariable String id) throws ActException {
        try {
            RentalApplyBO bo = rentalApplyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 租房申请信息列表
     *
     * @param rentalApplyDTO 租房申请dto
     * @return class RentalApplyVO
     * @des 租房申请信息列表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RentalApplyDTO rentalApplyDTO, HttpServletRequest request) throws ActException {
        try {
            List<RentalApplyVO> rentalApplyVOS = BeanTransform.copyProperties(
                    rentalApplyAPI.findListRentalApply(rentalApplyDTO), RentalApplyVO.class,request);
            return ActResult.initialize(rentalApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加租房申请
     *
     * @param rentalApplyTO 租房申请to
     * @return class RentalApplyVO
     * @des 添加租房申请
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addApply(@Validated({ADD.class}) RentalApplyTO rentalApplyTO, BindingResult bindingResult) throws ActException {
        try {
            RentalApplyBO applyBO = rentalApplyAPI.insertApply(rentalApplyTO);
            return ActResult.initialize(applyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑租房申请
     *
     * @param rentalApplyTO 租房申请数据bo
     * @return class RentalApplyVO
     * @des 编辑租房申请
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editApply(@Validated(EDIT.class) RentalApplyTO rentalApplyTO,BindingResult bindingResult) throws ActException {
        try {
            RentalApplyBO rentalApplyBO = rentalApplyAPI.editApply(rentalApplyTO);
            return ActResult.initialize(rentalApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据用户id删除租房申请记录
     *
     * @param id 用户id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteApply(@PathVariable String id) throws ActException {
        try {
            rentalApplyAPI.removeApply(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 商务发展部意见
     *
     * @param rentalApplyTO 租房方案数据bo
     * @return class RentalApplyVO
     * @des 商务发展部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/businessAudit")
    public Result businessAudit(@Validated(RentalApplyTO.TestBusiness.class) RentalApplyTO rentalApplyTO) throws ActException {
        try {
            RentalApplyBO bo = rentalApplyAPI.businessAudit(rentalApplyTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalApplyVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 运营财务部意见
     *
     * @param rentalApplyTO 租房方案数据bo
     * @return class RentalApplyVO
     * @des 运营财务部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/financeAudit")
    public Result financeAudit(@Validated(RentalApplyTO.TestFinance.class) RentalApplyTO rentalApplyTO) throws ActException {
        try {
            RentalApplyBO bo = rentalApplyAPI.financeAudit(rentalApplyTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalApplyVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 综合资源部意见
     *
     * @param rentalApplyTO 租房方案数据bo
     * @return class RentalApplyVO
     * @des 综合资源部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/resourceAudit")
    public Result resourceAudit(@Validated(RentalApplyTO.TestResource.class) RentalApplyTO rentalApplyTO) throws ActException {
        try {
            RentalApplyBO bo = rentalApplyAPI.resourceAudit(rentalApplyTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalApplyVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param rentalApplyTO 租房方案数据bo
     * @return class RentalApplyVO
     * @des 项目经理审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/manageAudit")
    public Result manageAudit(@Validated(RentalApplyTO.TestManage.class) RentalApplyTO rentalApplyTO) throws ActException {
        try {
            RentalApplyBO bo = rentalApplyAPI.manageAudit(rentalApplyTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
//    /**
//     * 租房信息
//     *
//     * @param to 租房申请数据to
//     * @return class RentalVO
//     * @des 租房信息
//     * @version v1
//     */
//    @PostMapping("v1/rentInfo")
//    public Result rentInfo(@Validated(RentalApplyTO.TestManage.class) RentalApplyTO to) throws ActException {
//        try {
//            RentalBO rentalBO = rentalApplyAPI.rentInfo(to);
//            return ActResult.initialize(BeanTransform.copyProperties(rentalBO, RentalVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 导出excel
     *
     * @param dto 租房申请
     * @des 导出租房申请
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(RentalApplyDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "租房申请.xlsx";
            super.writeOutFile(response, rentalApplyAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    @RequestMapping("v1/hello")
    public String hello(){
        return "hello";
    }

//    public ModelAndView hello(){
//        return new ModelAndView("hello");
//    }

}

