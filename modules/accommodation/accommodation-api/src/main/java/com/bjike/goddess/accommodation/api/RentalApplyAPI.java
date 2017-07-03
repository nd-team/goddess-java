package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 10:16]
 * @Description: [租房申请接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RentalApplyAPI {
    /**
     * 租房方案列表总条数
     */
    default Long count(RentalApplyDTO rentalApplyDTO) throws SerException {
        return null;
    }

    /**
     * 一个租房方案
     *
     * @return class RentalApplyBO
     */
    default RentalApplyBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 获取所有租房申请
     *
     * @param rentalApplyDTO 租房申请dto
     * @return class RentalApplyBO
     * @throws SerException
     */
    default List<RentalApplyBO> findListRentalApply(RentalApplyDTO rentalApplyDTO) throws SerException {
        return null;
    }

    /**
     * 添加租房申请
     *
     * @param applyTO 租房申请数据集合
     * @return class RentalApplyBO
     * @throws SerException
     */
    default RentalApplyBO insertApply(RentalApplyTO applyTO) throws SerException {
        return null;
    }

    /**
     * 编辑租房申请
     *
     * @param applyTO 租房申请信息数据to
     * @return class RentalApplyBO
     * @throws SerException
     */
    default RentalApplyBO editApply(RentalApplyTO applyTO) throws SerException {
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
     * 项目经理审核
     *
     * @param applyTO
     * @return class RentalApplyBO
     * @throws SerException
     */
    default RentalApplyBO manageAudit(RentalApplyTO applyTO) throws SerException {
        return null;
    }
    /**
     * 租房申请汇总到租房信息中
     */
    default void summary() throws SerException {
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(RentalApplyDTO dto) throws SerException;

    /**
     * 自动生成记账凭证
     */
    default String generateCredentials() throws SerException {
        return null;
    }


}
