package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.SelfProjectBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.capability.entity.SelfProject;
import com.bjike.goddess.capability.dto.SelfProjectDTO;

/**
 * 个人经手项目业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:26 ]
 * @Description: [ 个人经手项目业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SelfProjectSer extends Ser<SelfProject, SelfProjectDTO> {
    /**
     * 添加
     *
     * @param selfProjects 个人经手项目信息
     * @param id           个人id
     * @return class SelfProjectBO
     */
    default SelfProjectBO addSelfProject(String[] selfProjects, String id) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param selfProjects 个人经手项目信息
     * @param id           个人id
     * @return class SelfProjectBO
     */
    default SelfProjectBO editSelfProject(String[] selfProjects, String id) throws SerException {
        return null;
    }

    /**
     * 删除个人经手项目
     *
     * @param id id
     */
    default void deleteSelfProject(String id) throws SerException {
        return;
    }


}