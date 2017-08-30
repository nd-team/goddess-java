package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.assistance.dto.HouseAssistDTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.HouseAssistTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface HouseAssistAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 住宿补助列表总条数
     *
     */
    default Long countHouseAssist(HouseAssistDTO houseAssistDTO) throws SerException {
        return null;
    }
    /**
     * 一个住宿补助
     * @return class HouseAssistBO
     */
    default HouseAssistBO getOneById(String id) throws SerException {return null;}

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


    /**
     * 根据计薪周期开始时间和结束时间获取住宿补助信息
     */
    HouseAssistBO findHouse(String startTime,String endTime) throws SerException;

}