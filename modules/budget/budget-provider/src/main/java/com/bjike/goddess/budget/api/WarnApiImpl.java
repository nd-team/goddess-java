package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.WarnBO;
import com.bjike.goddess.budget.dto.WarnDTO;
import com.bjike.goddess.budget.service.WarnSer;
import com.bjike.goddess.budget.to.WarnTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预警业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-17 10:35 ]
 * @Description: [ 预警业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("warnApiImpl")
public class WarnApiImpl implements WarnAPI {
    @Autowired
    private WarnSer warnSer;

    @Override
    public WarnBO save(WarnTO to) throws SerException {
        return warnSer.save(to);
    }

    @Override
    public void edit(WarnTO to) throws SerException {
        warnSer.edit(to);
    }

    @Override
    public List<WarnBO> list(WarnDTO dto) throws SerException {
        return warnSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        warnSer.delete(id);
    }

    @Override
    public WarnBO findByID(String id) throws SerException {
        return warnSer.findByID(id);
    }

    @Override
    public Long countNum(WarnDTO dto) throws SerException {
        return warnSer.countNum(dto);
    }
}