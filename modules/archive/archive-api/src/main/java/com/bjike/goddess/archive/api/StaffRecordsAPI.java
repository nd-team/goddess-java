package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.PerBO;
import com.bjike.goddess.archive.bo.StaffNameBO;
import com.bjike.goddess.archive.bo.StaffRecords1BO;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecords1ExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 员工档案业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffRecordsAPI {

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
     * 上传数据
     *
     * @throws SerException
     */
    void upload(List<StaffRecordsExcelTO> toList) throws SerException;


    /**
     * 根据姓名查询员工档案
     *
     * @param username 姓名
     * @return
     * @throws SerException
     */
    default StaffRecordsBO findByName(String username) throws SerException {
        return null;
    }

    /**
     * 根据编号查询员工档案
     *
     * @param serialNumber
     * @return
     * @throws SerException
     */
    default StaffRecordsBO findByNumber(String serialNumber) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 员工档案数据传输对象
     * @return
     * @throws SerException
     */
    default List<StaffRecordsBO> maps(StaffRecordsDTO dto) throws SerException {
        return null;
    }


    /**
     * 根据id获取员工档案数据
     *
     * @param id 员工档案数据id
     * @return
     * @throws SerException
     */
    default StaffRecordsBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    default List<StaffNameBO> getName() throws SerException {
        return null;
    }

    /**
     * 根据姓名获取员工身份证和电话号码
     */
    default List<PerBO> getPerBO(String name) throws SerException {
        return null;
    }

    /**
     * 在职员工基本信息
     *
     * @return
     * @throws SerException
     */
    default List<StaffRecordsBO> listEmployee() throws SerException {
        return null;
    }

    /**
     * 导出模板excel
     *
     * @return
     * @throws SerException
     */
    byte[] templateExcel() throws SerException;

    void dimissionUpload(List<StaffRecords1ExcelTO> toList) throws SerException;

    /**
     * 离职人员列表
     *
     * @param dto 员工档案数据传输对象
     * @return
     * @throws SerException
     */
    List<StaffRecords1BO> dimissionMaps(StaffRecordsDTO dto) throws SerException;


    /**
     * 获取离职人员总条数
     */
    default Long count() throws SerException {
        return null;
    }

    /**
     * 导出离职模板
     *
     * @return
     */
    byte[] templateDimissionExcel() throws SerException;
}