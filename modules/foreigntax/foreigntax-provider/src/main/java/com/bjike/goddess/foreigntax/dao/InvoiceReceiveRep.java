package com.bjike.goddess.foreigntax.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.foreigntax.dto.InvoiceReceiveDTO;
import com.bjike.goddess.foreigntax.entity.InvoiceReceive;

/**
 * 发票领用持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InvoiceReceiveRep extends JpaRep<InvoiceReceive, InvoiceReceiveDTO> {

}