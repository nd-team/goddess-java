package com.bjike.goddess.contractware.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractware.dto.ProjectContractDTO;
import com.bjike.goddess.contractware.entity.ProjectContract;

/**
 * 项目合同持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目合同持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectContractRep extends JpaRep<ProjectContract, ProjectContractDTO> {

}