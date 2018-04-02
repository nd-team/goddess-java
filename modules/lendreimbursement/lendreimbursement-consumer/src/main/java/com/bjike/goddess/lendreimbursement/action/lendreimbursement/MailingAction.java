package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 寄件信息
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-03 03:23 ]
 * @Description: [ 寄件信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("mailing")
public class MailingAction {


    @GetMapping("/v1/getMailing")
    public Result getMailing() {

        return new ActResult();
    }

    @PostMapping("/v1/get")
    public Result mailingAdd() {

        return new ActResult();
    }
}