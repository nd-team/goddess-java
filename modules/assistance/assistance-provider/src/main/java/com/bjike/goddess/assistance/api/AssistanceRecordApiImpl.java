package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.AssistanceRecordBO;
import com.bjike.goddess.assistance.dto.AssistanceRecordDTO;
import com.bjike.goddess.assistance.service.AssistanceRecordSer;
import com.bjike.goddess.assistance.to.AssistanceRecordTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司员工补助信息记录业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:07 ]
 * @Description: [ 公司员工补助信息记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assistanceRecordApiImpl")
public class AssistanceRecordApiImpl implements AssistanceRecordAPI {



    @Autowired
    private AssistanceRecordSer assistanceRecordSer;

    @Override
    public Long countAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {
        return assistanceRecordSer.count( assistanceRecordDTO);
    }

    @Override
    public List<AssistanceRecordBO> listAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {
        return assistanceRecordSer.listAssistanceRecord(assistanceRecordDTO);
    }

    @Override
    public AssistanceRecordBO addAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException {
        return assistanceRecordSer.addAssistanceRecord(assistanceRecordTO);
    }

    @Override
    public AssistanceRecordBO editAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException {
        return assistanceRecordSer.editAssistanceRecord(assistanceRecordTO);
    }

    @Override
    public void deleteAssistanceRecord(String id) throws SerException {
        assistanceRecordSer.deleteAssistanceRecord(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return assistanceRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return assistanceRecordSer.guidePermission(guidePermissionTO);
    }
}