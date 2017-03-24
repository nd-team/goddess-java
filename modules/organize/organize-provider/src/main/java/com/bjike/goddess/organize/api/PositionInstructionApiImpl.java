package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionInstructionBO;
import com.bjike.goddess.organize.dto.PositionInstructionDTO;
import com.bjike.goddess.organize.service.PositionInstructionSer;
import com.bjike.goddess.organize.to.PositionInstructionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位说明书业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:38]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("positionInstructionApiImpl")
public class PositionInstructionApiImpl implements PositionInstructionAPI {
    
    @Autowired
    private PositionInstructionSer positionInstructionSer;

    @Override
    public List<PositionInstructionBO> findByPosition(String id) throws SerException {
        return positionInstructionSer.findByPosition(id);
    }

    @Override
    public List<PositionInstructionBO> findPage(PositionInstructionDTO dto) throws SerException {
        return positionInstructionSer.findPage(dto);
    }

    @Override
    public PositionInstructionBO save(PositionInstructionTO to) throws SerException {
        return positionInstructionSer.save(to);
    }

    @Override
    public PositionInstructionBO update(PositionInstructionTO to) throws SerException {
        return positionInstructionSer.update(to);
    }
}
