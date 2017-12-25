package com.bjike.goddess.system.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.AuswerBO;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.service.AuswerSer;
import com.bjike.goddess.system.to.AuswerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 答案业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("auswerApiImpl")
public class AuswerApiImpl implements AuswerAPI {
    @Autowired
    private AuswerSer auswerSer;
    @Override
    public Long count(AuswerDTO dto) throws SerException {
        return auswerSer.count(dto);
    }
    @Override
    public AuswerBO getOne(String id) throws SerException {
        return auswerSer.getOne(id);
    }

    @Override
    public List<AuswerBO> list(AuswerDTO dto) throws SerException {
        return auswerSer.list(dto);
    }

    @Override
    public AuswerBO insert(AuswerTO to) throws SerException {
        return auswerSer.insert(to);
    }

    @Override
    public AuswerBO edit(AuswerTO to) throws SerException {
        return auswerSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        auswerSer.remove(id);
    }

}