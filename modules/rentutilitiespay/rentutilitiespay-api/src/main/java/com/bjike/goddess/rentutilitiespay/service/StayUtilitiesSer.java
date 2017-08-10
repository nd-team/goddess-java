package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentutilitiespay.bo.CollectNameBO;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.to.GuidePermissionTO;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;
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
     * 员工住宿水电费列表总条数
     */
    default Long countStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        return null;
    }

    /**
     * 一个员工住宿水电费
     *
     * @return class StayUtilitiesBO
     */
    default StayUtilitiesBO getOne(String id) throws SerException {
        return null;
    }
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
     * @param names names
     * @return class CollectNameBO
     * @throws SerException
     */
    default List<CollectNameBO> collectName(String[] names) throws SerException {
        return null;
    }

    /**
     * 获取名字
     *
     * @return class String
     */
    default List<String> getName() throws SerException {
        return null;
    }
    /**
     * 员工核实
     *
     * @param to to
     * @return class StayUtilitiesBO
     * @throws SerException
     */
    default StayUtilitiesBO employeeVerify(StayUtilitiesTO to) throws SerException {
        return null;
    }
    /**
     * 运营财务部
     *
     * @param to to
     * @return class StayUtilitiesBO
     * @throws SerException
     */
    default StayUtilitiesBO financeAudit(StayUtilitiesTO to) throws SerException {
        return null;
    }


}