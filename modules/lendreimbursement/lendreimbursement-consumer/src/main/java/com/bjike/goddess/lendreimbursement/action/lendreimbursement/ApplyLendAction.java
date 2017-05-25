package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.ApplyLendAPI;
import com.bjike.goddess.lendreimbursement.bo.ApplyLendBO;
import com.bjike.goddess.lendreimbursement.bo.LendAuditDetailBO;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendDTO;
import com.bjike.goddess.lendreimbursement.to.ApplyLendTO;
import com.bjike.goddess.lendreimbursement.vo.AccountVoucherVO;
import com.bjike.goddess.lendreimbursement.vo.ApplyLendVO;
import com.bjike.goddess.lendreimbursement.vo.CollectDataVO;
import com.bjike.goddess.lendreimbursement.vo.LendAuditDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 申请借款
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("applylend")
public class ApplyLendAction {

    @Autowired
    private ApplyLendAPI applyLendAPI;

    /**
     * 申请借款列表总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有申请借款信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countApplyLend(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个申请借款
     *
     * @param id 项目签订与立项id
     * @des 根据id获取申请借款
     * @return  class ApplyLendVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ApplyLendVO applyLendVO = BeanTransform.copyProperties(
                    applyLendAPI.getOneById(id), ApplyLendVO.class);
            return ActResult.initialize(applyLendVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请借款列表
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 获取所有申请借款信息
     * @version v1
     */
    @GetMapping("v1/listApplyLend")
    public Result findListApplyLend(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listApplyLend(applyLendDTO), ApplyLendVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加申请借款
     *
     * @param applyLendTO 申请借款基本信息数据to
     * @return class ApplyLendVO
     * @des 添加申请借款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addApplyLend(@Validated(ApplyLendTO.TESTAddAndEdit.class) ApplyLendTO applyLendTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.addApplyLend(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑申请借款
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 添加申请借款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editApplyLend(@Validated(ApplyLendTO.TESTAddAndEdit.class) ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editApplyLend(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除申请借款信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteApplyLend(@PathVariable String id) throws ActException {
        try {
            applyLendAPI.deleteApplyLend(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }



    /**
     * 申请借款审核详情
     *
     * @param id id
     * @des 根据id删除申请借款信息记录
     * @version v1
     */
    @GetMapping("v1/applyLendDetail/{id}")
    public Result applyLendDetail(@PathVariable String id) throws ActException {
        try {
            ApplyLendBO lendAuditDetailBOS = applyLendAPI.getApplyLendDetail(id);
            return ActResult.initialize(BeanTransform.copyProperties(lendAuditDetailBOS, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException("查询失败：" + e.getMessage());
        }
    }

    /**
     * 申请借款导出
     *
     * @param applyLendTO applyLendTO
     * @des 根据id删除申请借款信息记录
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }


    /**
     * 申请借款审核详情
     *
     * @param id id
     * @des 根据id删除申请借款信息记录
     * @return class LendAuditDetailVO
     * @version v1
     */
    @GetMapping("v1/applyAuditDetail/{id}")
    public Result applyAuditDetail(@PathVariable String id) throws ActException {
        try {
            List<LendAuditDetailBO> lendAuditDetailBOS = applyLendAPI.getLendAuditDetailByApplyLendId(id);
            return ActResult.initialize(BeanTransform.copyProperties(lendAuditDetailBOS, LendAuditDetailVO.class, true));
        } catch (SerException e) {
            throw new ActException("查询失败：" + e.getMessage());
        }
    }

    /**
     * 等待审核列表总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有申请借款信息总条数
     * @version v1
     */
    @GetMapping("v1/countWaitAudit")
    public Result countWaitAudit(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countWaitAudit(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待审核列表
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 获取所有等待审核列表
     * @version v1
     */
    @GetMapping("v1/listWaitAudit")
    public Result findListWaitAudit(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listWaitAudit(applyLendDTO), ApplyLendVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑等待审核
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 编辑等待审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editWaitAudit")
    public Result editWaitAudit(@Validated(ApplyLendTO.TESTAddAndEdit.class) ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editWaitAudit(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 负责人审核
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 负责人审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editAuditBycharger")
    public Result editAuditBycharger(@Validated(ApplyLendTO.TESTChargeAudit.class) ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editChargerWaitAudit(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部审核
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 运营商务部审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editAuditByOperate")
    public Result editAuditByOperate(@Validated(ApplyLendTO.TESTFinaceAudit.class) ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editOperateWaitAudit(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 总经办审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editAuditByManager")
    public Result editAuditByManager(@Validated(ApplyLendTO.TESTManageAudit.class) ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editManageWaitAudit(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部申请冻结
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 运营商务部冻结
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congelAuditByOperate")
    public Result congelAuditByOperate( ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editOperateCongel(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 负责人确认冻结
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 负责人确认冻结
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congelSureByCharger")
    public Result congelSureByCharger( ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editChargeSureCongel(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 负责人取消冻结
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 负责人取消冻结
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congelConcelByCharger")
    public Result congelConcelByCharger( ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editChargeConcelCongel(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请单有误列表总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有申请单有误列表总条数
     * @version v1
     */
    @GetMapping("v1/countError")
    public Result countError(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countApplyError(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请单有误列表
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 获取所有申请单有误列表
     * @version v1
     */
    @GetMapping("v1/listError")
    public Result findListError(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listApplyError(applyLendDTO), ApplyLendVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑申请单有误
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 编辑申请单有误
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editError")
    public Result editError(@Validated(ApplyLendTO.TESTAddAndEdit.class)  ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editApplyError(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除申请单有误
     *
     * @param id id
     * @des 根据id删除申请借款信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/deleteError/{id}")
    public Result deleteError(@PathVariable String id) throws ActException {
        try {
            applyLendAPI.deleteApplyError(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 申请单有误审核详情
     *
     * @param id 申请借款信息id
     * @return class ApplyLendVO
     * @des 获取所有申请单有误审核详情
     * @version v1
     */
    @GetMapping("v1/getError")
    public Result getError(@PathVariable String id, BindingResult bindingResult) throws ActException {
        try {
            ApplyLendVO applyLendVO = BeanTransform.copyProperties(
                    applyLendAPI.getApplyApplyError(id), ApplyLendVO.class);
            return ActResult.initialize(applyLendVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请单有误审核详情副本
     *
     * @param id 申请借款信息id
     * @return class ApplyLendVO
     * @des 获取申请单有误审核详情副本
     * @version v1
     */
    @GetMapping("v1/getErrorCopy/{id}")
    public Result getErrorCopy(@PathVariable String id ) throws ActException {
        try {
            ApplyLendVO applyLendVO = BeanTransform.copyProperties(
                    applyLendAPI.getApplyApplyErrorCopy(id), ApplyLendVO.class);
            return ActResult.initialize(applyLendVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核或分析记录总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有已审核或分析记录总条数
     * @version v1
     */
    @GetMapping("v1/countHasAudit")
    public Result countHasAudit(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countHasAudit(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已审核或分析记录列表
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 获取所有已审核或分析记录列表
     * @version v1
     */
    @GetMapping("v1/findListHasAudit")
    public Result findListHasAudit(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listHasAudit(applyLendDTO), ApplyLendVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有等待付款总条数
     * @version v1
     */
    @GetMapping("v1/countWaitPay")
    public Result countWaitPay(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countWaitPay(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款列表
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 获取所有等待付款列表
     * @version v1
     */
    @GetMapping("v1/findListWaitPay")
    public Result findListWaitPay(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listWaitPay(applyLendDTO), ApplyLendVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 付款
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 等待付款的付款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editPay")
    public Result editPay( ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editPayMoney(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款导出
     *
     * @param applyLendTO applyLendTO
     * @des 根据id删除申请借款信息记录
     * @version v1
     */
    @GetMapping("v1/exportPayExcel")
    public Result exportPayExcel(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }

    /**
     * 确认收款总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有确认收款总条数
     * @version v1
     */
    @GetMapping("v1/countRecieve")
    public Result countSureRecieve(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countSureRecieve(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认收款列表
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 获取所有确认收款列表
     * @version v1
     */
    @GetMapping("v1/listSureRecieve")
    public Result findListSureRecieve(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listSureRecieveMoney(applyLendDTO), ApplyLendVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认收款
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 确认收款
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSureRecieve")
    public Result editSureRecieve(  ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editSureRecieveMoney(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款记录总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有借款记录总条数
     * @version v1
     */
    @GetMapping("v1/countBorRecord")
    public Result countBorrowRecord(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countBorrowRecord(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款记录列表
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 获取所有借款记录列表
     * @version v1
     */
    @GetMapping("v1/listBorrowRecord")
    public Result findListBorrowRecord(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listBorrowRecord(applyLendDTO), ApplyLendVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款记录还款编辑
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 借款记录还款编辑
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editReturn")
    public Result editReturn(  ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editReturnBorrowRecord(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款记录寄件编辑
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 借款记录寄件编辑
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSend")
    public Result editSend(@Validated(ApplyLendTO.TESTReturnSend.class) ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.editBorrowRecordSend(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款记录生成记账凭证
     *
     * @param id 申请借款信息id
     * @return class AccountVoucherVO
     * @des 借款记录生成记账凭证
     * @version v1
     */
    @GetMapping("v1/listAccountVoucher/{id}")
    public Result listAccountVoucher(@PathVariable String id) throws ActException {
        try {
            List<AccountVoucherVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listAccountVoucherByRecord(id), AccountVoucherVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款记录导出
     *
     * @param applyLendTO applyLendTO
     * @des
     * @version v1
     */
    @GetMapping("v1/exportBorrowExcel")
    public Result exportBorrowExcel(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }

    /**
     * 借款记录附件
     *
     * @param applyLendTO applyLendTO
     * @des
     * @version v1
     */
    @GetMapping("v1/uploadFileBorrow")
    public Result uploadFileBorrow(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }

    /**
     * 还款记录总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有还款记录总条数
     * @version v1
     */
    @GetMapping("v1/countReturn")
    public Result countReturn(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countReturn(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 还款记录
     *
     * @param applyLendDTO 申请借款信息dto
     * @return class ApplyLendVO
     * @des 还款记录
     * @version v1
     */
    @GetMapping("v1/listReturnMoney")
    public Result findListReturnMoney(ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listReturnMoneyRecord(applyLendDTO), ApplyLendVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 还款记录导出
     *
     * @param applyLendTO applyLendTO
     * @des
     * @version v1
     */
    @GetMapping("v1/exportReturnExcel")
    public Result exportReturnExcel(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }

    /**
     * 还款记录生成记账凭证
     *
     * @param id 申请借款信息id
     * @return class AccountVoucherVO
     * @des 借款记录生成记账凭证
     * @version v1
     */
    @GetMapping("v1/listVoucherByReturn/{id}")
    public Result listVoucherReturn(@PathVariable String id ) throws ActException {
        try {
            List<AccountVoucherVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listAccountVoucherByReturnMoney(id), AccountVoucherVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 还款记录还款核对
     *
     * @param applyLendTO 申请借款信息applyLendTO
     * @return class ApplyLendVO
     * @des 还款记录还款核对
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/checkReturn")
    public Result checkReturn(@Validated(ApplyLendTO.TESTCheckReturnMoney.class) ApplyLendTO applyLendTO ) throws ActException {
        try {
            ApplyLendVO applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.checkReturnMoney(applyLendTO), ApplyLendVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 帐务核对总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有帐务核对总条数
     * @version v1
     */
    @GetMapping("v1/countBusCheck")
    public Result countBusCheck(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countBusCheck(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 帐务核对记录
     *
     * @param applyLendDTO 申请借款信息applyLendDTO
     * @return class ApplyLendVO
     * @des 帐务核对记录
     * @version v1
     */
    @GetMapping("v1/listBusinessCheck")
    public Result listBusinessCheck( ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listBusinessCheck(applyLendDTO), ApplyLendVO.class);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 帐务核对附件
     *
     * @param applyLendTO applyLendTO
     * @des
     * @version v1
     */
    @GetMapping("v1/uploadFileCkTicket")
    public Result uploadFileCkTicket(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }

    /**
     * 帐务核对收到单据确认
     *
     * @param applyLendTO 申请借款基本信息数据bo
     * @return class ApplyLendVO
     * @des 帐务核对收到单据确认
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editCheckTicket")
    public Result editCheckTicket( ApplyLendTO applyLendTO) throws ActException {
        try {
            ApplyLendBO applyLendBO1 = applyLendAPI.checkTicket(applyLendTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyLendBO1, ApplyLendVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已收票总条数
     *
     * @param applyLendDTO 申请借款信息dto
     * @des 获取所有已收票总条数
     * @version v1
     */
    @GetMapping("v1/countRecTicket")
    public Result countRecTicket(ApplyLendDTO applyLendDTO) throws ActException {
        try {
            Long count = applyLendAPI.countRecTicket(applyLendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已收票记录
     *
     * @param applyLendDTO 申请借款信息applyLendDTO
     * @return class ApplyLendVO
     * @des 已收票记录
     * @version v1
     */
    @GetMapping("v1/listRecieveTicket")
    public Result listRecieveTicket(@Validated ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ApplyLendVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.listRecieveTicketRecord(applyLendDTO), ApplyLendVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已收票记录附件
     *
     * @param applyLendTO applyLendTO
     * @des
     * @version v1
     */
    @GetMapping("v1/uploadFileRecieve")
    public Result uploadFileRecieve(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }

    /**
     * 已收票记录导出
     *
     * @param applyLendTO applyLendTO
     * @des
     * @version v1
     */
    @GetMapping("v1/exportReceiveExcel")
    public Result exportReceiveExcel(ApplyLendTO applyLendTO ,BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-04-10
        return  new ActResult(null);
    }

    /**
     * 汇总借款人
     *
     * @param applyLendDTO 申请借款信息applyLendDTO
     * @return class CollectDataVO
     * @des 汇总借款人
     * @version v1
     */
    @GetMapping("v1/collectLender")
    public Result collectLender(@Validated ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectDataVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.collectLender(applyLendDTO), CollectDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总地区
     *
     * @param applyLendDTO 申请借款信息applyLendDTO
     * @return class CollectDataVO
     * @des 汇总地区
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(@Validated ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectDataVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.collectArea(applyLendDTO), CollectDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总项目组
     *
     * @param applyLendDTO 申请借款信息applyLendDTO
     * @return class CollectDataVO
     * @des 汇总项目组
     * @version v1
     */
    @GetMapping("v1/collectProjectGroup")
    public Result collectProjectGroup(@Validated ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectDataVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.collectProjectGroup(applyLendDTO), CollectDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总项目名称
     *
     * @param applyLendDTO 申请借款信息applyLendDTO
     * @return class CollectDataVO
     * @des 汇总项目名称
     * @version v1
     */
    @GetMapping("v1/collectProjectName")
    public Result collectProjectName(@Validated ApplyLendDTO applyLendDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CollectDataVO> applyLendVOList = BeanTransform.copyProperties(
                    applyLendAPI.collectProjectName(applyLendDTO), CollectDataVO.class, true);
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取借款人
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取借款人集合
     * @version v1
     */
    @GetMapping("v1/getLenderList")
    public Result getLenderList() throws ActException {
        try {
            List<String> areaList = applyLendAPI.listLender();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/getArea")
    public Result getArea() throws ActException {
        try {
            List<String> areaList = applyLendAPI.listArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目组
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取项目组集合
     * @version v1
     */
    @GetMapping("v1/getPGroupList")
    public Result getPGroupList() throws ActException {
        try {
            List<String> areaList = applyLendAPI.listProjectGroup();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目名
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取项目名集合
     * @version v1
     */
    @GetMapping("v1/getPNameList")
    public Result getPNameList() throws ActException {
        try {
            List<String> areaList = applyLendAPI.listProjectName();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




}