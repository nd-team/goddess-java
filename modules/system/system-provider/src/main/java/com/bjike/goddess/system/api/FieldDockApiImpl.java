package com.bjike.goddess.system.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.FieldDockBO;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.service.FieldDockSer;
import com.bjike.goddess.system.to.FieldDockTO;
import com.bjike.goddess.system.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字段对接业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fieldDockApiImpl")
public class FieldDockApiImpl implements FieldDockAPI {
    @Autowired
    private FieldDockSer fieldDockSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return fieldDockSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return fieldDockSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long count(FieldDockDTO dto) throws SerException {
        return fieldDockSer.count(dto);
    }

    @Override
    public FieldDockBO getOne(String id) throws SerException {
        return fieldDockSer.getOne(id);
    }

    @Override
    public List<FieldDockBO> list(FieldDockDTO dto) throws SerException {
        return fieldDockSer.list(dto);
    }

    @Override
    public FieldDockBO insert(FieldDockTO to) throws SerException {
        return fieldDockSer.insert(to);
    }

    @Override
    public FieldDockBO edit(FieldDockTO to) throws SerException {
        return fieldDockSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        fieldDockSer.remove(id);
    }
    @Override
    public List<String> getProjectName() throws SerException {
        return fieldDockSer.getProjectName();
    }
    @Override
    public FieldDockBO importExcel(List<FieldDockTO> fieldDockTOS) throws SerException {
        return fieldDockSer.importExcel(fieldDockTOS);
    }

    @Override
    public byte[] exportExcel(FieldDockDTO dto) throws SerException {
        return fieldDockSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return fieldDockSer.templateExport();
    }
}