package com.bjike.goddess.contractcommunicat.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.CollectEmailBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.dto.CollectEmailDTO;
import com.bjike.goddess.contractcommunicat.entity.CollectEmail;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.CollectEmailTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;

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
public interface ContractCollectEmailSer extends Ser<CollectEmail, CollectEmailDTO> {




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
     * 汇总项目承包商务洽谈
     *
     * @param to
     * @return class CollectEmailBO
     */
    default List<ProjectContractCollectBO> gatherPb(CollectEmail to) throws SerException {
        return null;
    }


    /**
     * 汇总项目分包商务洽谈
     *
     * @param to
     * @return class CollectEmailBO
     */
    default List<ProjectOutsourcingCollectBO> gatherPc(CollectEmail to) throws SerException {
        return null;
    }


    ;

    /**
     * 定时器检测要发送的邮件
     *
     */
    default void checkSendEmail() throws SerException {
        return ;
    }

    ;



}