package com.bjike.goddess.businessevaluate.dao;

import com.bjike.goddess.businessevaluate.dto.ProjectAmountDTO;
import com.bjike.goddess.businessevaluate.entity.ProjectAmount;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 项目金额持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectAmountRep extends JpaRep<ProjectAmount, ProjectAmountDTO> {

}