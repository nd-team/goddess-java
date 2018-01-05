package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.IncomeDistributionBO;
import com.bjike.goddess.moneyside.dto.IncomeDistributionDTO;
import com.bjike.goddess.moneyside.entity.IncomeDistribution;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.IncomeDistributionTO;

import java.util.List;

/**
 * 收益比例分配业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IncomeDistributionSer extends Ser<IncomeDistribution, IncomeDistributionDTO> {
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
     * 收益比例分配列表总条数
     */
    default Long countIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        return null;
    }

    /**
     * 一个收益比例分配
     *
     * @return class IncomeDistributionBO
     */
    default IncomeDistributionBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 收益比例分配
     *
     * @param incomeDistributionDTO 收益比例分配dto
     * @return class IncomeDistributionBO
     * @throws SerException
     */
    default List<IncomeDistributionBO> findListIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        return null;
    }

    /**
     * 添加收益比例分配
     *
     * @param incomeDistributionTO 收益比例分配数据to
     * @return class IncomeDistributionBO
     * @throws SerException
     */
    default IncomeDistributionBO insertIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        return null;
    }

    /**
     * 编辑收益比例分配
     *
     * @param incomeDistributionTO 收益比例分配数据to
     * @return class IncomeDistributionBO
     * @throws SerException
     */
    default IncomeDistributionBO editIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除收益比例分配
     *
     * @param id
     * @throws SerException
     */
    default void removeIncomeDistribution(String id) throws SerException {

    }
}