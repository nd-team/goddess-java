package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.service.RentalSer;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public RentalBO insertRental(RentalTO rentalTO) throws SerException {
        return rentalSer.insertRental(rentalTO);
    }
    @Override
    public RentalBO editRental(RentalTO rentalTO) throws SerException {
        return rentalSer.editRental(rentalTO);
    }

    @Override
    public void removeRental(String id) throws SerException {
        rentalSer.removeRental(id);
    }

    @Override
    public List<RentalBO> listRental(RentalDTO rentalDTO) throws SerException {
        List<Rental> rentals = rentalSer.listRental(rentalDTO);
        return BeanTransform.copyProperties(rentals, RentalBO.class, true);
    }
    /**
     * 上传附件
     */
    @Override
    public void uploadAttachments() throws SerException {
        rentalSer.uploadAttachments();
    }
    /**
     * 附件
     */
    @Override
    public void attachments() throws SerException {
        rentalSer.attachments();
    }
    /**
     * 上传
     */
    @Override
    public void upload() throws SerException {
        rentalSer.upload();
    }
    /**
     *租房状态
     */
    @Override
    public RentalBO rentalStatus(RentalTO rentalTO)throws SerException {
        return rentalSer.rentalStatus(rentalTO);
    }

    /**
     *租房信息导出明细
     */
    @Override
    public String exportExcel(String area)throws SerException {
        return rentalSer.exportExcel(area);
    }

}
