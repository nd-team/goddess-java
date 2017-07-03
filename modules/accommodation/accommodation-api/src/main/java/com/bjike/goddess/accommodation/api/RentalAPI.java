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
     * 租房信息列表总条数
     */
    default Long count(RentalDTO rentalDTO) throws SerException {
        return null;
    }

    /**
     * 一个租房信息
     *
     * @return class RentalBO
     */
    default RentalBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 租房信息列表
     *
     * @param rentalDTO 租房信息dto
     * @return class RentalBO
     * @throws SerException
     */
    default List<RentalBO> findListRental(RentalDTO rentalDTO) throws SerException {
        return null;
    }

    /**
     * 添加租房信息
     *
     * @param rentalTO 租房信息数据集合
     * @return class RentalBO
     * @throws SerException
     */
    default RentalBO insertRental(RentalTO rentalTO) throws SerException {
        return null;
    }

    /**
     * 编辑租房信息
     *
     * @param rentalTO 租房信息数据to
     * @return class RentalBO
     * @throws SerException
     */
    default RentalBO editRental(RentalTO rentalTO) throws SerException {
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
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(RentalDTO dto) throws SerException;

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }


}
