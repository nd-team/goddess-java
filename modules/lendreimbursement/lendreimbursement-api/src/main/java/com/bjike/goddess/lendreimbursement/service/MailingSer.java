package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.lendreimbursement.dto.MailingDTO;
import com.bjike.goddess.lendreimbursement.entity.Mailing;

/**
 * 寄件信息业务接口
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-03 03:23 ]
 * @Description: [ 寄件信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MailingSer extends Ser<Mailing, MailingDTO> {

    Mailing getMailing() throws SerException;

}