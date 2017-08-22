package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.dispatchcar.bo.LeaseCarCostBO;
import com.bjike.goddess.dispatchcar.dto.LeaseCarCostDTO;
import com.bjike.goddess.dispatchcar.entity.LeaseCarCost;
import com.bjike.goddess.dispatchcar.to.LeaseCarCostTO;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;

import java.util.List;

/**
 * 租车费用基本信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LeaseCarCostSer extends Ser<LeaseCarCost, LeaseCarCostDTO> {

    /**
     * 新增租车费用基本信息
     *
     * @param to 租车费用基本信息
     * @return 租车费用基本信息
     */
    LeaseCarCostBO insertModel(LeaseCarCostTO to) throws SerException;

    /**
     * 编辑租车费用基本信息
     *
     * @param to 租车费用基本信息
     * @return 租车费用基本信息
     */
    LeaseCarCostBO updateModel(LeaseCarCostTO to) throws SerException;

    /**
     * 分页查询租车费用基本信息
     *
     * @param dto 分页条件
     * @return 租车费用基本信息结果集
     */
    List<LeaseCarCostBO> pageList(LeaseCarCostDTO dto) throws SerException;

    /**
     * 查出所有未冻结的部门
     */
    List<OpinionBO> findDeapartment() throws SerException;

    /**
     * 查询所有地区
     */
    List<AreaBO> findArea() throws SerException;

}