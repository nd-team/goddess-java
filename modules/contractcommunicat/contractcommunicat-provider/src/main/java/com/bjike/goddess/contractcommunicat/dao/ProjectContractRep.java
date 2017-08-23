package com.bjike.goddess.contractcommunicat.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;

/**
 * 项目承包洽谈持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.939 ]
 * @Description: [ 项目承包洽谈持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectContractRep extends JpaRep<ProjectContract, ProjectContractDTO> {

}