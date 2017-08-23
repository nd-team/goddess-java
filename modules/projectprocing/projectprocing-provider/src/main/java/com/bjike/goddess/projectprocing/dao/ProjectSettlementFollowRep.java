package com.bjike.goddess.projectprocing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectprocing.dto.ProjectSettlementFollowDTO;
import com.bjike.goddess.projectprocing.entity.ProjectSettlementFollow;

/**
 * 项目结算跟进持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectSettlementFollowRep extends JpaRep<ProjectSettlementFollow, ProjectSettlementFollowDTO> {

}