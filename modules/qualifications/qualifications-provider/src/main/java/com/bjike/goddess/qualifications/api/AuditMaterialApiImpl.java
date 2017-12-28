package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.dto.AuditMaterialDTO;
import com.bjike.goddess.qualifications.service.AuditMaterialSer;
import com.bjike.goddess.qualifications.to.AuditMaterialTO;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审核资料业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("auditMaterialApiImpl")
public class AuditMaterialApiImpl implements AuditMaterialAPI {

    @Autowired
    private AuditMaterialSer auditMaterialSer;

    @Override
    public AuditMaterialBO save(AuditMaterialTO to) throws SerException {
        return auditMaterialSer.save(to);
    }

    @Override
    public AuditMaterialBO update(AuditMaterialTO to) throws SerException {
        return auditMaterialSer.update(to);
    }

    @Override
    public AuditMaterialBO delete(String id) throws SerException {
        return auditMaterialSer.delete(id);
    }

    @Override
    public List<AuditMaterialBO> all() throws SerException {
        return auditMaterialSer.all();
    }

    @Override
    public List<AuditMaterialBO> maps(AuditMaterialDTO dto) throws SerException {
        return auditMaterialSer.maps(dto);
    }

    @Override
    public Integer getTotal() throws SerException {
        return auditMaterialSer.getTotal();
    }

    @Override
    public AuditMaterialBO getById(String id) throws SerException {
        return auditMaterialSer.getById(id);
    }
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return auditMaterialSer.guidePermission( guidePermissionTO );
    }
}