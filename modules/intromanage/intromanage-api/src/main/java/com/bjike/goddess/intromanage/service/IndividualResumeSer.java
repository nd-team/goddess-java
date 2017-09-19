package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.intromanage.bo.IndividualResumeBO;
import com.bjike.goddess.intromanage.bo.SummationBO;
import com.bjike.goddess.intromanage.dto.IndividualResumeDTO;
import com.bjike.goddess.intromanage.entity.IndividualResume;
import com.bjike.goddess.intromanage.excel.SonPermissionObject;
import com.bjike.goddess.intromanage.to.GuidePermissionTO;
import com.bjike.goddess.intromanage.to.IndividualDisplayFieldTO;
import com.bjike.goddess.intromanage.to.IndividualResumeTO;

import java.util.List;

/**
 * 个人简介业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndividualResumeSer extends Ser<IndividualResume, IndividualResumeDTO> {


    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 分页查询个人简介
     *
     * @return class IndividualResumeBO
     * @throws SerException
     */
    List<IndividualResumeBO> list(IndividualResumeDTO dto) throws SerException;

    /**
     *一个个人简介
     *
     * @return class IndividualResumeBO
     * @throws SerException
     */
    IndividualResumeBO findResumeById(String id) throws SerException;

    /**
     * 保存个人简介
     *
     * @param to 个人简介to
     * @return class IndividualResumeBO
     * @throws SerException
     */
    IndividualResumeBO save(IndividualResumeTO to) throws SerException;

    /**
     * 根据id删除个人简介
     *
     * @param id 个人简介唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新个人简介
     *
     * @param to 个人简介to
     * @throws SerException
     */
    void update(IndividualResumeTO to) throws SerException;

    /**
     * 设置个人简介显示字段
     *
     * @param username 用户名称数组
     * @param to       个人简介显示字段
     * @throws SerException
     */
    void setIndividualDisplayField(String[] username, IndividualDisplayFieldTO to) throws SerException;
    /**
     * 冻结公司简介
     *
     * @param id id
     */
    default void congealFirmin(String id) throws SerException {
        return;
    }


    /**
     * 解冻公司简介
     *
     * @param id id
     */
    default void thawFirmin(String id) throws SerException {
        return;
    }

    /**
     * 转正管理周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class SummationBO
     * @throws SerException
     */
    default SummationBO summaWeek(Integer year, Integer month, Integer week) throws SerException{
        return null;
    }
    /**
     * 转正管理月汇总
     * @param year 年份
     * @param month 月份
     * @return class SummationBO
     * @throws SerException
     */
    default SummationBO summaMonth(Integer year,Integer month) throws SerException{
        return null;
    }
    /**
     * 转正管理累计汇总
     * @param endDate 截止日期
     * @return class SummationBO
     * @throws SerException
     */
    default SummationBO summaTotal(String endDate) throws SerException{
        return null;
    }

}