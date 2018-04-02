package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.lendreimbursement.api.AuditingAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 审核详情
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-02 09:39 ]
 * @Description: [ 审核详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("auditing")
public class AuditingAction {

    @Autowired
    private AuditingAPI auditingAPI;

    @GetMapping("/getAuditing")
    public Result getAuditing() throws SerException {
        return new ActResult("success", auditingAPI.getAuditing("212"));
    }

    /**
     * 审核详情列表
     * @param id
     * @param pageNum
     * @return
     */
    @PostMapping("v1/auditDetails")
    public Result auditDetails(String id, Integer pageNum) {
        System.out.println(id + "----" + pageNum);
        return new ActResult("success", auditingAPI.getAuditingPage(pageNum, id));
    }
}