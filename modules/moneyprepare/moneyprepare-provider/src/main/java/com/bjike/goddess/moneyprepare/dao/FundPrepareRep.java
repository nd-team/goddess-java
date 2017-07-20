package com.bjike.goddess.moneyprepare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyprepare.dto.FundPrepareDTO;
import com.bjike.goddess.moneyprepare.entity.FundPrepare;

/**
 * 资金准备持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundPrepareRep extends JpaRep<FundPrepare, FundPrepareDTO> {

}