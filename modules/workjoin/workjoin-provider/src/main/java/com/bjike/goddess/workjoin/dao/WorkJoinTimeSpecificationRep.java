package com.bjike.goddess.workjoin.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workjoin.dto.WorkJoinTimeSpecificationDTO;
import com.bjike.goddess.workjoin.entity.WorkJoinTimeSpecification;

/**
 * 工作交接时间规范持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkJoinTimeSpecificationRep extends JpaRep<WorkJoinTimeSpecification, WorkJoinTimeSpecificationDTO> {

}