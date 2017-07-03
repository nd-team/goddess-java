package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AttachedEndBO;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.service.AttachedEndSer;
import com.bjike.goddess.secure.to.AttachedEndTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 挂靠到期业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:04 ]
 * @Description: [ 挂靠到期业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("attachedEndApiImpl")
public class AttachedEndApiImpl implements AttachedEndAPI {
    @Autowired
    private AttachedEndSer attachedEndSer;

    @Override
    public AttachedEndBO save(AttachedEndTO to) throws SerException {
        return attachedEndSer.save(to);
    }

    @Override
    public AttachedEndBO is_Again(AttachedEndTO to) throws SerException {
        return attachedEndSer.is_Again(to);
    }

    @Override
    public List<AttachedEndBO> find(AttachedEndDTO dto) throws SerException {
        return attachedEndSer.find(dto);
    }

    @Override
    public AttachedEndBO findByID(String id) throws SerException {
        return attachedEndSer.findByID(id);
    }

    @Override
    public AttachedEndBO delete(String id) throws SerException {
        return attachedEndSer.delete(id);
    }

    @Override
    public void send() throws SerException {
        attachedEndSer.send();
    }

    @Override
    public void quartz() throws SerException {
        attachedEndSer.quartz();
    }

    @Override
    public Long count(AttachedEndDTO dto) throws SerException {
        return attachedEndSer.count(dto);
    }
}