package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.assistance.to.HouseAssistTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.assistance.entity.HouseAssist;
import com.bjike.goddess.assistance.dto.HouseAssistDTO;

import java.util.List;

/**
 * 住宿补助业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 11:27 ]
 * @Description: [ 住宿补助业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HouseAssistSer extends Ser<HouseAssist, HouseAssistDTO> {

    /**
     * 住宿补助列表总条数
     *
     */
    default Long countHouseAssist(HouseAssistDTO houseAssistDTO) throws SerException {
        return null;
    }
    /**
     * 住宿补助列表
     * @return class HouseAssistBO
     */
    default List<HouseAssistBO> listHouseAssist(HouseAssistDTO houseAssistDTO) throws SerException {return null;}
    /**
     *  添加
     * @param houseAssistTO 住宿补助信息
     * @return class HouseAssistBO
     */
    default HouseAssistBO addHouseAssist(HouseAssistTO houseAssistTO) throws SerException { return null;}

    /**
     *  编辑
     * @param houseAssistTO 住宿补助信息
     * @return class HouseAssistBO
     */
    default HouseAssistBO editHouseAssist(HouseAssistTO houseAssistTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteHouseAssist(String id ) throws SerException {return;};

}