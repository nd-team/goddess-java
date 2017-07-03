package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractware.bo.HousingContractBO;
import com.bjike.goddess.contractware.dto.HousingContractDTO;
import com.bjike.goddess.contractware.entity.HousingContract;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.to.HousingContractTO;

import java.util.List;

/**
 * 房屋合同业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:42 ]
 * @Description: [ 房屋合同业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HousingContractSer extends Ser<HousingContract, HousingContractDTO> {
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
     * 房屋合同列表总条数
     */
    default Long countHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        return null;
    }

    /**
     * 一个房屋合同
     *
     * @return class HousingContractBO
     */
    default HousingContractBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 房屋合同
     *
     * @param housingContractDTO 房屋合同dto
     * @return class HousingContractBO
     * @throws SerException
     */
    default List<HousingContractBO> findListHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        return null;
    }

    /**
     * 添加房屋合同
     *
     * @param housingContractTO 房屋合同数据to
     * @return class HousingContractBO
     * @throws SerException
     */
    default HousingContractBO insertHousingContract(HousingContractTO housingContractTO) throws SerException {
        return null;
    }

    /**
     * 编辑房屋合同
     *
     * @param housingContractTO 房屋合同数据to
     * @return class HousingContractBO
     * @throws SerException
     */
    default HousingContractBO editHousingContract(HousingContractTO housingContractTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除房屋合同
     *
     * @param id
     * @throws SerException
     */
    default void removeHousingContract(String id) throws SerException {

    }

}