package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.CollectEmailBO;
import com.bjike.goddess.businessproject.dto.CollectEmailDTO;
import com.bjike.goddess.businessproject.entity.CollectEmail;
import com.bjike.goddess.businessproject.to.CollectEmailTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 客户邮件发送定制业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.883 ]
 * @Description: [ 客户邮件发送定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectEmailSer extends Ser<CollectEmail, CollectEmailDTO> {

    /**
     * 总条数
     */
    default Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        return null;
    }


    /**
     * 一个个邮件
     * @return class CollectEmailBO
     */
    default CollectEmailBO getOne(String id) throws SerException {return null;}

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

    ;

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

    ;

    /**
     * 汇总合同签订与立项
     *
     * @param area area
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> collectCollectEmail(String[] area) throws SerException {
        return null;
    }


    /**
     * 汇总合同基本信息
     *
     * @param firstCompany 甲方公司
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> collectBaseInfoEmail(String[] firstCompany) throws SerException {
        return null;
    }

    ;

    /**
     * 汇总派工合同信息
     *
     * @param area 地区
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> collectDispatchEmail(String[] area) throws SerException {
        return null;
    }

    ;

}