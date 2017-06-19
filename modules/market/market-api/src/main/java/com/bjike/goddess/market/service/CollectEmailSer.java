package com.bjike.goddess.market.service;

import com.bjike.goddess.market.bo.CollectEmailBO;
import com.bjike.goddess.market.bo.MarketCollectBO;
import com.bjike.goddess.market.dto.CollectEmailDTO;
import com.bjike.goddess.market.entity.CollectEmail;
import com.bjike.goddess.market.to.CollectEmailTO;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 市场信息邮件发送定制业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.883 ]
 * @Description: [ 市场信息邮件发送定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectEmailSer extends Ser<CollectEmail, CollectEmailDTO> {


    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 总条数
     */
    default Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        return null;
    }


    /**
     * 一个个邮件
     *
     * @return class CollectEmailBO
     */
    default CollectEmailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 客户邮件汇总列表
     *
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param collectEmailTO 客户邮件汇总信息
     * @return class CollectEmailBO
     */
    default CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param collectEmailTO 客户邮件汇总信息
     * @return class CollectEmailBO
     */
    default CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return null;
    }

    /**
     * 删除邮件汇总
     *
     * @param id id
     */
    default void deleteCollectEmail(String id) throws SerException {
        return;
    }
    /**
     * 冻结客户邮件汇总
     *
     * @param id id
     */
    default void congealCollectEmail(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻客户邮件汇总
     *
     * @param id id
     */
    default void thawCollectEmail(String id) throws SerException {
        return;
    }
    /**
     * 汇总
     *
     * @param areas
     * @return class MarketEmailBO
     */
    default List<MarketCollectBO> marketCollect(String[] areas) throws SerException {
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



    /**
     * 定时器检测要发送的邮件
     */
    default void checkSendEmail() throws SerException {
        return;
    }


}