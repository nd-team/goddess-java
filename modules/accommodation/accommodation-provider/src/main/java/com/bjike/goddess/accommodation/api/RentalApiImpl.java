package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.CollectBO;
import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.service.RentalSer;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 10:16]
 * @Description: [租房信息接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("rentalApiImpl")
public class RentalApiImpl implements RentalAPI{
    @Autowired
    private RentalSer rentalSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return rentalSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return rentalSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long count(RentalDTO rentalDTO) throws SerException {
        return rentalSer.count(rentalDTO);
    }

    @Override
    public RentalBO getOne(String id) throws SerException {
        return rentalSer.getOne(id);
    }
//    @Override
//    public RentalBO insertRental(RentalTO rentalTO) throws SerException {
//        return rentalSer.insertRental(rentalTO);
//    }
    @Override
    public RentalBO editRental(RentalTO rentalTO) throws SerException {
        return rentalSer.editRental(rentalTO);
    }

    @Override
    public void removeRental(String id) throws SerException {
        rentalSer.removeRental(id);
    }

    @Override
    public List<RentalBO> findListRental(RentalDTO rentalDTO) throws SerException {
        return rentalSer.findListRental(rentalDTO);
    }
    @Override
    public byte[] exportExcel(RentalDTO dto) throws SerException {
        return rentalSer.exportExcel(dto);
    }
    @Override
    public List<CollectBO> collect(String [] areas) throws SerException {
        return rentalSer.collect(areas);
    }
    @Override
    public List<String> getArea() throws SerException {
        return rentalSer.getArea();
    }

    @Override
    public Set<String> allAddress() throws SerException {
        return rentalSer.allAddress();
    }
}
