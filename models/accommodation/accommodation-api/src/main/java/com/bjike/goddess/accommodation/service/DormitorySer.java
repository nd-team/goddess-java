package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.DormitoryBO;
import com.bjike.goddess.accommodation.dto.DormitoryDTO;
import com.bjike.goddess.accommodation.entity.Dormitory;
import com.bjike.goddess.accommodation.to.DormitoryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [宿舍信息接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DormitorySer extends Ser<Dormitory,DormitoryDTO>{
    /**
     * 添加宿舍信息
     * @param dormitoryTO 宿舍信息数据集合
     * @throws SerException
     */
    default DormitoryBO insertDormitory(DormitoryTO dormitoryTO) throws SerException {
        return null;
    }

    /**
     * 编辑宿舍信息
     *
     * @param dormitoryTO  宿舍信息数据to
     * @return class rentalPreceptBO
     * @throws SerException
     */
    default DormitoryBO editDormitory(DormitoryTO dormitoryTO ) throws SerException {
        return null;
    }


    /**
     * 根据id删除宿舍信息
     *
     * @param id
     * @throws SerException
     */
    default void removeDormitory(String id) throws SerException {
        return;
    }
    /**
     * 获取所有宿舍信息
     * @param dormitoryDTO 宿舍信息dto
     * @return class dormitory
     * @throws SerException
     */
    default List<Dormitory> listDormitory(DormitoryDTO dormitoryDTO) throws SerException {
        return null;
    }

    /**
     * 导入数据
     */
    default void importFile() throws SerException {
    }
    /**
     * 导出宿舍信息明细
     */
    default String exportExcel(String area)throws SerException {
        return null;
    }
}
