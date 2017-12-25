package com.bjike.goddess.communicatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.communicatemeeting.bo.CommunicateBO;
import com.bjike.goddess.communicatemeeting.dto.CommunicateDTO;
import com.bjike.goddess.communicatemeeting.service.CommunicateSer;
import com.bjike.goddess.communicatemeeting.to.CommunicateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交流讨论业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-27 02:00 ]
 * @Description: [ 交流讨论业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("communicateApiImpl")
public class CommunicateApiImpl implements CommunicateAPI {
    @Autowired
    private CommunicateSer communicateSer;

    @Override
    public CommunicateBO oneRound(CommunicateTO to) throws SerException {
        return communicateSer.oneRound(to);
    }

    @Override
    public List<CommunicateBO> twoRound(CommunicateDTO dto) throws SerException {
        return communicateSer.twoRound(dto);
    }

    @Override
    public void replenish(CommunicateTO to) throws SerException {
        communicateSer.replenish(to);
    }

    @Override
    public void vote(String id) throws SerException {
        communicateSer.vote(id);
    }

    @Override
    public CommunicateBO findByID(String id) throws SerException {
        return communicateSer.findByID(id);
    }

    @Override
    public List<CommunicateBO> showVote(CommunicateDTO dto) throws SerException {
        return communicateSer.showVote(dto);
    }

    @Override
    public Long countNum(CommunicateDTO dto) throws SerException {
        return communicateSer.countNum(dto);
    }
}