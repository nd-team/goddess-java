package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.ReadyBO;
import com.bjike.goddess.negotiatemeeting.dto.ReadyDTO;
import com.bjike.goddess.negotiatemeeting.service.ReadySer;
import com.bjike.goddess.negotiatemeeting.to.ReadyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 协商前准备信息业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:39 ]
 * @Description: [ 协商前准备信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("readyApiImpl")
public class ReadyApiImpl implements ReadyAPI {
    @Autowired
    private ReadySer readySer;

    @Override
    public ReadyBO save(ReadyTO to) throws SerException {
        return readySer.save(to);
    }

    @Override
    public void edit(ReadyTO to) throws SerException {
        readySer.edit(to);
    }

    @Override
    public List<ReadyBO> currentList() throws SerException {
        return readySer.currentList();
    }

    @Override
    public List<ReadyBO> list(ReadyDTO dto) throws SerException {
        return readySer.list(dto);
    }

    @Override
    public Long count(ReadyDTO dto) throws SerException {
        return readySer.count(dto);
    }

    @Override
    public ReadyBO findByID(String id) throws SerException {
        return readySer.findByID(id);
    }
}