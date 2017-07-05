package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.ReimburseAuditLogAPI;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAuditLogBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAuditLogDTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseAuditLogTO;
import com.bjike.goddess.lendreimbursement.vo.ReimburseAuditLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 报销审核分析日志表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销审核日志表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("reimburseauditlog")
public class ReimburseAuditLogAction {

    @Autowired
    private ReimburseAuditLogAPI reimburseAuditLogAPI;

    /**
     * 报销审核日志列表总条数
     *
     * @param reimburseAuditLogDTO 报销审核日志信息dto
     * @des 获取所有报销审核日志信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ReimburseAuditLogDTO reimburseAuditLogDTO) throws ActException {
        try {
            Long count = reimburseAuditLogAPI.countReimburseAuditLog(reimburseAuditLogDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 报销审核日志列表
     *
     * @param reimburseAuditLogDTO 报销审核日志信息dto
     * @return class ReimburseAuditLogVO
     * @des 获取所有报销审核日志信息
     * @version v1
     */
    @GetMapping("v1/listReimburseAuditLog")
    public Result findListReimburseAuditLog(ReimburseAuditLogDTO reimburseAuditLogDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseAuditLogVO> reimburseAuditLogVOList = BeanTransform.copyProperties(
                    reimburseAuditLogAPI.listReimburseAuditLog(reimburseAuditLogDTO), ReimburseAuditLogVO.class, true);
            return ActResult.initialize(reimburseAuditLogVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加报销审核日志
     *
     * @param reimburseAuditLogTO 报销审核日志基本信息数据to
     * @return class ReimburseAuditLogVO
     * @des 添加报销审核日志
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addReimburseAuditLog( ReimburseAuditLogTO reimburseAuditLogTO, BindingResult bindingResult) throws ActException {
        try {
            ReimburseAuditLogBO reimburseAuditLogBO1 = reimburseAuditLogAPI.addReimburseAuditLog(reimburseAuditLogTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseAuditLogBO1, ReimburseAuditLogVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑报销审核日志
     *
     * @param reimburseAuditLogTO 报销审核日志基本信息数据bo
     * @return class ReimburseAuditLogVO
     * @des 添加报销审核日志
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editReimburseAuditLog( ReimburseAuditLogTO reimburseAuditLogTO) throws ActException {
        try {
            ReimburseAuditLogBO reimburseAuditLogBO1 = reimburseAuditLogAPI.editReimburseAuditLog(reimburseAuditLogTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseAuditLogBO1, ReimburseAuditLogVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除报销审核日志信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteReimburseAuditLog(@PathVariable String id) throws ActException {
        try {
            reimburseAuditLogAPI.deleteReimburseAuditLog(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 根据报销记录id查询
     *
     * @param reimburseId reimburseId
     * @return class ReimburseAuditLogVO
     * @des 报销记录查看详情
     * @version v1
     */
    @GetMapping("v1/getAuditLogByReimburseId/{reimburseId}")
    public Result listAuditLogByReimburseId(@PathVariable String reimburseId ) throws ActException {
        try {
            List<ReimburseAuditLogVO> reimburseAuditLogVO = BeanTransform.copyProperties(
                    reimburseAuditLogAPI.listReimburseAuditLogByRid(reimburseId), ReimburseAuditLogVO.class, true);
            return ActResult.initialize(reimburseAuditLogVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}