package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.FacilityBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.FacilityDTO;
import com.bjike.goddess.projectroyalty.service.FacilitySer;
import com.bjike.goddess.projectroyalty.to.FacilityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 难易度业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:47 ]
 * @Description: [ 难易度业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("facilityApiImpl")
public class FacilityApiImpl implements FacilityAPI {

    @Autowired
    private FacilitySer facilitySer;

    @Override
    public FacilityBO save(FacilityTO to) throws SerException {
        return facilitySer.save(to);
    }

    @Override
    public FacilityBO update(FacilityTO to) throws SerException {
        return facilitySer.update(to);
    }

    @Override
    public FacilityBO delete(String id) throws SerException {
        return facilitySer.delete(id);
    }

    @Override
    public FacilityBO getById(String id) throws SerException {
        return facilitySer.getById(id);
    }

    @Override
    public List<FacilityBO> maps(FacilityDTO dto) throws SerException {
        return facilitySer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return facilitySer.getTotal();
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        return facilitySer.findOpinion();
    }
}