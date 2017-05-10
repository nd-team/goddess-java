package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.OutEmployeeBO;
import com.bjike.goddess.secure.dto.OutEmployeeDTO;
import com.bjike.goddess.secure.service.OutEmployeeSer;
import com.bjike.goddess.secure.to.OutEmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 离职名单业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("outEmployeeApiImpl")
public class OutEmployeeApiImpl implements OutEmployeeAPI {
    @Autowired
    private OutEmployeeSer outEmployeeSer;

    @Override
    public void save() throws SerException {
        outEmployeeSer.save();
    }

    @Override
    public OutEmployeeBO is_again(OutEmployeeTO to) throws SerException {
        return outEmployeeSer.is_again(to);
    }

    @Override
    public OutEmployeeBO delete(String id) throws SerException {
        return outEmployeeSer.delete(id);
    }

    @Override
    public List<OutEmployeeBO> find(OutEmployeeDTO dto) throws SerException {
        return outEmployeeSer.find(dto);
    }

    @Override
    public OutEmployeeBO findByID(String id) throws SerException {
        return outEmployeeSer.findByID(id);
    }
}