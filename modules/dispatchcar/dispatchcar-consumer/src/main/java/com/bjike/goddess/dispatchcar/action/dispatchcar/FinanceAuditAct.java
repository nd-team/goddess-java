package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 财务核对
 *
 * @Author: [Jason]
 * @Date: [17-4-14 上午11:53]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("financeaudit")
public class FinanceAuditAct {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    /**
     * 财务核对页面分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(DispatchCarInfoDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.FINANCEAUDIT));
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核详情
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findAudit/{id}")
    public Result findAudit(@PathVariable String id) throws ActException {
        try {
            List<DispatchCarInfoVO> vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findAudit(id), DispatchCarInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 收到单据数据查询
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            List<DispatchCarInfoVO> vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findDetail(id), DispatchCarInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 单据审核
     *
     * @param id 出车记录id
     * @param auditReceiptSugg 审核意见
     * @param receiveReceiptDate 签收日期
     * @param auditReceiptResult 审核结果
     * @version v1
     */
    @PostMapping("v1/receiptAudit")
    public Result receiptAudit(String id, String auditReceiptSugg ,String receiveReceiptDate,Boolean auditReceiptResult) throws ActException {
        try {
            dispatchCarInfoAPI.receiptAudit(id,auditReceiptSugg,receiveReceiptDate,auditReceiptResult);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
