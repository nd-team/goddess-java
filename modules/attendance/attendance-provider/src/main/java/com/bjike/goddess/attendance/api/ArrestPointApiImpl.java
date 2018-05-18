package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.ArrestPointBO;
import com.bjike.goddess.attendance.dto.ArrestPointDTO;
import com.bjike.goddess.attendance.service.ArrestPointSer;
import com.bjike.goddess.attendance.to.ArrestPointTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 驻点设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("arrestPointApiImpl")
public class ArrestPointApiImpl implements ArrestPointAPI {
    @Autowired
    private ArrestPointSer arrestPointSer;

    @Override
    public ArrestPointBO save(ArrestPointTO to) throws SerException {
        return arrestPointSer.save(to);
    }

    @Override
    public void edit(ArrestPointTO to) throws SerException {
        arrestPointSer.edit(to);
    }

    @Override
    public List<ArrestPointBO> list(ArrestPointDTO dto) throws SerException {
        return arrestPointSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        arrestPointSer.delete(id);
    }

    @Override
    public ArrestPointBO findByID(String id) throws SerException {
        return arrestPointSer.findByID(id);
    }

    @Override
    public Long count(ArrestPointDTO dto) throws SerException {
        return arrestPointSer.count(dto);
    }

    @Override
    public void start(String id) throws SerException {
        arrestPointSer.start(id);
    }

    @Override
    public void stop(String id) throws SerException {
        arrestPointSer.stop(id);
    }

    @Override
    public Set<String> pointAreas() throws SerException {
        return arrestPointSer.pointAreas();
    }
}