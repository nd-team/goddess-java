package com.bjike.goddess.businessevaluate.dao;

import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.entity.BusinessEvaluateCollect;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 商务评估汇总持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessEvaluateCollectRep extends JpaRep<BusinessEvaluateCollect, BusinessEvaluateCollectDTO> {

}