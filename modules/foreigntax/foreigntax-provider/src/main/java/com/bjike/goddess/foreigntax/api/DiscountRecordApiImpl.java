package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.DiscountRecordBO;
import com.bjike.goddess.foreigntax.dto.DiscountRecordDTO;
import com.bjike.goddess.foreigntax.service.DiscountRecordSer;
import com.bjike.goddess.foreigntax.to.DiscountRecordTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠备案业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("discountRecordApiImpl")
public class DiscountRecordApiImpl implements DiscountRecordAPI {
    @Autowired
    private DiscountRecordSer discountRecordSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return discountRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return discountRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(DiscountRecordDTO dto) throws SerException {
        return discountRecordSer.count(dto);
    }

    @Override
    public DiscountRecordBO getOne(String id) throws SerException {
        return discountRecordSer.getOne(id);
    }

    @Override
    public List<DiscountRecordBO> list(DiscountRecordDTO dto) throws SerException {
        return discountRecordSer.list(dto);
    }

    @Override
    public DiscountRecordBO insert(DiscountRecordTO to) throws SerException {
        return discountRecordSer.insert(to);
    }

    @Override
    public DiscountRecordBO edit(DiscountRecordTO to) throws SerException {
        return discountRecordSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        discountRecordSer.remove(id);
    }

}