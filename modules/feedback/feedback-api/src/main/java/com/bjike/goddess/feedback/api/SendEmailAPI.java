package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.SendEmailBO;
import com.bjike.goddess.feedback.dto.SendEmailDTO;
import com.bjike.goddess.feedback.to.SendEmailTO;

import java.util.List;

/**
 * 发送邮件业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-05 10:27 ]
 * @Description: [ 发送邮件业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SendEmailAPI {
    /**
     * 总条数
     */
    default Long counts(SendEmailDTO sendEmailDTO) throws SerException {
        return null;
    }


    /**
     * 一个邮件
     *
     * @return class SendEmailBO
     */
    default SendEmailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 客户邮件汇总列表
     *
     * @return class SendEmailBO
     */
    default List<SendEmailBO> list(SendEmailDTO sendEmailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param sendEmailTO 客户邮件汇总信息
     * @return class SendEmailBO
     */
    default SendEmailBO add(SendEmailTO sendEmailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param sendEmailTO 客户邮件汇总信息
     * @return class SendEmailBO
     */
    default SendEmailBO edit(SendEmailTO sendEmailTO) throws SerException {
        return null;
    }

    /**
     * 删除邮件汇总
     *
     * @param id id
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 冻结客户邮件汇总
     *
     * @param id id
     */
    default void congeal(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻客户邮件汇总
     *
     * @param id id
     */
    default void thaw(String id) throws SerException {
        return;
    }


    /**
     * 定时器检测要发送的邮件
     */
    default void checkSendEmail() throws SerException {
        return;
    }
}