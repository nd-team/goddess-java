package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.DetailsBO;
import com.bjike.goddess.staffshares.dto.DetailsDTO;
import com.bjike.goddess.staffshares.service.DetailsSer;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.staffshares.to.SellscheduleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交易详情业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 08:54 ]
 * @Description: [ 交易详情业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("detailsApiImpl")
public class DetailsApiImpl implements DetailsAPI {
    @Autowired
    private DetailsSer detailsSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return detailsSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return detailsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<DetailsBO> listDetail(DetailsDTO dto) throws SerException {
        return detailsSer.listDetail(dto);
    }

    @Override
    public DetailsBO getDetailById(String id) throws SerException {
        return detailsSer.getDetailById(id);
    }

    @Override
    public Long getTotal(DetailsDTO detailsDTO) throws SerException {
        return detailsSer.getTotal(detailsDTO);
    }

    @Override
    public void buy(PurchaseTO to) throws SerException {
        detailsSer.buy(to);
    }

    @Override
    public void recovery(String id) throws SerException {
        detailsSer.recovery(id);
    }

    @Override
    public void sell(SellscheduleTO to) throws SerException {
        detailsSer.sell(to);
    }
}