package com.bjike.goddess.contractquotemanager.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractNodeStandard;

/**
 * 合同节点标准信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.725 ]
 * @Description: [ 合同节点标准信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractNodeStandardRep extends JpaRep<ContractNodeStandard, ContractNodeStandardDTO> {

}