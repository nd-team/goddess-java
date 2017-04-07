package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.MarketSesponseBO;
import com.bjike.goddess.businessevaluate.dto.MarketSesponseDTO;
import com.bjike.goddess.businessevaluate.entity.MarketSesponse;
import com.bjike.goddess.businessevaluate.to.MarketSesponseTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 市场反应和创新能力业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketSesponseSer extends Ser<MarketSesponse, MarketSesponseDTO> {

    /**
     * 添加市场反应和创新能力
     *
     * @param to 市场反应和创新能力
     * @return 市场反应和创新能力
     */
    MarketSesponseBO addModel(MarketSesponseTO to) throws SerException;

    /**
     * 编辑市场反应和创新能力
     *
     * @param to 市场反应和创新能力
     * @return 市场反应和创新能力
     */
    MarketSesponseBO updateModel(MarketSesponseTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 市场反应和创新能力结果集
     */
    List<MarketSesponseBO> pageList(MarketSesponseDTO dto) throws SerException;
}