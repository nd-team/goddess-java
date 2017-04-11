package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.entity.RentalApply;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房申请接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RentalApplySer extends Ser<RentalApply,RentalApplyDTO> {
    /**
     * 添加租房申请
     * @param applyTO 租房申请数据集合
     * @throws SerException
     */
    default RentalApplyBO insertApply(RentalApplyTO applyTO) throws SerException {
        return null;
    }

    /**
     * 编辑租房申请
     *
     * @param applyTO   租房申请信息数据to
     * @return class rentalPreceptBO
     * @throws SerException
     */
    default RentalApplyBO editApply(RentalApplyTO applyTO ) throws SerException {
        return null;
    }


    /**
     * 根据id删除租房申请
     *
     * @param id
     * @throws SerException
     */
    default void removeApply(String id) throws SerException {
        return;
    }
    /**
     * 获取所有租房申请
     * @param rentalApplyDTO 租房申请dto
     * @return class rentalApply
     * @throws SerException
     */
    default List<RentalApplyBO> findListRentalApply(RentalApplyDTO rentalApplyDTO) throws SerException {
        return null;
    }
    /**
     * 审核
     * @param applyTO
     * @throws SerException
     */
    default void audit(RentalApplyTO applyTO) throws SerException{
        return ;
    }

    /**
     *租房申请导出明细
     */
    default String exportExcel (String startTime, String endTime)throws SerException{
        return null;
    }
    /**
     *自动生成记账凭证
     */
    default String generateCredentials ()throws SerException{
        return null;
    }
    /**
     *租房申请汇总到租房信息中
     */
    default RentalApplyBO summary (RentalApplyTO rentalApplyTO)throws SerException{
        return null;
    }
}
