package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.PurchaseBO;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.service.PurchaseSer;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 干股申购表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("purchaseApiImpl")
public class PurchaseApiImpl implements PurchaseAPI {
    @Autowired
    private PurchaseSer purchaseSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return purchaseSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return purchaseSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void buy(PurchaseTO to) throws SerException {
        purchaseSer.buy(to);
    }

    @Override
    public void update(PurchaseTO to) throws SerException {
        purchaseSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        purchaseSer.delete(id);
    }

    @Override
    public void examine(PurchaseTO to) throws SerException {
        purchaseSer.examine(to);
    }

    @Override
    public List<PurchaseBO> list(PurchaseDTO dto) throws SerException {
        return purchaseSer.list(dto);
    }

    @Override
    public PurchaseBO getById(String id) throws SerException {
        return purchaseSer.getById(id);
    }

    @Override
    public Long getTotal(PurchaseDTO dto) throws SerException {
        return purchaseSer.getTotal(dto);
    }
}