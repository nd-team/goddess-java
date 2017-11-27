package com.bjike.goddess.contractcommunicat.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractcommunicat.dto.HistoryAppraiseDTO;
import com.bjike.goddess.contractcommunicat.entity.HistoryAppraise;

/**
 * 历史评价持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:56 ]
 * @Description: [ 历史评价持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HistoryAppraiseRep extends JpaRep<HistoryAppraise, HistoryAppraiseDTO> {

}