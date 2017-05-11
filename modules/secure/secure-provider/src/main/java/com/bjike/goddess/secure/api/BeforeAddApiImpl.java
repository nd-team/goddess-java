package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.service.BeforeAddSer;
import com.bjike.goddess.secure.to.BeforeAddTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 增员前业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("beforeAddApiImpl")
public class BeforeAddApiImpl implements BeforeAddAPI {
    @Autowired
    private BeforeAddSer beforeAddSer;

    @Override
    public BeforeAddBO save(BeforeAddTO to) throws SerException {
        return beforeAddSer.save(to);
    }

    @Override
    public BeforeAddBO completeAndConfirm(BeforeAddTO to) throws SerException {
        return beforeAddSer.completeAndConfirm(to);
    }

    @Override
    public BeforeAddBO delete(String id) throws SerException {
        return beforeAddSer.delete(id);
    }

    @Override
    public List<BeforeAddBO> find(BeforeAddDTO dto) throws SerException {
        return beforeAddSer.find(dto);
    }

    @Override
    public BeforeAddBO findByID(String id) throws SerException {
        return beforeAddSer.findByID(id);
    }
}