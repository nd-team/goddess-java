package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.SendEmailBO;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.entity.SendEmail;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;

import java.util.List;

/**
 * 物质购买发送邮件业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SendEmailSer extends Ser<SendEmail, SendEmailDTO> {
    /**
     * 总条数
     */
    default Long counts(SendEmailDTO buySendEmailDTO) throws SerException {
        return null;
    }


    /**
     * 一个个邮件
     *
     * @return class BuySendEmailBO
     */
    default SendEmailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 邮件汇总列表
     *
     * @return class BuySendEmailBO
     */
    default List<SendEmailBO> listCollectEmail(SendEmailDTO buySendEmailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param buySendEmailTO 邮件汇总信息
     * @return class BuySendEmailBO
     */
    default SendEmailBO addCollectEmail(SendEmailTO buySendEmailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param buySendEmailTO 邮件汇总信息
     * @return class BuySendEmailBO
     */
    default SendEmailBO editCollectEmail(SendEmailTO buySendEmailTO) throws SerException {
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
     * 冻结邮件汇总
     *
     * @param id id
     */
    default void congealCollectEmail(String id) throws SerException {
        return;
    }


    /**
     * 解冻邮件汇总
     *
     * @param id id
     */
    default void thawCollectEmail(String id) throws SerException {
        return;
    }

    /**
     * 根据模块名获取所有的汇总类型
     *
     * @param moduleType
     */
    default List<SummaryType> summaryType(ModuleType moduleType) throws SerException {
        return null;
    }
}