package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.DormitoryBO;
import com.bjike.goddess.accommodation.dto.DormitoryDTO;
import com.bjike.goddess.accommodation.entity.Dormitory;
import com.bjike.goddess.accommodation.to.DormitoryTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;
import java.util.Set;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 10:16]
 * @Description: [宿舍信息接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DormitoryAPI {
    /**
     * 添加租房信息
     * @param dormitoryTO 租房信息数据集合
     * @throws SerException
     */
    default DormitoryBO insertDormitory(DormitoryTO dormitoryTO) throws SerException {
        return null;
    }
    /**
     * 编辑租房信息
     *
     * @param dormitoryTO   租房信息数据to
     * @return class dormitoryBO
     * @throws SerException
     */
    default DormitoryBO editDormitory(DormitoryTO dormitoryTO ) throws SerException {
        return null;
    }



    /**
     * 根据id删除租房信息
     *
     * @param id
     * @throws SerException
     */
    default void removeDormitory(String id) throws SerException {
        return;
    }
    /**
     * 获取所有租房信息
     * @param dormitoryDTO 租房信息dto
     * @return class rental
     * @throws SerException
     */
    default List<DormitoryBO> findListDormitory(DormitoryDTO dormitoryDTO) throws SerException {
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

    /**
     * chenjunhao
     * 获取所有宿舍地址
     *
     * @return
     * @throws SerException
     */
    Set<String> allDormitoryAddress() throws SerException;

    /**
     * chenjunhao
     * 根据住宿地址获取负责人联系方式
     *
     * @param dormitoryAddress
     * @return
     * @throws SerException
     */
    String findContact(String dormitoryAddress) throws SerException;
}
