package com.bjike.goddess.receivable.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.receivable.dto.MainIncomeDTO;
import com.bjike.goddess.receivable.entity.MainIncome;

/**
 * 主营业务收入持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MainIncomeRep extends JpaRep<MainIncome, MainIncomeDTO> {

}