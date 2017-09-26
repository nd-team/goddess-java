package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.TemperatureSubsidiesBO;
import com.bjike.goddess.assistance.dto.TemperatureSubsidiesDTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesTO;
import com.bjike.goddess.assistance.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 高温补助业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TemperatureSubsidiesAPI {
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
     * 高温补助列表总条数
     */
    default Long countTempera(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws SerException {
        return null;
    }

    /**
     * 一个高温补助
     *
     * @return class TemperatureSubsidiesBO
     */
    default TemperatureSubsidiesBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 高温补助列表
     *
     * @return class TemperatureSubsidiesBO
     */
    default List<TemperatureSubsidiesBO> listTempera(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     */
    default void saveTempera(TemperatureSubsidiesTO temperatureSubsidiesTO) throws SerException {
        return;
    }

    /**
     * 编辑
     */
    default void editTempera(TemperatureSubsidiesTO temperatureSubsidiesTO) throws SerException {
        return;
    }

    /**
     * 删除
     * @param id
     * @throws SerException
     */
    default void deleteTemp(String id) throws SerException{
        return ;
    }
    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param temperatureSubsidiesExcelTOS 工龄补助
     */
    void importExcel(List<TemperatureSubsidiesExcelTO> temperatureSubsidiesExcelTOS) throws SerException;

    /**
     * 提醒确认
     *
     * @param id 高温补助id
     */
    default void remindingConfirm(String id) throws SerException {
        return;
    }

    /**
     * 确认
     *
     * @param id 高温补助id
     */
    default void confirm(String id, Boolean confirm) throws SerException {
        return;
    }

    /**
     * 根据计薪周期开始时间和结束时间获取高温补助
     */
    TemperatureSubsidiesBO findTemperature(String paytStartTime,String payEndTime) throws SerException;
}