package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.LeavingMessageBO;
import com.bjike.goddess.businessinteraction.dto.LeavingMessageDTO;
import com.bjike.goddess.businessinteraction.service.LeavingMessageSer;
import com.bjike.goddess.businessinteraction.to.LeavingMessageTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:11 ]
 * @Description: [ 留言业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("leavingMessageApiImpl")
public class LeavingMessageApiImpl implements LeavingMessageAPI {

    @Autowired
    private LeavingMessageSer leavingMessageSer;

    @Override
    public Long countInter(LeavingMessageDTO leavingMessageDTO) throws SerException {
        return leavingMessageSer.countInter(leavingMessageDTO);
    }

    @Override
    public List<LeavingMessageBO> listLeavingMessage(String interactionId ) throws SerException {
        return leavingMessageSer.listLeavingMessage(interactionId);
    }

    @Override
    public LeavingMessageBO addLeavingMessage(LeavingMessageTO leavingMessageTO) throws SerException {
        return leavingMessageSer.addLeavingMessage(leavingMessageTO);
    }

    @Override
    public LeavingMessageBO editLeavingMessage(LeavingMessageTO leavingMessageTO) throws SerException {
        return leavingMessageSer.editLeavingMessage(leavingMessageTO);
    }

    @Override
    public void deleteLeavingMessage(String id) throws SerException {
        leavingMessageSer.deleteLeavingMessage(id);
    }
}