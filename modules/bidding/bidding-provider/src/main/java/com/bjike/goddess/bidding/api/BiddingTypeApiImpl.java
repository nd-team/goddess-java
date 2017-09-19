package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingTypeBO;
import com.bjike.goddess.bidding.dto.BiddingTypeDTO;
import com.bjike.goddess.bidding.entity.BiddingType;
import com.bjike.goddess.bidding.service.BiddingTypeSer;
import com.bjike.goddess.bidding.to.BiddingTypeTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招投标类型业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:24 ]
 * @Description: [ 招投标类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("biddingTypeApiImpl")
public class BiddingTypeApiImpl implements BiddingTypeAPI {
    @Autowired
    private BiddingTypeSer biddingTypeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return biddingTypeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return biddingTypeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(BiddingTypeDTO dto) throws SerException {
        return biddingTypeSer.count(dto);
    }

    @Override
    public BiddingTypeBO getOne(String id) throws SerException {
        return biddingTypeSer.getOne(id);
    }

    @Override
    public List<BiddingTypeBO> list(BiddingTypeDTO dto) throws SerException {
        return biddingTypeSer.list(dto);
    }

    @Override
    public BiddingTypeBO save(BiddingTypeTO to) throws SerException {
        return biddingTypeSer.save(to);
    }

    @Override
    public BiddingTypeBO edit(BiddingTypeTO to) throws SerException {
        return biddingTypeSer.edit(to);
    }
    @Override
    public void remove(String id) throws SerException {
        biddingTypeSer.remove(id);
    }
    @Override
    public List<String> getType() throws SerException {
        return biddingTypeSer.getType();
    }
}