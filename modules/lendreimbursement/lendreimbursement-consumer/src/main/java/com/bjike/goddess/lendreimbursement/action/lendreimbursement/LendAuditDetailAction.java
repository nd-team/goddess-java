package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.LendAuditDetailAPI;
import com.bjike.goddess.lendreimbursement.dto.LendAuditDetailDTO;
import com.bjike.goddess.lendreimbursement.entity.LendAuditDetail;
import com.bjike.goddess.lendreimbursement.vo.LendAuditDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 借款审核人员
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:06 ]
 * @Description: [ 借款审核人员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("lendreimbursement/lendauditdetail")
public class LendAuditDetailAction {

    @Autowired
    private LendAuditDetailAPI lendAuditDetailAPI;

    /**
     * 借款审核人员列表总条数
     *
     * @param lendAuditDetailDTO 借款审核人员信息dto
     * @des 获取所有借款审核人员信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LendAuditDetailDTO lendAuditDetailDTO) throws ActException {
        try {
            Long count = lendAuditDetailAPI.countDetail(lendAuditDetailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个借款审核人员
     *
     * @param id 报销单号信息id
     * @des 根据id获取借款审核人员
     * @return  class LendAuditDetailVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            LendAuditDetailVO lendAuditDetailVO = BeanTransform.copyProperties(
                    lendAuditDetailAPI.getOneById(id), LendAuditDetailVO.class);
            return ActResult.initialize(lendAuditDetailVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款审核人员列表
     *
     * @param lendAuditDetailDTO 借款审核人员信息dto
     * @return class LendAuditDetailVO
     * @des 获取所有借款审核人员信息
     * @version v1
     */
    @GetMapping("v1/listLendAuditDetail")
    public Result findListLendAuditDetail(LendAuditDetailDTO lendAuditDetailDTO, BindingResult bindingResult) throws ActException {
        try {
            List<LendAuditDetailVO> lendAuditDetailVOList = BeanTransform.copyProperties(
                    lendAuditDetailAPI.listLendAuditDetail(lendAuditDetailDTO), LendAuditDetailVO.class, true);
            return ActResult.initialize(lendAuditDetailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}