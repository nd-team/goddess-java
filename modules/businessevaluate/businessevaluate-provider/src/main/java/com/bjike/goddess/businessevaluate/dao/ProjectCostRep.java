package com.bjike.goddess.businessevaluate.dao;

import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.entity.ProjectCost;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 项目费用持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 02:17 ]
 * @Description: [ 项目费用持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectCostRep extends JpaRep<ProjectCost, ProjectCostDTO> {

}