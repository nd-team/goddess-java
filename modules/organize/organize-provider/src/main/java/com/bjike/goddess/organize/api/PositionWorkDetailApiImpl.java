package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionWorkDetailBO;
import com.bjike.goddess.organize.dto.PositionWorkDetailDTO;
import com.bjike.goddess.organize.service.PositionWorkDetailSer;
import com.bjike.goddess.organize.to.PositionWorkDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位工作详细业务实现
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("positionWorkDetailApiImpl")
public class PositionWorkDetailApiImpl implements PositionWorkDetailAPI {

    @Autowired
    private PositionWorkDetailSer positionWorkDetailSer;

    @Override
    public List<PositionWorkDetailBO> findByInstruction(String id) throws SerException {
        return positionWorkDetailSer.findByInstruction(id);
    }

    @Override
    public PositionWorkDetailBO save(PositionWorkDetailTO to) throws SerException {
        return positionWorkDetailSer.save(to);
    }

    @Override
    public PositionWorkDetailBO update(PositionWorkDetailTO to) throws SerException {
        return positionWorkDetailSer.update(to);
    }

    @Override
    public PositionWorkDetailBO delete(String id) throws SerException {
        return positionWorkDetailSer.delete(id);
    }

    @Override
    public List<PositionWorkDetailBO> maps(PositionWorkDetailDTO dto) throws SerException {
        return positionWorkDetailSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        PositionWorkDetailDTO dto = new PositionWorkDetailDTO();
        return positionWorkDetailSer.count(dto);
    }

    @Override
    public PositionWorkDetailBO findById(String id) throws SerException {
        return positionWorkDetailSer.getById(id);
    }
}
