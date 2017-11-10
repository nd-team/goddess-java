package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.OverWorkCountSetBO;
import com.bjike.goddess.attendance.dto.OverWorkCountSetDTO;
import com.bjike.goddess.attendance.service.OverWorkCountSetSer;
import com.bjike.goddess.attendance.to.OverWorkCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加班汇总通报设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:16 ]
 * @Description: [ 加班汇总通报设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("overWorkCountSetApiImpl")
public class OverWorkCountSetApiImpl implements OverWorkCountSetAPI {
    @Autowired
    private OverWorkCountSetSer overWorkCountSetSer;

    @Override
    public OverWorkCountSetBO save(OverWorkCountSetTO to) throws SerException {
        return overWorkCountSetSer.save(to);
    }

    @Override
    public void edit(OverWorkCountSetTO to) throws SerException {
        overWorkCountSetSer.edit(to);
    }

    @Override
    public List<OverWorkCountSetBO> list(OverWorkCountSetDTO dto) throws SerException {
        return overWorkCountSetSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        overWorkCountSetSer.delete(id);
    }

    @Override
    public OverWorkCountSetBO findByID(String id) throws SerException {
        return overWorkCountSetSer.findByID(id);
    }

    @Override
    public Long count(OverWorkCountSetDTO dto) throws SerException {
        return overWorkCountSetSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        overWorkCountSetSer.send();
    }
}