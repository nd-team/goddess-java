package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 10:16]
 * @Description: [租房信息接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RentalAPI {
    /**
     * 添加租房信息
     * @param rentalTO 租房信息数据集合
     * @throws SerException
     */
    default RentalBO insertRental(RentalTO rentalTO) throws SerException {
        return null;
    }
    /**
     * 编辑租房信息
     *
     * @param rentalTO   租房信息数据to
     * @return class rentalBO
     * @throws SerException
     */
    default RentalBO editRental(RentalTO rentalTO ) throws SerException {
        return null;
    }


    /**
     * 根据id删除租房信息
     *
     * @param id
     * @throws SerException
     */
    default void removeRental(String id) throws SerException {
        return;
    }
    /**
     * 获取所有租房信息
     * @param rentalDTO 租房信息dto
     * @return class rental
     * @throws SerException
     */
    default List<RentalBO> findListRental(RentalDTO rentalDTO) throws SerException {
        return null;
    }
    /**
     * 上传附件
     */
    default void uploadAttachments() throws SerException {
        return ;
    }
    /**
     * 附件
     */
    default void attachments() throws SerException {
        return ;
    }
    /**
     * 上传
     */
    default void upload() throws SerException {
        return ;
    }
    /**
     *租房状态
     */

    default RentalBO rentalStatus(RentalTO rentalTO)throws SerException {
        return null;
    }

    /**
     *租房信息导出明细
     */

    default String exportExcel(String area)throws SerException {
        return null;
    }

}
