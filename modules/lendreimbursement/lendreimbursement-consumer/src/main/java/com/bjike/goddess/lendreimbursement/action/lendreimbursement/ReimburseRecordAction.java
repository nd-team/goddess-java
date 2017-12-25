package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.api.AccountanCourseAPI;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.lendreimbursement.api.ReimburseAuditLogAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.excel.SonPermissionObject;
import com.bjike.goddess.lendreimbursement.to.AccountVoucherTO;
import com.bjike.goddess.lendreimbursement.to.LendDeleteFileTO;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import com.bjike.goddess.lendreimbursement.vo.AccountVoucherVO;
import com.bjike.goddess.lendreimbursement.vo.CollectReimerDataVO;
import com.bjike.goddess.lendreimbursement.vo.ReimburseAuditLogVO;
import com.bjike.goddess.lendreimbursement.vo.ReimburseRecordVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 报销记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("reimburserecord")
public class ReimburseRecordAction extends BaseFileAction {

    @Autowired
    private ReimburseRecordAPI reimburseRecordAPI;
    @Autowired
    private ReimburseAuditLogAPI reimburseAuditLogAPI;
    @Autowired
    private AccountanCourseAPI accountanCourseAPI;
    @Autowired
    private AccountAPI accountAPI;
    @Autowired
    private FileAPI fileAPI;
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
    public Result setButtonPermission() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = reimburseRecordAPI.sonPermission();
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
    public Result guidePermission(@Validated(LendGuidePermissionTO.TestAdd.class) LendGuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = reimburseRecordAPI.guidePermission(guidePermissionTO);
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
     * 一个申请报销
     *
     * @param id 申请报销id
     * @return class ReimburseRecordVO
     * @des 根据id获取申请报销
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ReimburseRecordVO applyLendVO = BeanTransform.copyProperties(
                    reimburseRecordAPI.getOneById(id), ReimburseRecordVO.class);
            return ActResult.initialize(applyLendVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 申请报销列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有申请报销信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countReimburseRecord(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 报销列表总条数(phone)
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有申请报销信息总条数
     * @version v1
     */
    @GetMapping("v1/counts")
    public Result counts(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countReimburseRecords(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请报销列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有申请报销信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListReimburseRecord(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listReimburseRecord(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加申请报销
     *
     * @param reimburseRecordTO 申请报销基本信息数据to
     * @return class ReimburseRecordVO
     * @des 添加申请报销
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addReimburseRecord(@Validated(ReimburseRecordTO.TestAddAndEdit.class) ReimburseRecordTO reimburseRecordTO, BindingResult bindingResult) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.addReimburseRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑申请报销
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 编辑申请报销
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editReimburseRecord(@Validated(ReimburseRecordTO.TestAddAndEdit.class) ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.editReimburseRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除申请报销信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteReimburseRecord(@PathVariable String id) throws ActException {
        try {
            reimburseRecordAPI.deleteReimburseRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 审核详情
     *
     * @param id id
     * @return class ReimburseAuditLogVO
     * @des 报销记录查看审核详情
     * @version v1
     */
    @GetMapping("v1/getReimburseRecord/{id}")
    public Result getReimburseRecord(@PathVariable String id) throws ActException {
        try {
            List<ReimburseAuditLogVO> reimburseAuditLogVO = BeanTransform.copyProperties(
                    reimburseAuditLogAPI.listReimburseAuditLogByRid(id), ReimburseAuditLogVO.class, true);
            return ActResult.initialize(reimburseAuditLogVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请报销列误列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有申请报销信息总条数
     * @version v1
     */
    @GetMapping("v1/countError")
    public Result countError(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countErrorRecord(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请报销有误列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有申请报销信息
     * @version v1
     */
    @GetMapping("v1/listErrorRecord")
    public Result findListErrorRecord(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listErrorRecord(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑有误申请报销
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 编辑有误申请报销重新提交
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editError")
    public Result editErrorRecord(@Validated(ReimburseRecordTO.TestAddAndEdit.class) ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.editErrorRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 等待审核列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dtossssss
     * @des 获取所有等待审核报销信息总条数
     * @version v1
     */
    @GetMapping("v1/countAuditRecord")
    public Result countAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countAuditRecord(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待审核列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有等待审核申请报销信息
     * @version v1
     */
    @GetMapping("v1/listAuditRecord")
    public Result findListAuditRecord(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listAuditRecord(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 负责人审核等待审核
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 审核等待审核报销
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/auditRecord")
    public Result auditRecord(@Validated(ReimburseRecordTO.TestChargeAudit.class) ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.auditRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认冻结等待审核
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 等待审核确认冻结分析人员申请的冻结报销单
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congelAuditRecord")
    public Result congelAuditRecord(@Validated(ReimburseRecordTO.TestChargeCongel.class) ReimburseRecordTO reimburseRecordTO, BindingResult bindingResult) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.congelAuditRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已经审核列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有已经审核报销信息总条数
     * @version v1
     */
    @GetMapping("v1/countAnalisysRecord")
    public Result countAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countAnalisysRecord(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已经审核列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有已经审核申请报销信息
     * @version v1
     */
    @GetMapping("v1/listAnalisysRecord")
    public Result findListAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listAnalisysRecord(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 分析已经审核(运营部分析)
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 审分析报销
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/analisysRecord")
    public Result analisysRecord(@Validated(ReimburseRecordTO.TestAnalysis.class) ReimburseRecordTO reimburseRecordTO, BindingResult bindingResult) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.analisysRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请冻结已经审核
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 申请冻结已经审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congelAnalisysRecord")
    public Result congelAnalisysRecord(ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.congelAnalisysRecord(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已经分析列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有已经分析报销信息总条数
     * @version v1
     */
    @GetMapping("v1/countHasAnalisys")
    public Result countHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countHasAnalisys(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已经分析列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有已经分析申请报销信息
     * @version v1
     */
    @GetMapping("v1/listHasAnalisys")
    public Result findListHasAnalisys(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listHasAnalisys(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 帐务核对列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有帐务核对总条数
     * @version v1
     */
    @GetMapping("v1/countAccountCheck")
    public Result countAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countAccountCheck(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 帐务核对列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有帐务核对信息
     * @version v1
     */
    @GetMapping("v1/listAccountCheck")
    public Result findListAccountCheck(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listAccountCheck(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 收到单据
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 帐务核对确认收到单据
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/recieveTicket")
    public Result recieveTicket(@Validated(ReimburseRecordTO.TestRecieveTicketCheck.class) ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.recieveTicketCondition(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有等待付款总条数
     * @version v1
     */
    @GetMapping("v1/countWaitPay")
    public Result countWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countWaitPay(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有等待付款信息, 注意：必须规定的所有分析人员分析完且已经收到单据的
     * @version v1
     */
    @GetMapping("v1/listWaitPay")
    public Result findListWaitPay(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listWaitPay(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 等待付款导出
     *
     * @param reimburseRecordDTO
     * @return class ReimburseRecordVO
     * @des 等待付款导出
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportPay")
    public Result exportPay(ReimburseRecordDTO reimburseRecordDTO, HttpServletResponse response) throws ActException {
        try {
            String fileName = "等待付款.xlsx";
            super.writeOutFile(response, reimburseRecordAPI.exportExcel(reimburseRecordDTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 预计付款等待付款
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 等待付款预计付款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/prepay")
    public Result prepay(@Validated(ReimburseRecordTO.TestPrePay.class) ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.prePay(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 付款等待付款
     *
     * @param reimburseRecordTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 等待付款的付款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/waitPay")
    public Result waitPay(@Validated(ReimburseRecordTO.TestPay.class) ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {
            ReimburseRecordBO reimburseRecordBO1 = reimburseRecordAPI.waitPay(reimburseRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseRecordBO1, ReimburseRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款记录列表总条数
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有已付款记录总条数
     * @version v1
     */
    @GetMapping("v1/countHasPay")
    public Result countHasPay(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countHasPay(reimburseRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款记录列表
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @return class ReimburseRecordVO
     * @des 获取所有已付款记录信息
     * @version v1
     */
    @GetMapping("v1/listHasPay")
    public Result findListHasPay(ReimburseRecordDTO reimburseRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseRecordVO> reimburseRecordVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listHasPay(reimburseRecordDTO), ReimburseRecordVO.class, true);
            return ActResult.initialize(reimburseRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已付款记录导出
     *
     * @param reimburseRecordDTO 申请报销基本信息数据bo
     * @return class ReimburseRecordVO
     * @des 已付款记录导出
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/exportHasPay")
    public Result exportHasPay(ReimburseRecordDTO reimburseRecordDTO, HttpServletResponse response) throws ActException {
        try {
            String fileName = "已付款记录.xlsx";
            super.writeOutFile(response, reimburseRecordAPI.exportAlPayExcel(reimburseRecordDTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 已付款记录生成记账凭证
     *
     * @param id 已付款记录信息id
     * @return class AccountVoucherVO
     * @des 已付款记录生成记账凭证
     * @version v1
     */
    @GetMapping("v1/listAccountVoucher/{id}")
    public Result listAccountVoucher(@PathVariable String id) throws ActException {
        try {
            List<AccountVoucherVO> applyLendVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.listAccountVoucherByRecord(id), AccountVoucherVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款记录添加记账凭证
     *
     * @param accountVoucherTO 已付款记录信息accountVoucherTO
     * @return class AccountVoucherVO
     * @des 已付款记录生成记账凭证
     * @version v1
     */
    @GetMapping("v1/addAccountVoucher")
    public Result addAccountVoucher(AccountVoucherTO accountVoucherTO, BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-13 插入记账凭证  修改标志  若已生成记账凭证则修改
        //         //将 是否生成记账凭证 标志改成 是
//        try {
//            List<AccountVoucherVO> applyLendVOList = BeanTransform.copyProperties(
//                    reimburseRecordAPI.addAccountVoucherByRecord(accountVoucherTO), AccountVoucherVO.class, true);
//            return ActResult.initialize(applyLendVOList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        return ActResult.initialize(null);
    }


    /**
     * 汇总报销人
     *
     * @param applyLendDTO 已付款记录信息applyLendDTO
     * @return class CollectReimerDataVO
     * @des 汇总报销人
     * @version v1
     */
    @GetMapping("v1/collectReimer")
    public Result collectReimer(@Validated ReimburseRecordDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectReimerDataVO> applyLendVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.collectLender(applyLendDTO), CollectReimerDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总地区
     *
     * @param applyLendDTO 已付款记录信息applyLendDTO
     * @return class CollectReimerDataVO
     * @des 汇总地区
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(@Validated ReimburseRecordDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectReimerDataVO> applyLendVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.collectArea(applyLendDTO), CollectReimerDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总一级科目
     *
     * @param applyLendDTO 已付款记录信息applyLendDTO
     * @return class CollectReimerDataVO
     * @des 汇总一级科目
     * @version v1
     */
    @GetMapping("v1/collectProjectGroup")
    public Result collectProjectGroup(@Validated ReimburseRecordDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectReimerDataVO> applyLendVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.collectFirstSubject(applyLendDTO), CollectReimerDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总项目名称
     *
     * @param applyLendDTO 已付款记录信息applyLendDTO
     * @return class CollectReimerDataVO
     * @des 汇总项目名称
     * @version v1
     */
    @GetMapping("v1/collectProjectName")
    public Result collectProjectName(@Validated ReimburseRecordDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectReimerDataVO> applyLendVOList = BeanTransform.copyProperties(
                    reimburseRecordAPI.collectProjectName(applyLendDTO), CollectReimerDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有一级科目
     *
     * @des 获取所有一级科目
     * @version v1
     */
    @GetMapping("v1/listFirstSubject")
    public Result listFirstSubject() throws ActException {
        try {
            List<String> userList = reimburseRecordAPI.listFirstSubject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @des 获取所有地区
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result listArea() throws ActException {
        try {
            List<String> userList = reimburseRecordAPI.listArea();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目名称
     *
     * @des 获取所有项目名称
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result listProject() throws ActException {
        try {
            List<String> userList = reimburseRecordAPI.listProject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总报销人条件
     *
     * @des 获取所有报销人
     * @version v1
     */
    @GetMapping("v1/listReimUser")
    public Result listReimUser() throws ActException {
        try {
            List<String> userList = reimburseRecordAPI.listReimUser();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有报销人
     *
     * @des 获取所有报销人
     * @version v1
     */
    @GetMapping("v1/listUser")
    public Result listUser() throws ActException {
        try {
            List<String> userList = reimburseRecordAPI.listAllUser();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有一级科目和一级科目代码
     *
     * @des 获取所有一级科目和一级科目代码
     * @version v1
     */
    @GetMapping("v1/list/firstSubject/code")
    public Result listFirstSubjectAndCode() throws ActException {
        try {
            List<AccountAddDateBO> list = accountanCourseAPI.findFirstNameCode();

            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有二级科目
     *
     * @des 根据一级科目的代码获取所有二级科目
     * @version v1
     */
    @GetMapping("v1/listSecondSubject/{code}")
    public Result listSecondByCode(@PathVariable String code ) throws ActException {
        try {
            List<AccountAddDateBO> list = accountanCourseAPI.findSecondName( code );

            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有三级科目
     *
     * @des 根据二级科目的代码获取所有三级科目
     * @version v1
     */
    @GetMapping("v1/listThirdSubject/{code}")
    public Result listThirdByCode(@PathVariable String code ) throws ActException {
        try {
            List<AccountAddDateBO> list = accountanCourseAPI.findThirdName( code );

            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 获取报销单号预计付款等待付款
     *
     * @des 获取报销单号预计付款等待付款
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/reimNumByPrepay")
    public Result reimNumByPrepay() throws ActException {
        try {
            List<String> list = reimburseRecordAPI.reimNumByPrepay();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有支付(账户)来源
     *
     * @des 获取所有支付(账户)来源
     * @version v1
     */
    @GetMapping("v1/listAccountOrigin")
    public Result listAccountOrigin(ReimburseRecordTO reimburseRecordTO) throws ActException {
        try {

            List<String> accountOrigins = accountAPI.listAccountOrigin();
            return ActResult.initialize(accountOrigins);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/reimburseRecord/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/reimburseRecord/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(LendDeleteFileTO.TestDEL.class) LendDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


}