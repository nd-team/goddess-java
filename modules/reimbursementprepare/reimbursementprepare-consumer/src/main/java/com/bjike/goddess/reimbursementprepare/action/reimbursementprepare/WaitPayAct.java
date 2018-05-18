package com.bjike.goddess.reimbursementprepare.action.reimbursementprepare;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.ApplyLendAPI;
import com.bjike.goddess.reimbursementprepare.api.WaitPayAPI;
import com.bjike.goddess.reimbursementprepare.bo.DifferencesBO;
import com.bjike.goddess.reimbursementprepare.bo.WaitPayBO;
import com.bjike.goddess.reimbursementprepare.dto.WaitPayDTO;
import com.bjike.goddess.reimbursementprepare.to.GuidePermissionTO;
import com.bjike.goddess.reimbursementprepare.to.WaitPayTO;
import com.bjike.goddess.reimbursementprepare.vo.DifferencesVO;
import com.bjike.goddess.reimbursementprepare.vo.WaitPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:01 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("waitpay")
public class WaitPayAct extends BaseFileAction {
    @Autowired
    private WaitPayAPI waitPayAPI;
    @Autowired
    private ApplyLendAPI applyLendAPI;

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

            Boolean isHasPermission = waitPayAPI.guidePermission(guidePermissionTO);
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
     * 付款
     *
     * @param to 等待付款信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/pay")
    public Result pay(@Validated({EDIT.class}) WaitPayTO to, BindingResult result) throws ActException {
        try {
            waitPayAPI.pay(to);
            return new ActResult("付款成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款列表
     *
     * @param dto     等待付款分页信息
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/waitPays")
    public Result waitPays(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.waitPays(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param dto     dto
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectGroupCount")
    public Result projectGroupCount(@Validated(WaitPayDTO.PROJECTGROUP.class) WaitPayDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.conditionsCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param dto     dto
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areaCount")
    public Result areaCount(@Validated(WaitPayDTO.AREA.class) WaitPayDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.conditionsCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金准备与实际支付差异
     *
     * @param dto     dto
     * @param request 请求对象
     * @return class DifferencesVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/difference")
    public Result difference(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DifferencesBO> list = waitPayAPI.difference(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DifferencesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款列表
     *
     * @param dto dto
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pays")
    public Result pays(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.pays(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找等待付款总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/waitPayCount")
    public Result waitPayCount(WaitPayDTO dto) throws ActException {
        try {
            return ActResult.initialize(waitPayAPI.waitPayCount(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找已付款总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/payCount")
    public Result payCount(WaitPayDTO dto) throws ActException {
        try {
            return ActResult.initialize(waitPayAPI.payCount(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出等待付款Excel
     *
     * @param dto dto
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/waitPayExport")
    public Result waitPayExport(WaitPayDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "借款报销资金准备与支付等待付款.xlsx";
            super.writeOutFile(response, waitPayAPI.waitPayExport(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导出已付款Excel
     *
     * @param dto dto
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/payExport")
    public Result payExport(WaitPayDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "借款报销资金准备与支付已付款.xlsx";
            super.writeOutFile(response, waitPayAPI.payExport(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 获取所有项目组
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allProjectGroups")
    public Result allProjectGroups() throws ActException {
        try {
            return ActResult.initialize(waitPayAPI.allProjectGroups());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allAreas")
    public Result allAreas() throws ActException {
        try {
            return ActResult.initialize(waitPayAPI.allAreas());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有付款来源
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listAccountCom")
    public Result listAccountCom() throws ActException {
        try {
            return ActResult.initialize(applyLendAPI.listAccountCom());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找等待付款
     *
     * @param id id
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findWait/{id}")
    public Result findWait(@PathVariable String id, HttpServletRequest re) throws ActException {
        try {
            WaitPayBO bo = waitPayAPI.findWait(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, WaitPayVO.class, re));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}