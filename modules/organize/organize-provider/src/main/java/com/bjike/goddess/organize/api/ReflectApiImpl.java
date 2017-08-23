package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.ReflectBO;
import com.bjike.goddess.organize.dto.ReflectDTO;
import com.bjike.goddess.organize.service.ReflectSer;
import com.bjike.goddess.organize.to.ReflectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 体现类型操作业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("reflectApiImpl")
public class ReflectApiImpl implements ReflectAPI {

    @Autowired
    private ReflectSer reflectSer;

    @Override
    public List<ReflectBO> findStatus() throws SerException {
        return reflectSer.findStatus();
    }

    @Override
    public ReflectBO save(ReflectTO to) throws SerException {
        return reflectSer.save(to);
    }

    @Override
    public ReflectBO update(ReflectTO to) throws SerException {
        return reflectSer.update(to);
    }

    @Override
    public ReflectBO delete(String id) throws SerException {
        return reflectSer.delete(id);
    }

    @Override
    public ReflectBO close(String id) throws SerException {
        return reflectSer.close(id);
    }

    @Override
    public ReflectBO open(String id) throws SerException {
        return reflectSer.open(id);
    }

    @Override
    public List<ReflectBO> maps(ReflectDTO dto) throws SerException {
        return reflectSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        ReflectDTO dto = new ReflectDTO();
        return reflectSer.count(dto);
    }

    @Override
    public ReflectBO findById(String id) throws SerException {
        return reflectSer.getById(id);
    }
}
