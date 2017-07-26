package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.service.AttachedSer;
import com.bjike.goddess.secure.to.AttachedTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 挂靠业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("attachedApiImpl")
public class AttachedApiImpl implements AttachedAPI {
    @Autowired
    private AttachedSer attachedSer;

    @Override
    public AttachedBO save(AttachedTO to) throws SerException {
        return attachedSer.save(to);
    }

    @Override
    public AttachedBO delete(String id) throws SerException {
        return attachedSer.delete(id);
    }

    @Override
    public List<AttachedBO> find(AttachedDTO dto) throws SerException {
        return attachedSer.find(dto);
    }

    @Override
    public AttachedBO findByID(String id) throws SerException {
        return attachedSer.findByID(id);
    }

    @Override
    public List<AttachedBO> findALL() throws SerException {
        return attachedSer.findALL();
    }

    @Override
    public AttachedBO complete(AttachedTO to) throws SerException {
        return attachedSer.complete(to);
    }

    @Override
    public Long count(AttachedDTO dto) throws SerException {
        return attachedSer.count(dto);
    }

    @Override
    public AttachedBO edit(AttachedTO to) throws SerException {
        return attachedSer.edit(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return attachedSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return attachedSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void pass(AddEmployeeDTO dto, String id) throws SerException {
        attachedSer.pass(dto,id);
    }

    @Override
    public void notPass(AddEmployeeDTO dto, String id) throws SerException {
        attachedSer.notPass(dto, id);
    }
}