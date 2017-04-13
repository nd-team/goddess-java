package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.LaborRelationBO;
import com.bjike.goddess.archive.dto.LaborRelationDTO;
import com.bjike.goddess.archive.service.LaborRelationSer;
import com.bjike.goddess.archive.to.LaborRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 劳动关系类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:05 ]
 * @Description: [ 劳动关系类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("laborRelationApiImpl")
public class LaborRelationApiImpl implements LaborRelationAPI {

    @Autowired
    private LaborRelationSer laborRelationSer;

    @Override
    public LaborRelationBO save(LaborRelationTO to) throws SerException {
        return laborRelationSer.save(to);
    }

    @Override
    public LaborRelationBO update(LaborRelationTO to) throws SerException {
        return laborRelationSer.update(to);
    }

    @Override
    public LaborRelationBO delete(String id) throws SerException {
        return laborRelationSer.delete(id);
    }

    @Override
    public LaborRelationBO congeal(String id) throws SerException {
        return laborRelationSer.congeal(id);
    }

    @Override
    public LaborRelationBO thaw(String id) throws SerException {
        return laborRelationSer.thaw(id);
    }

    @Override
    public List<LaborRelationBO> findByStatus(Status status) throws SerException {
        return laborRelationSer.findByStatus(status);
    }

    @Override
    public List<LaborRelationBO> maps(LaborRelationDTO dto) throws SerException {
        return laborRelationSer.maps(dto);
    }
}