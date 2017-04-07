package com.bjike.goddess.businessevaluate.dao;

import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 项目基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EvaluateProjectInfoRep extends JpaRep<EvaluateProjectInfo, EvaluateProjectInfoDTO> {

}