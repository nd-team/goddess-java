package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.ActualSonBO;
import com.bjike.goddess.staffing.dto.ActualSonDTO;
import com.bjike.goddess.staffing.entity.ActualSon;
import com.bjike.goddess.staffing.service.ActualSonSer;
import com.bjike.goddess.staffing.to.ActualSonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人数配置-实际子表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-03 11:19 ]
 * @Description: [ 人数配置-实际子表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("actualSonApiImpl")
public class ActualSonApiImpl implements ActualSonAPI {
    @Autowired
    private ActualSonSer actualSonSer;

    @Override
    public List<ActualSonBO> list(ActualSonDTO dto) throws SerException {
        return actualSonSer.list(dto);
    }

    @Override
    public ActualSonBO save(ActualSonTO to) throws SerException {
        return actualSonSer.save(to);
    }

    @Override
    public void edit(ActualSonTO to) throws SerException {
        actualSonSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        actualSonSer.delete(id);
    }

    @Override
    public ActualSonBO findByID(String id) throws SerException {
        return actualSonSer.findByID(id);
    }

    @Override
    public Long count(ActualSonDTO dto) throws SerException {
        return actualSonSer.count(dto);
    }
}