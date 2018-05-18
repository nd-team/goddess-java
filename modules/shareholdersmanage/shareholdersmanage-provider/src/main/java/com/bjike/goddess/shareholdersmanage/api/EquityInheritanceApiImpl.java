package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityInheritanceBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityInheritanceDTO;
import com.bjike.goddess.shareholdersmanage.service.EquityInheritanceSer;
import com.bjike.goddess.shareholdersmanage.to.EquityInheritanceTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股权继承业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 股权继承业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("equityInheritanceApiImpl")
public class EquityInheritanceApiImpl implements EquityInheritanceAPI {
    @Autowired
    private EquityInheritanceSer equityInheritanceSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return equityInheritanceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return equityInheritanceSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countInheritance(EquityInheritanceDTO equityInheritanceDTO) throws SerException {
        return equityInheritanceSer.countInheritance(equityInheritanceDTO);
    }

    @Override
    public EquityInheritanceBO getOne(String id) throws SerException {
        return equityInheritanceSer.getOne(id);
    }

    @Override
    public List<EquityInheritanceBO> findList(EquityInheritanceDTO equityInheritanceDTO) throws SerException {
        return equityInheritanceSer.findList(equityInheritanceDTO);
    }

    @Override
    public EquityInheritanceBO save(EquityInheritanceTO equityInheritanceTO) throws SerException {
        return equityInheritanceSer.save(equityInheritanceTO);
    }

    @Override
    public EquityInheritanceBO edit(EquityInheritanceTO equityInheritanceTO) throws SerException {
        return equityInheritanceSer.edit(equityInheritanceTO);
    }

    @Override
    public void delete(String id) throws SerException {
        equityInheritanceSer.delete(id);
    }
}