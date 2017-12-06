package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.businessproject.api.OutsourcBusinessContractAPI;
import com.bjike.goddess.businessproject.vo.OutsourcBusinessContractVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.OutsourProProgressManageAPI;
import com.bjike.goddess.projectprocing.bo.OutsourProProgressManageBO;
import com.bjike.goddess.projectprocing.dto.OutsourProProgressManageDTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.OutsourProProgressManageTO;
import com.bjike.goddess.projectprocing.vo.OutsourProProgressManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 外包半外包项目结算进度管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:03 ]
 * @Description: [ 外包半外包项目结算进度管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("outsourproprogressmanage")
public class OutsourProProgressManageAction {
    @Autowired
    private OutsourProProgressManageAPI outsourProProgressManageAPI;
    @Autowired
    private OutsourcBusinessContractAPI outsourcBusinessContractAPI;


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

            Boolean isHasPermission = outsourProProgressManageAPI.guidePermission(guidePermissionTO);
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
     * 外包半外包项目结算进度管理总条数
     *
     * @param outsourProProgressManageDTO 外包半外包项目结算进度管理dto
     * @des 获取所有外包半外包项目结算进度管理总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(OutsourProProgressManageDTO outsourProProgressManageDTO) throws ActException {
        try {
            Long count = outsourProProgressManageAPI.countOuts(outsourProProgressManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个外包半外包项目结算进度管理
     *
     * @param id 外包半外包项目结算进度管理id
     * @return class OutsourProProgressManageVO
     * @des 根据id获取节点表头定制
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            OutsourProProgressManageVO outsourProProgressManageVO = BeanTransform.copyProperties(
                    outsourProProgressManageAPI.getOneById(id), OutsourProProgressManageVO.class, true);
            return ActResult.initialize(outsourProProgressManageVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 外包半外包项目结算进度管理列表
     *
     * @param outsourProProgressManageDTO 外包,半外包项目结算进度管理dto
     * @return class OutsourProProgressManageVO
     * @des 获取所有外包半外包项目结算进度管理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(OutsourProProgressManageDTO outsourProProgressManageDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<OutsourProProgressManageVO> outsourProProgressManageVOS = BeanTransform.copyProperties(
                    outsourProProgressManageAPI.listOuts(outsourProProgressManageDTO), OutsourProProgressManageVO.class, request);
            return ActResult.initialize(outsourProProgressManageVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加外包半外包项目结算进度管理
     *
     * @param outsourProProgressManageTO 外包半外包项目结算进度管理数据to
     * @return class OutsourProProgressManageVO
     * @des 添加外包半外包项目结算进度管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) OutsourProProgressManageTO outsourProProgressManageTO, BindingResult bindingResult) throws ActException {
        try {
            OutsourProProgressManageBO outsourProProgressManageBO = outsourProProgressManageAPI.addOuts(outsourProProgressManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(outsourProProgressManageBO, OutsourProProgressManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑外包半外包项目结算进度管理
     *
     * @param outsourProProgressManageTO 外包半外包项目结算进度管理bo
     * @return class OutsourProProgressManageVO
     * @des 添加外包半外包项目结算进度管理
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) OutsourProProgressManageTO outsourProProgressManageTO) throws ActException {
        try {
            OutsourProProgressManageBO outsourProProgressManageBO = outsourProProgressManageAPI.editOuts(outsourProProgressManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(outsourProProgressManageBO, OutsourProProgressManageVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除外包半外包项目结算进度管理
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            outsourProProgressManageAPI.deleteOuts(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 添加编辑中供应商名称下拉值
     *
     * @des 添加编辑中供应商名称下拉值
     * @version v1
     */
    @GetMapping("v1/findSupplierName")
    public Result findSupplierName(HttpServletRequest request) throws ActException {
        try {
            List<String> name = outsourcBusinessContractAPI.findSupplierName();
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加编辑中根据供应商名称供应商编号下拉值
     *
     * @des 添加编辑中根据供应商名称供应商编号下拉值
     * @version v1
     */
    @GetMapping("v1/findSupplierNum/supplierName")
    public Result findSupplierNum(@RequestParam String supplierName, HttpServletRequest request) throws ActException {
        try {
            List<String> supplierNum = outsourcBusinessContractAPI.findSupplierNum(supplierName);
            return ActResult.initialize(supplierNum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据供应商编号获取对应数据
     *
     * @des 根据供应商编号获取对应数据
     * @version v1
     */
    @GetMapping("v1/findBySupplierNum")
    public Result findBySupplierNum(@RequestParam String supplierNum, HttpServletRequest request) throws ActException {
        try {
            List<OutsourcBusinessContractVO> outsourcBusinessContractVOS = BeanTransform.copyProperties(outsourcBusinessContractAPI.findBySupplierNum(supplierNum), OutsourcBusinessContractVO.class);
            return ActResult.initialize(outsourcBusinessContractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理意见填写
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理bo
     * @des 项目经理意见填写
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/manageOpinion")
    public Result manageOpinion(@Validated({OutsourProProgressManageTO.testManageOpinion.class}) OutsourProProgressManageTO outsourProProgressManageTO) throws ActException {
        try {
            outsourProProgressManageAPI.manageOpinion(outsourProProgressManageTO);
            return new ActResult("填写成功 ");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 回款确认
     *
     * @param outsourProProgressManageTO 外包半外包项目结算进度管理bo
     * @des 回款确认
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/receivaConfir")
    public Result receivaConfir(@Validated({OutsourProProgressManageTO.testReceivaConfir.class}) OutsourProProgressManageTO outsourProProgressManageTO) throws ActException {
        try {
            outsourProProgressManageAPI.receivaConfir(outsourProProgressManageTO);
            return new ActResult("回款确认成功 ");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 增值税发票通报
     *
     * @param outsourProProgressManageTO 外包半外包项目结算进度管理bo
     * @des 增值税发票通报
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/noticeInvoice")
    public Result noticeInvoice(@Validated({OutsourProProgressManageTO.testNoticeInvoice.class}) OutsourProProgressManageTO outsourProProgressManageTO) throws ActException {
        try {
            outsourProProgressManageAPI.noticeInvoice(outsourProProgressManageTO);
            return new ActResult("增值税发票通报成功 ");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 付款
     *
     * @param outsourProProgressManageTO 外包半外包项目结算进度管理bo
     * @des 付款
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/payMoney")
    public Result payMoney(@Validated({OutsourProProgressManageTO.testPayMoney.class}) OutsourProProgressManageTO outsourProProgressManageTO) throws ActException {
        try {
            outsourProProgressManageAPI.payMoney(outsourProProgressManageTO);
            return new ActResult("付款成功 ");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 进度确认
     *
     * @param outsourProProgressManageTO 外包半外包项目结算进度管理bo
     * @des 进度确认
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/scheduleConfirm")
    public Result scheduleConfirm(@Validated({OutsourProProgressManageTO.testScheduleConfirm.class}) OutsourProProgressManageTO outsourProProgressManageTO) throws ActException {
        try {
            outsourProProgressManageAPI.scheduleConfirm(outsourProProgressManageTO);
            return new ActResult("进度确认成功 ");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}