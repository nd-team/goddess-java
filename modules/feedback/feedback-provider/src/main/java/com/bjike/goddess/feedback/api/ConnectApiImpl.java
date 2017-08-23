package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ConnectBO;
import com.bjike.goddess.feedback.dto.ConnectDTO;
import com.bjike.goddess.feedback.service.ConnectSer;
import com.bjike.goddess.feedback.to.ConnectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 各类沟通模板业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("connectApiImpl")
public class ConnectApiImpl implements ConnectAPI {

    @Autowired
    private ConnectSer connectSer;
    @Override
    public Long count(ConnectDTO dto) throws SerException {
        return connectSer.count(dto);
    }
    @Override
    public ConnectBO getOne(String id) throws SerException {
        return connectSer.getOne(id);
    }
    @Override
    public List<ConnectBO> list(ConnectDTO dto) throws SerException {
        return connectSer.list(dto);
    }
    @Override
    public ConnectBO edit(ConnectTO to) throws SerException {
        return connectSer.edit(to);
    }

    @Override
    public List<String> getSort() throws SerException {
        return connectSer.getSort();
    }

    @Override
    public List<ConnectBO> getConnect(String sorting) throws SerException {
        return connectSer.getConnect(sorting);
    }
}