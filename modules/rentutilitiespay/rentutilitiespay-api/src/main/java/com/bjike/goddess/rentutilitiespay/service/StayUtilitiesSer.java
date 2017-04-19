package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.to.StayUtilitiesTO;

import java.util.List;

/**
 * 员工住宿水电费业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StayUtilitiesSer extends Ser<StayUtilities, StayUtilitiesDTO> {
    /**
     * 获取员工住宿水电费
     *
     * @param stayUtilitiesDTO 员工住宿水电费dto
     * @return class StayUtilitiesBO
     * @throws SerException
     */
    default List<StayUtilitiesBO> findListStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工住宿水电费
     *
     * @param stayUtilitiesTO 员工住宿水电费数据to
     * @return class StayUtilitiesBO
     * @throws SerException
     */
    default StayUtilitiesBO insertStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        return null;
    }

    /**
     * 编辑员工住宿水电费
     *
     * @param stayUtilitiesTO 员工住宿水电费数据to
     * @return class StayUtilitiesBO
     * @throws SerException
     */
    default StayUtilitiesBO editStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除员工住宿水电费
     *
     * @param id
     * @throws SerException
     */
    default void removeStayUtilities(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param name name
     * @return class StayUtilitiesBO
     * @throws SerException
     */
    default List<StayUtilitiesBO> collectName(String[] name) throws SerException {
        return null;
    }

    /**
     * 获取名字
     *
     * @return class String
     */
    default List<String> getStayUtilitiesName() throws SerException {
        return null;
    }
    /**
     * 获取编号
     *
     * @return class String
     */
    default List<String> getStayUtilitiesNum() throws SerException {
        return null;
    }
    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getStayUtilitiesArea() throws SerException {
        return null;
    }
    /**
     * 获取项目组
     *
     * @return class String
     */
    default List<String> getStayUtilitiesProGroup() throws SerException {
        return null;
    }
    /**
     * 获取项目名称
     *
     * @return class String
     */
    default List<String> getStayUtilitiesProName() throws SerException {
        return null;
    }
    /**
     * 获取地址
     *
     * @return class String
     */
    default List<String> getStayUtilitiesAddress() throws SerException {
        return null;
    }

}