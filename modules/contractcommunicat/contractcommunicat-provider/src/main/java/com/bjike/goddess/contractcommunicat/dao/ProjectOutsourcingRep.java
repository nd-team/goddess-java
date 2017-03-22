package com.bjike.goddess.contractcommunicat.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;

/**
 * 项目外包洽谈持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.829 ]
 * @Description: [ 项目外包洽谈持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectOutsourcingRep extends JpaRep<ProjectOutsourcing, ProjectOutsourcingDTO> {

}