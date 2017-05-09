package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.market.bo.MarketCollectBO;
import com.bjike.goddess.market.bo.MarketEmailBO;
import com.bjike.goddess.market.dto.MarketEmailDTO;
import com.bjike.goddess.market.entity.MarketEmail;
import com.bjike.goddess.market.to.MarketEmailTO;

import java.util.List;

/**
 * 市场邮件发送定制业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-22T19:08:18.883 ]
 * @Description: [ 市场邮件发送定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketEmailSer extends Ser<MarketEmail, MarketEmailDTO> {

    /**
     * 总条数
     */
    default Long counts(MarketEmailDTO marketEmailDTO) throws SerException {
        return null;
    }


    /**
     * 一个邮件
     * @return class MarketEmailBO
     */
    default MarketEmailBO getOne(String id) throws SerException {return null;}


    /**
     * 市场信息邮件汇总列表
     *
     * @return class MarketEmailBO
     */
    default List<MarketEmailBO> listMarketEmail(MarketEmailDTO marketEmailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param marketEmailTO 市场信息邮件汇总信息
     * @return class MarketEmailBO
     */
    default MarketEmailBO addMarketEmail(MarketEmailTO marketEmailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param marketEmailTO 市场信息邮件汇总信息
     * @return class MarketEmailBO
     */
    default MarketEmailBO editMarketEmail(MarketEmailTO marketEmailTO) throws SerException {
        return null;
    }

    /**
     * 删除邮件汇总
     *
     * @param id id
     */
    default void deleteMarketEmail(String id) throws SerException {
        return;
    }

    /**
     * 汇总
     *
     * @param areas
     * @return class MarketEmailBO
     */
    default List<MarketCollectBO> marketCollect (String[] areas) throws SerException {
        return null;
    }
    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }

}