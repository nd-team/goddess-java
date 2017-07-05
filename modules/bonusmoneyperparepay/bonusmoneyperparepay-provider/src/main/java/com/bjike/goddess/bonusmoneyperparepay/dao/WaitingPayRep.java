package com.bjike.goddess.bonusmoneyperparepay.dao;

import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.entity.WaitingPay;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 等待付款持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitingPayRep extends JpaRep<WaitingPay, WaitingPayDTO> {

}