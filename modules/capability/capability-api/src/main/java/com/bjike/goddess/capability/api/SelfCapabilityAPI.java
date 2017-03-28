package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.SelfCapabilityBO;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.to.SelfCapabilityTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 个人能力展示业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SelfCapabilityAPI {

    /**
     * 个人能力展示列表
     * @return class SelfCapabilityBO
     */
    default List<SelfCapabilityBO> listSelfCapability(SelfCapabilityDTO selfCapabilityDTO) throws SerException {return null;}
    /**
     *  添加
     * @param selfCapabilityTO 个人能力展示信息
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO addSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException { return null;}

    /**
     *  编辑
     * @param selfCapabilityTO 个人能力展示信息
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO editSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteSelfCapability(String id ) throws SerException {return;};

    /**
     * 查看个人社交资源
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO getSelfConnector(String id ) throws SerException {return null;}

    /**
     *  添加或编辑个人社交资源
     * @param selfCapabilityTO 合作对象商务展示信息
     * @return class CooperCapabilityBO
     */
    default SelfCapabilityBO editSelfConnector(SelfCapabilityTO selfCapabilityTO) throws SerException { return null;}

    /**
     * 搜索
     * @return class SelfCapabilityBO
     */
    default List<SelfCapabilityBO> listSelfCapabilityByName(SelfCapabilityDTO selfCapabilityDTO ) throws SerException {return null;}

    /**
     * 根据姓名查找个人能力
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO getSelf(String name ) throws SerException {return null;}

    /**
     * 查找所有姓名
     * @return class SelfCapabilityBO
     */
    default List<SelfCapabilityBO> listAllSelfName( ) throws SerException {return null;}


}