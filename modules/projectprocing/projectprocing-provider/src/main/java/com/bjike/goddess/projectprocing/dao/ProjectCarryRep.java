package com.bjike.goddess.projectprocing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectprocing.dto.ProjectCarryDTO;
import com.bjike.goddess.projectprocing.entity.ProjectCarry;

/**
 * 项目实施持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:33 ]
 * @Description: [ 项目实施持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectCarryRep extends JpaRep<ProjectCarry, ProjectCarryDTO> {

}