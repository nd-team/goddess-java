package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
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
}