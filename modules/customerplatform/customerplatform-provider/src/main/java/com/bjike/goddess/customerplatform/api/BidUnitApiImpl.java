package com.bjike.goddess.customerplatform.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customerplatform.bo.BidUnitBO;
import com.bjike.goddess.customerplatform.dto.BidUnitDTO;
import com.bjike.goddess.customerplatform.entity.SonPermissionObject;
import com.bjike.goddess.customerplatform.service.BidUnitSer;
import com.bjike.goddess.customerplatform.to.BidUnitTO;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 中标单位业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:20 ]
 * @Description: [ 中标单位业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bidUnitApiImpl")
public class BidUnitApiImpl implements BidUnitAPI {
    @Autowired
    private BidUnitSer bidUnitSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return bidUnitSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return bidUnitSer.guidePermission( guidePermissionTO );
    }

    @Override
    public void save(BidUnitTO to) throws SerException {
        bidUnitSer.save(to);
    }

    @Override
    public void update(BidUnitTO to) throws SerException {
        bidUnitSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        bidUnitSer.delete(id);
    }

    @Override
    public List<BidUnitBO> maps(BidUnitDTO dto) throws SerException {
        return bidUnitSer.maps(dto);
    }

    @Override
    public BidUnitBO getById(String id) throws SerException {
        return bidUnitSer.getById(id);
    }

    @Override
    public Long getTotal(BidUnitDTO dto) throws SerException {
        return bidUnitSer.getTotal(dto);
    }
}