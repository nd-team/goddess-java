package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistanceRecordBO;
import com.bjike.goddess.assistance.to.AssistanceRecordTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.assistance.entity.AssistanceRecord;
import com.bjike.goddess.assistance.dto.AssistanceRecordDTO;

import java.util.List;

/**
 * 公司员工补助信息记录业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:07 ]
 * @Description: [ 公司员工补助信息记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssistanceRecordSer extends Ser<AssistanceRecord, AssistanceRecordDTO> {
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
     * 一个公司员工补助
     * @return class AssistanceRecordBO
     */
    default AssistanceRecordBO getOneById(String id) throws SerException {return null;}


    /**
     * 公司员工补助列表总条数
     *
     */
    default Long countAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {
        return null;
    }
    /**
     * 公司员工补助列表
     * @return class AssistanceRecordBO
     */
    default List<AssistanceRecordBO> listAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {return null;}
    /**
     *  添加
     * @param assistanceRecordTO 公司员工补助信息
     * @return class AssistanceRecordBO
     */
    default AssistanceRecordBO addAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException { return null;}

    /**
     *  编辑
     * @param assistanceRecordTO 公司员工补助信息
     * @return class AssistanceRecordBO
     */
    default AssistanceRecordBO editAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteAssistanceRecord(String id ) throws SerException {return;};

}