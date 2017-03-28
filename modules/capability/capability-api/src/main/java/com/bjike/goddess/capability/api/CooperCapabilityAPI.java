package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CooperCapabilityBO;
import com.bjike.goddess.capability.dto.CooperCapabilityDTO;
import com.bjike.goddess.capability.to.CooperCapabilityTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 合作对象商务展示业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:31 ]
 * @Description: [ 合作对象商务展示业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CooperCapabilityAPI {

    /**
     * 合作对象商务展示列表
     * @return class CooperCapabilityBO
     */
    default List<CooperCapabilityBO> listCooperCapability(CooperCapabilityDTO cooperCapabilityDTO) throws SerException {return null;}
    /**
     *  添加
     * @param cooperCapabilityTO 合作对象商务展示信息
     * @return class CooperCapabilityBO
     */
    default CooperCapabilityBO addCooperCapability(CooperCapabilityTO cooperCapabilityTO) throws SerException { return null;}

    /**
     *  编辑
     * @param cooperCapabilityTO 合作对象商务展示信息
     * @return class CooperCapabilityBO
     */
    default CooperCapabilityBO editCooperCapability(CooperCapabilityTO cooperCapabilityTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteCooperCapability(String id ) throws SerException {return;};

    /**
     * 查看公司联系人
     * @return class CooperCapabilityBO
     */
    default CooperCapabilityBO getCompanyConnector(String id ) throws SerException {return null;}

    /**
     *  添加或编辑公司联系人
     * @param cooperCapabilityTO 合作对象商务展示信息
     * @return class CooperCapabilityBO
     */
    default CooperCapabilityBO editCompanyConnector(CooperCapabilityTO cooperCapabilityTO) throws SerException { return null;}

    /**
     * 搜索
     * @return class CooperCapabilityBO
     */
    default List<CooperCapabilityBO> listCooperCapabilityByName(CooperCapabilityDTO cooperCapabilityDTO ) throws SerException {return null;}

    /**
     * 根据公司名查找合作对象商务能力
     * @return class CooperCapabilityBO
     */
    default CooperCapabilityBO getCompany(String companyName ) throws SerException {return null;}

    /**
     * 查找所有公司名
     * @return class CooperCapabilityBO
     */
    default List<CooperCapabilityBO> listAllCompanyName( ) throws SerException {return null;}


}