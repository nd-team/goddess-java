package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalPreceptDTO;
import com.bjike.goddess.accommodation.entity.RentalPrecept;
import com.bjike.goddess.accommodation.enums.PassStatus;
import com.bjike.goddess.accommodation.excel.SonPermissionObject;
import com.bjike.goddess.accommodation.service.RentalPreceptSer;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 10:16]
 * @Description: [租房方案接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("rentalPreceptApiImpl")
public class RentalPreceptApiImpl implements RentalPreceptAPI{
    @Autowired
    private RentalPreceptSer rentalPreceptSer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return rentalPreceptSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return rentalPreceptSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        return rentalPreceptSer.countRentalPrecept(rentalPreceptDTO);
    }

    @Override
    public RentalPreceptBO getOne(String id) throws SerException {
        return rentalPreceptSer.getOne(id);
    }
    @Override
    public List<RentalPreceptBO> findListRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        return rentalPreceptSer.findListRentalPrecept(rentalPreceptDTO);
    }
    @Override
    public RentalPreceptBO insertPecept(RentalPreceptTO preceptTO) throws SerException {
        return rentalPreceptSer.insertPecept(preceptTO);
    }
    @Override
    public RentalPreceptBO editPecept(RentalPreceptTO rentalPreceptTO) throws SerException {
        return rentalPreceptSer.editPecept(rentalPreceptTO);
    }

    @Override
    public void removePecept(String id) throws SerException {
        rentalPreceptSer.removePecept(id);
    }
    @Override
    public RentalPreceptBO businessAudit(RentalPreceptTO preceptTO) throws SerException {
        return rentalPreceptSer.businessAudit(preceptTO);
    }
    @Override
    public RentalPreceptBO financeAudit(RentalPreceptTO preceptTO) throws SerException {
        return rentalPreceptSer.financeAudit(preceptTO);
    }
    @Override
    public RentalPreceptBO resourceAudit(RentalPreceptTO preceptTO) throws SerException {
        return rentalPreceptSer.resourceAudit(preceptTO);
    }

    @Override
    public RentalPreceptBO manageAudit(RentalPreceptTO preceptTO) throws SerException {
        return rentalPreceptSer.manageAudit(preceptTO);
    }

    @Override
    public RentalPreceptBO generalAudit(RentalPreceptTO preceptTO) throws SerException {
        return rentalPreceptSer.generalAudit(preceptTO);
    }
    @Override
    public List<String> getNum() throws SerException {
        return rentalPreceptSer.getNum();
    }

    @Override
    public RentalPreceptBO getRent(String rentNum) throws SerException {
        return rentalPreceptSer.getRent(rentNum);
    }
    @Override
    public List<UserBO> getUser() throws SerException {
        return rentalPreceptSer.getUser();
    }
}
