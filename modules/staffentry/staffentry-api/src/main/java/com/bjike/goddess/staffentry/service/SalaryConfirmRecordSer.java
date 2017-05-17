package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.staffentry.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.staffentry.entity.SalaryConfirmRecord;
import com.bjike.goddess.staffentry.to.SalaryConfirmRecordTO;

import java.util.List;

/**
 * 薪资确认业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 17:25]
 * @Description: [薪资确认业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface SalaryConfirmRecordSer extends Ser<SalaryConfirmRecord,SalaryConfirmRecordDTO> {

    /**
     * 薪资确认列表总条数
     */
    default Long countSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取薪资确认
     *
     * @return class SalaryConfirmRecordBO
     */
    default SalaryConfirmRecordBO getOne(String id ) throws SerException {
        return null;
    }
    /**
     * 获取所有薪资确认
     * @param salaryConfirmRecordDTO 薪资确认dto
     * @return class SalaryConfirmRecordBO
     * @throws SerException
     */
    default List<SalaryConfirmRecord> listSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws SerException {
        return null;
    }

    /**
     * 添加薪资确认基本信息
     * @param salaryConfirmRecordTO   薪资确认基本信息数据to
     * @return class SalaryConfirmRecordBO
     * @throws SerException
     */
    default SalaryConfirmRecordBO insertSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {
        return null;
    }

    ;


    /**
     * 编辑薪资确认基本信息
     *
     * @param salaryConfirmRecordTO   薪资确认基本信息数据to
     * @return class SalaryConfirmRecordBO
     * @throws SerException
     */
    default SalaryConfirmRecordBO editSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {
        return null;
    }

    ;


    /**
     * 根据id删除入职基本信息
     *
     * @param id
     * @throws SerException
     */
    default void removeSalaryConfirmRecord(String id) throws SerException {
        return;
    }
}
