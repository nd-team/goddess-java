package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.entity.RentalApply;
import com.bjike.goddess.accommodation.service.RentalApplySer;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 10:16]
 * @Description: [租房申请接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("rentalApplyApiImpl")
public class RentalApplyApiImpl implements RentalApplyAPI{
    @Autowired
    private RentalApplySer rentalApplySer;
    @Override
    public Long count(RentalApplyDTO rentalApplyDTO) throws SerException {
        return rentalApplySer.count(rentalApplyDTO);
    }

    @Override
    public RentalApplyBO getOne(String id) throws SerException {
        return rentalApplySer.getOne(id);
    }
    @Override
    public List<RentalApplyBO> findListRentalApply(RentalApplyDTO rentalApplyDTO) throws SerException {
        return rentalApplySer.findListRentalApply(rentalApplyDTO);
    }
    @Override
    public RentalApplyBO insertApply(RentalApplyTO applyTO) throws SerException {
        return rentalApplySer.insertApply(applyTO);
    }
    @Override
    public RentalApplyBO editApply(RentalApplyTO applyTO) throws SerException {
        return rentalApplySer.editApply(applyTO);
    }

    @Override
    public void removeApply(String id) throws SerException {
        rentalApplySer.removeApply(id);
    }

    @Override
    public RentalApplyBO manageAudit(RentalApplyTO applyTO) throws SerException {
        return rentalApplySer.manageAudit(applyTO);
    }
    @Override
    public void summary() throws SerException {
        rentalApplySer.summary();
    }

    @Override
    public byte[] exportExcel(RentalApplyDTO dto) throws SerException{
        return rentalApplySer.exportExcel(dto);
    }
    /**
     *自动生成记账凭证
     */
    @Override
    public String generateCredentials ()throws SerException{
        return rentalApplySer.generateCredentials();
    }
}
