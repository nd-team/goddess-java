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
    public List<RentalApplyBO> listRentalApply(RentalApplyDTO rentalApplyDTO) throws SerException {
        List<RentalApply> rentalApplies = rentalApplySer.listRentalApply(rentalApplyDTO);
        return BeanTransform.copyProperties(rentalApplies, RentalApplyBO.class, true);
    }
    @Override
    public void audit(RentalApplyTO applyTO) throws SerException{
        rentalApplySer.audit(applyTO);
    }

    /**
     *租房申请导出明细
     */
    @Override
    public String exportExcel (String startTime, String endTime)throws SerException{
        return rentalApplySer.exportExcel(startTime,endTime);
    }
    /**
     *自动生成记账凭证
     */
    @Override
    public String generateCredentials ()throws SerException{
        return rentalApplySer.generateCredentials();
    }
    /**
     *租房申请汇总到租房信息中
     */
    @Override
    public RentalApplyBO summary (RentalApplyTO rentalApplyTO)throws SerException{
        return rentalApplySer.summary(rentalApplyTO);
    }
}
