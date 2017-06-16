package com.bjike.goddess.checkfunds.action.checkfunds;

import com.bjike.goddess.checkfunds.api.BankReconciliationAPI;
import com.bjike.goddess.checkfunds.api.NotPassAuditAPI;
import com.bjike.goddess.checkfunds.dto.NotPassAuditDTO;
import com.bjike.goddess.checkfunds.vo.NotPassAuditVO;
import com.bjike.goddess.checkfunds.vo.RemainAdjustVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 审批不通过记录
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:23 ]
 * @Description: [ 审批不通过记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("notpassaudit")
public class NotPassAuditAct {
    @Autowired
    private NotPassAuditAPI notPassAuditAPI;
    @Autowired
    private BankReconciliationAPI bankReconciliationAPI;

    /**
     * 列表
     *
     * @param dto 审批不通过记录dto
     * @return class NotPassAuditVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(NotPassAuditDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<NotPassAuditVO> VOS = BeanTransform.copyProperties
                    (notPassAuditAPI.list(dto), NotPassAuditVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找类表总记录数
     *
     * @param dto 审批不通过记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(NotPassAuditDTO dto) throws ActException {
        try {
            Long count = notPassAuditAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看余额调节列表
     *
     * @param id 银企对账id
     * @return class RemainAdjustVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/listAdjust/{id}")
    public Result listAdjust(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<RemainAdjustVO> VOS = BeanTransform.copyProperties
                    (bankReconciliationAPI.adjust(id), RemainAdjustVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}