package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.ComputerSubsidiesBO;
import com.bjike.goddess.assistance.dto.ComputerSubsidiesDTO;
import com.bjike.goddess.assistance.entity.ComputerSubsidies;
import com.bjike.goddess.assistance.to.ComputerSubsidiesAddTO;
import com.bjike.goddess.assistance.to.ComputerSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.ComputerSubsidiesTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 电脑补助业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ComputerSubsidiesSer extends Ser<ComputerSubsidies, ComputerSubsidiesDTO> {
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
     *电脑补助列表总条数
     */
    default Long countComputer(ComputerSubsidiesDTO computerSubsidiesDTO) throws SerException {
        return null;
    }

    /**
     * 一个电脑补助
     *
     * @return class ComputerSubsidiesBO
     */
    default ComputerSubsidiesBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 电脑补助列表
     *
     * @return class ComputerSubsidiesBO
     */
    default List<ComputerSubsidiesBO> listComputer(ComputerSubsidiesDTO computerSubsidiesDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     */
    default void saveComputer(ComputerSubsidiesAddTO computerSubsidiesAddTO) throws SerException {
        return;
    }

    /**
     * 编辑
     */
    default void editComputer(ComputerSubsidiesTO computerSubsidiesTO) throws SerException {
        return;
    }

    default void deleteTemp(String id) throws SerException {
        return;
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
     * @param computerSubsidiesExcelTOS 工龄补助
     */
    void importExcel(List<ComputerSubsidiesExcelTO> computerSubsidiesExcelTOS) throws SerException;

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
}