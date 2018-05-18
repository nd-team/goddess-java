package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FirstPhoneRecordSer extends Ser<FirstPhoneRecord, FirstPhoneRecordDTO> {
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
     * 分页查询第一次电访记录
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FirstPhoneRecordBO> list(FirstPhoneRecordDTO dto) throws SerException;

    /**
     * 保存第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @return
     * @throws SerException
     */
    FirstPhoneRecordBO save(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException;

    /**
     * 根据id删除第一次电访记录
     *
     * @param id 第一次电访记录id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新第一次电访记录
     *
     * @param firstPhoneRecordTO
     * @throws SerException
     */
    void update(FirstPhoneRecordTO firstPhoneRecordTO) throws SerException;

    /**
     * 查找所有第一次电放记录的姓名名单
     *
     * @return
     * @throws SerException
     */
    Set<String> allFirstName() throws SerException;

    /**
     * 导入
     *
     * @param firstPhoneRecordTOS 第一次电放记录
     * @return class FirstPhoneRecordBO
     */
    default FirstPhoneRecordBO importExcel(List<FirstPhoneRecordTO> firstPhoneRecordTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(FirstPhoneRecordDTO dto) throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;
}
