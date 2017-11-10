package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.AuditTimeSetBO;
import com.bjike.goddess.attendance.dto.AuditTimeSetDTO;
import com.bjike.goddess.attendance.service.AuditTimeSetSer;
import com.bjike.goddess.attendance.to.AuditTimeSetTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批时间设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:37 ]
 * @Description: [ 审批时间设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("auditTimeSetApiImpl")
public class AuditTimeSetApiImpl implements AuditTimeSetAPI {
    @Autowired
    private AuditTimeSetSer auditTimeSetSer;

    @Override
    public List<AuditTimeSetBO> list(AuditTimeSetDTO dto) throws SerException {
        return auditTimeSetSer.list(dto);
    }

    @Override
    public AuditTimeSetBO save(AuditTimeSetTO to) throws SerException {
        return auditTimeSetSer.save(to);
    }

    @Override
    public void edit(AuditTimeSetTO to) throws SerException {
        auditTimeSetSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        auditTimeSetSer.delete(id);
    }

    @Override
    public AuditTimeSetBO findByID(String id) throws SerException {
        return auditTimeSetSer.findByID(id);
    }

    @Override
    public Long count(AuditTimeSetDTO dto) throws SerException {
        return auditTimeSetSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return auditTimeSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return auditTimeSetSer.guidePermission(guidePermissionTO);
    }
}