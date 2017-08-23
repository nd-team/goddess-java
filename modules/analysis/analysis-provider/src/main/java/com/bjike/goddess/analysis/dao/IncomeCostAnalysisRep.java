package com.bjike.goddess.analysis.dao;

import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.entity.IncomeCostAnalysis;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 收入成本分析持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IncomeCostAnalysisRep extends JpaRep<IncomeCostAnalysis, IncomeCostAnalysisDTO> {

}