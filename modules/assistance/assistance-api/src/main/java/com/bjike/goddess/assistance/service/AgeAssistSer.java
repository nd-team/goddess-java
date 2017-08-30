package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.entity.AgeAssist;
import com.bjike.goddess.assistance.to.AgeAssistTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 工龄补助业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AgeAssistSer extends Ser<AgeAssist, AgeAssistDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 工龄补助列表总条数
     */
    default Long countAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        return null;
    }

    /**
     * 一个工龄补助
     *
     * @return class AgeAssistBO
     */
    default AgeAssistBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 工龄补助列表
     *
     * @return class AgeAssistBO
     */
    default List<AgeAssistBO> listAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param ageAssistTO 工龄补助信息
     * @return class AgeAssistBO
     */
    default AgeAssistBO addAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param ageAssistTO 工龄补助信息
     * @return class AgeAssistBO
     */
    default AgeAssistBO editAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        return null;
    }

    /**
     * 删除级别
     *
     * @param id id
     */
    default void deleteAgeAssist(String id) throws SerException {
        return;
    }

    ;

    /**
     * 根据员工姓名获取工龄
     *
     * @param userName
     * @return
     * @throws SerException
     */
    default Double getJobAge(String userName) throws SerException {
        return null;
    }

    /**
     * 根据计薪周期开始时间和结束时间获取工龄补助信息
     */
    AgeAssistBO findAge(String employeeName) throws SerException;



}