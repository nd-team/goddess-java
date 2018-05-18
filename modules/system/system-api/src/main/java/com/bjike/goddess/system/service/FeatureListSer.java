package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.system.bo.FeatureListBO;
import com.bjike.goddess.system.dto.FeatureListDTO;
import com.bjike.goddess.system.entity.FeatureList;
import com.bjike.goddess.system.to.FeatureListTO;
import com.bjike.goddess.system.to.QuestionTO;

import java.util.List;

/**
 * 功能列表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:44 ]
 * @Description: [ 功能列表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FeatureListSer extends Ser<FeatureList, FeatureListDTO> {
    /**
     * 功能列表列表总条数
     */
    default Long count(FeatureListDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个功能列表
     *
     * @return class FeatureListBO
     */
    default FeatureListBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 功能列表
     *
     * @param dto 功能列表dto
     * @return class FeatureListBO
     * @throws SerException
     */
    default List<FeatureListBO> list(FeatureListDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加功能列表
     *
     * @param to 功能列表数据to
     * @return class FeatureListBO
     * @throws SerException
     */
    default FeatureListBO insert(FeatureListTO to) throws SerException {
        return null;
    }

    /**
     * 编辑功能列表
     *
     * @param to 功能列表数据to
     * @return class FeatureListBO
     * @throws SerException
     */
    default FeatureListBO edit(FeatureListTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除功能列表
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }


    /**
     * 我要发问
     *
     * @param id
     * @param questionTO
     * @throws SerException
     */
    default void ask(String id, QuestionTO questionTO) throws SerException {

    }

    /**
     * 详情
     *
     * @param id
     * @return class FeatureListBO
     * @throws SerException
     */
    default FeatureListBO findDetail(String id) throws SerException {
        return null;
    }

    /**
     * 获取功能名称
     * zhuangkaiqin
     */
    default List<String> getFeatureName() throws SerException {
        return null;
    }

    /**
     * 根据功能名称获取功能目的
     * zhuangkaiqin
     * @param name
     * @return
     * @throws SerException
     */
    default String getPurpose(String name) throws SerException {
        return null;
    }

    /**
     * 根据功能名称获取版本
     * zhuangkaiqin
     *
     * @param name
     * @return
     */
    default String getVersion(String name) throws SerException {
        return null;
    }
}