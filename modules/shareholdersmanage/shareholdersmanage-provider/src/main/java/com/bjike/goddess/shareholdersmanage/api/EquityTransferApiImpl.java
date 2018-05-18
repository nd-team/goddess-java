package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransferBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransferDTO;
import com.bjike.goddess.shareholdersmanage.service.EquityTransferSer;
import com.bjike.goddess.shareholdersmanage.to.EquityTransferTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股权转让业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:32 ]
 * @Description: [ 股权转让业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("equityTransferApiImpl")
public class EquityTransferApiImpl implements EquityTransferAPI {
    @Autowired
    private EquityTransferSer equityTransferSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return equityTransferSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return equityTransferSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countEquityfer(EquityTransferDTO equityTransferDTO) throws SerException {
        return equityTransferSer.countEquityfer(equityTransferDTO);
    }

    @Override
    public EquityTransferBO getOne(String id) throws SerException {
        return equityTransferSer.getOne(id);
    }

    @Override
    public List<EquityTransferBO> findList(EquityTransferDTO equityTransferDTO) throws SerException {
        return equityTransferSer.findList(equityTransferDTO);
    }

    @Override
    public EquityTransferBO save(EquityTransferTO equityTransferTO) throws SerException {
        return equityTransferSer.save(equityTransferTO);
    }

    @Override
    public EquityTransferBO edit(EquityTransferTO equityTransferTO) throws SerException {
        return equityTransferSer.edit(equityTransferTO);
    }

    @Override
    public void delete(String id) throws SerException {
        equityTransferSer.delete(id);
    }
}