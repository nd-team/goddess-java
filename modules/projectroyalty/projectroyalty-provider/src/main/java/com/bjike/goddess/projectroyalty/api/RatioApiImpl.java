package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.RatioBO;
import com.bjike.goddess.projectroyalty.dto.RatioDTO;
import com.bjike.goddess.projectroyalty.service.RatioSer;
import com.bjike.goddess.projectroyalty.to.RatioTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 毛利率业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:48 ]
 * @Description: [ 毛利率业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("ratioApiImpl")
public class RatioApiImpl implements RatioAPI {

    @Autowired
    private RatioSer ratioSer;

    @Override
    public RatioBO save(RatioTO to) throws SerException {
        return ratioSer.save(to);
    }

    @Override
    public RatioBO update(RatioTO to) throws SerException {
        return ratioSer.update(to);
    }

    @Override
    public RatioBO delete(String id) throws SerException {
        return ratioSer.delete(id);
    }

    @Override
    public RatioBO getById(String id) throws SerException {
        return ratioSer.getById(id);
    }

    @Override
    public List<RatioBO> maps(RatioDTO dto) throws SerException {
        return ratioSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return ratioSer.getTotal();
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        return ratioSer.findOpinion();
    }
}