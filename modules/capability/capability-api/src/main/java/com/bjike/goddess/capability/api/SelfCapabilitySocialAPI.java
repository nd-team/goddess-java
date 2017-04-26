package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.SelfCapabilitySocialBO;
import com.bjike.goddess.capability.dto.SelfCapabilitySocialDTO;
import com.bjike.goddess.capability.to.SelfCapabilitySocialTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 社交能力展示业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 社交能力展示业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SelfCapabilitySocialAPI {


    /**
     * 总条数
     */
    default Long counts(SelfCapabilitySocialDTO selfCapabilitySocialDTO) throws SerException {
        return null;
    }

    /**
     * 一个社交能力展示
     *
     * @return class SelfCapabilitySocialBO
     */
    default SelfCapabilitySocialBO getOne(String id) throws SerException {
        return null;
    }


    /**
     * 社交能力展示列表
     *
     * @return class SelfCapabilitySocialBO
     */
    default List<SelfCapabilitySocialBO> listSelfCapabilitySocial(SelfCapabilitySocialDTO selfCapabilitySocialDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param selfCapabilitySocialTO 社交能力展示信息
     * @return class SelfCapabilitySocialBO
     */
    default SelfCapabilitySocialBO addSelfCapabilitySocial(SelfCapabilitySocialTO selfCapabilitySocialTO) throws SerException {
        return null;
    }


    /**
     * 编辑
     *
     * @param selfCapabilitySocialTO 社交能力展示信息
     * @param id 社交能力展示信息id
     * @return class SelfCapabilitySocialBO
     */
    default SelfCapabilitySocialBO editSelfCapabilitySocial(String id ,SelfCapabilitySocialTO selfCapabilitySocialTO) throws SerException {
        return null;
    }



    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSelfCapabilitySocial(String id) throws SerException {
        return;
    }

    ;





}