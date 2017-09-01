package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.shareholdersmanage.bo.EquityInheritanceBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityInheritanceDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityInheritance;
import com.bjike.goddess.shareholdersmanage.to.EquityInheritanceTO;

import java.util.List;

/**
 * 股权继承业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 股权继承业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityInheritanceSer extends Ser<EquityInheritance, EquityInheritanceDTO> {
    /**
     * 股权继承列表总条数
     */
    default Long countInheritance(EquityInheritanceDTO equityInheritanceDTO) throws SerException {
        return null;
    }

    /**
     * 一个股权继承
     *
     * @return class EquityInheritanceBO
     */
    default EquityInheritanceBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股权继承列表
     *
     * @param equityInheritanceDTO 股权转让dto
     * @return class EquityInheritanceBO
     * @throws SerException
     */
    default List<EquityInheritanceBO> findList(EquityInheritanceDTO equityInheritanceDTO) throws SerException {
        return null;
    }

    /**
     * 股权继承添加
     *
     * @param equityInheritanceTO 股权转让数据to
     * @throws SerException
     */
    default EquityInheritanceBO save(EquityInheritanceTO equityInheritanceTO) throws SerException {
        return null;
    }

    /**
     * 股权继承编辑
     *
     * @param equityInheritanceTO 股权继承数据to
     * @throws SerException
     */
    default EquityInheritanceBO edit(EquityInheritanceTO equityInheritanceTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除股权继承
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
}