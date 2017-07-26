package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.AssistanceEmpBO;
import com.bjike.goddess.assistance.dto.AssistanceEmpDTO;
import com.bjike.goddess.assistance.service.AssistanceEmpSer;
import com.bjike.goddess.assistance.to.AssistanceEmpTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 补助员工名单业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:12 ]
 * @Description: [ 补助员工名单业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assistanceEmpApiImpl")
public class AssistanceEmpApiImpl implements AssistanceEmpAPI {

    @Autowired
    private AssistanceEmpSer assistanceEmpSer;

    @Override
    public Long countAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {
        return assistanceEmpSer.count( assistanceEmpDTO);
    }

    @Override
    public List<AssistanceEmpBO> listAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {
        return assistanceEmpSer.listAssistanceEmp(assistanceEmpDTO);
    }

    @Override
    public AssistanceEmpBO addAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException {
        return assistanceEmpSer.addAssistanceEmp(assistanceEmpTO);
    }

    @Override
    public AssistanceEmpBO editAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException {
        return assistanceEmpSer.editAssistanceEmp(assistanceEmpTO);
    }

    @Override
    public void deleteAssistanceEmp(String id) throws SerException {
        assistanceEmpSer.deleteAssistanceEmp(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return assistanceEmpSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return assistanceEmpSer.guidePermission(guidePermissionTO);
    }
}