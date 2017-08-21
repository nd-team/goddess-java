package com.bjike.goddess.projectcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcost.bo.ArtificialCostBO;
import com.bjike.goddess.projectcost.dto.ArtificialCostDTO;
import com.bjike.goddess.projectcost.excel.SonPermissionObject;
import com.bjike.goddess.projectcost.service.ArtificialCostSer;
import com.bjike.goddess.projectcost.to.ArtificialCostTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人工费用业务接口实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:19 ]
 * @Description: [ 人工费用业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("artificialCostApiImpl")
public class ArtificialCostApiImpl implements ArtificialCostAPI {

    @Autowired
    private ArtificialCostSer artificialCostSer;

    @Override
    public ArtificialCostBO save(ArtificialCostTO to) throws SerException {
        return artificialCostSer.save(to);
    }

    @Override
    public ArtificialCostBO update(ArtificialCostTO to) throws SerException {
        return artificialCostSer.update(to);
    }

    @Override
    public ArtificialCostBO delete(String id) throws SerException {
        return artificialCostSer.delete(id);
    }

    @Override
    public List<ArtificialCostBO> maps(ArtificialCostDTO dto) throws SerException {
        return artificialCostSer.maps(dto);
    }

    @Override
    public ArtificialCostBO updateActual(ArtificialCostTO to) throws SerException {
        return artificialCostSer.updateActual(to);
    }

    @Override
    public ArtificialCostBO getById(String id) throws SerException {
        return artificialCostSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return artificialCostSer.getTotal();
    }

    @Override
    public List<ArtificialCostBO> findByTO(FindTO to) throws SerException {
        return artificialCostSer.findByTO(to);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return artificialCostSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return artificialCostSer.guidePermission( guidePermissionTO );
    }

    @Override
    public List<ArtificialCostBO> find(Integer year, Integer month, String project) throws SerException {
        return artificialCostSer.find(year, month, project);
    }
}