package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CollectEmailBO;
import com.bjike.goddess.capability.bo.CollectEmailBO;
import com.bjike.goddess.capability.dto.CollectEmailDTO;
import com.bjike.goddess.capability.to.CollectEmailTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 商业能力邮件发送定制业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.882 ]
 * @Description: [ 客户汇总邮件发送定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectEmailAPI {

    /**
     * 总条数
     */
    default Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        return null;
    }

    /**
     * 商业能力邮件汇总列表
     *
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
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
     * 添加
     *
     * @param collectEmailTO 商业能力邮件汇总信息
     * @return class CollectEmailBO
     */
    default CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param collectEmailTO 商业能力邮件汇总信息
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
     * 冻结商业能力邮件汇总
     *
     * @param id id
     */
    default void congealCollectEmail(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻商业能力邮件汇总
     *
     * @param id id
     */
    default void thawCollectEmail(String id) throws SerException {
        return;
    }

    ;

    /**
     * 汇总商业能力展示
     *
     * @param companyOrName 公司名
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> collectCompanyEmail(String[] companyOrName) throws SerException {
        return null;
    }

    ;

    /**
     * 汇总个人能力展示
     *
     * @param name 员工姓名
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> collectSelfEmail(String[] name) throws SerException {
        return null;
    }

    ;

    /**
     * 汇总对象能力展示
     *
     * @param companyOrName 公司名
     * @return class CollectEmailBO
     */
    default List<CollectEmailBO> collectCooperEmail(String[] companyOrName) throws SerException {
        return null;
    }

    ;

    /**
     * 汇总获取公司名/姓名
     *
     * @param type 汇总类型
     */
    default List<String> listName(String type) throws SerException {
        return null;
    }

    /**
     * 定时器检测要发送的邮件
     */
    default void checkSendEmail() throws SerException {
        return;
    }

    /**
     * 功能导航权限
     * @throws SerException
     * @version v1
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

}