package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.CusEmailBO;
import com.bjike.goddess.customer.entity.CusEmail;
import com.bjike.goddess.customer.dto.CusEmailDTO;
import com.bjike.goddess.customer.to.CusEmailTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;

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
public interface CusEmailSer extends Ser<CusEmail, CusEmailDTO> {


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
     * 客户汇总邮件列表总条数
     *
     */
    default Long countCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        return null;
    }

    /**
     * 客户邮件汇总列表
     *
     * @return class CusEmailBO
     */
    default List<CusEmailBO> listCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param cusEmailTO 客户邮件汇总信息
     * @return class CusEmailBO
     */
    default CusEmailBO addCusEmail(CusEmailTO cusEmailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param cusEmailTO 客户邮件汇总信息
     * @return class CusEmailBO
     */
    default CusEmailBO editCusEmail(CusEmailTO cusEmailTO) throws SerException {
        return null;
    }

    /**
     * 删除邮件汇总
     *
     * @param id id
     */
    default void deleteCusEmail(String id) throws SerException {
        return;
    }

    ;

    /**
     * 冻结客户邮件汇总
     *
     * @param id id
     */
    default void congealCusEmail(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻客户邮件汇总
     *
     * @param id id
     */
    default void thawCusEmail(String id) throws SerException {
        return;
    }

    ;


    /**
     * 汇总
     *
     * @param works works
     * @return class CusEmailBO
     */
    default List<CusEmailBO> collectCusEmail(String[] works) throws SerException {
        return null;
    }

    ;

    /**
     * 某个
     *
     * @param id id
     * @return class CusEmailBO
     */
    default CusEmailBO getCusEmailById(String id) throws SerException {
        return null;
    }

    ;


    /**
     * 定时器检测要发送的邮件
     *
     */
    default void checkSendEmail( ) throws SerException {
        return ;
    }

    ;


}