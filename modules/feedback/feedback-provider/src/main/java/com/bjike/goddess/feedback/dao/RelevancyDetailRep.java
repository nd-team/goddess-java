package com.bjike.goddess.feedback.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.feedback.dto.RelevancyDetailDTO;
import com.bjike.goddess.feedback.entity.RelevancyDetail;

/**
 * 各模块关联明细持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:23 ]
 * @Description: [ 各模块关联明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RelevancyDetailRep extends JpaRep<RelevancyDetail, RelevancyDetailDTO> {

}