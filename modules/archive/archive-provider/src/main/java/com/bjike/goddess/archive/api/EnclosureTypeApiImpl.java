package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.EnclosureTypeBO;
import com.bjike.goddess.archive.dto.EnclosureTypeDTO;
import com.bjike.goddess.archive.service.EnclosureTypeSer;
import com.bjike.goddess.archive.to.EnclosureTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 附件类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 附件类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("enclosureTypeApiImpl")
public class EnclosureTypeApiImpl implements EnclosureTypeAPI {

    @Autowired
    private EnclosureTypeSer enclosureTypeSer;

    @Override
    public EnclosureTypeBO save(EnclosureTypeTO to) throws SerException {
        return enclosureTypeSer.save(to);
    }

    @Override
    public EnclosureTypeBO update(EnclosureTypeTO to) throws SerException {
        return enclosureTypeSer.update(to);
    }

    @Override
    public EnclosureTypeBO delete(String id) throws SerException {
        return enclosureTypeSer.delete(id);
    }

    @Override
    public EnclosureTypeBO congeal(String id) throws SerException {
        return enclosureTypeSer.congeal(id);
    }

    @Override
    public EnclosureTypeBO thaw(String id) throws SerException {
        return enclosureTypeSer.thaw(id);
    }

    @Override
    public List<EnclosureTypeBO> findByStatus(Status status) throws SerException {
        return enclosureTypeSer.findByStatus(status);
    }

    @Override
    public List<EnclosureTypeBO> maps(EnclosureTypeDTO dto) throws SerException {
        return enclosureTypeSer.maps(dto);
    }
}