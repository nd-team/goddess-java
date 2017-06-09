package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.CollectionPeriodBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.CollectionPeriodDTO;
import com.bjike.goddess.projectroyalty.to.CollectionPeriodTO;

import java.util.List;

/**
 * 回款周期业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:55 ]
 * @Description: [ 回款周期业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectionPeriodAPI {

    /**
     * 保存
     *
     * @param to 回款周期传输对象
     * @return
     * @throws SerException
     */
    default CollectionPeriodBO save(CollectionPeriodTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 回款周期传输对象
     * @return
     * @throws SerException
     */
    default CollectionPeriodBO update(CollectionPeriodTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 回款周期数据id
     * @return
     * @throws SerException
     */
    default CollectionPeriodBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取回款周期数据
     *
     * @param id 回款周期数据id
     * @return
     * @throws SerException
     */
    default CollectionPeriodBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findOpinion() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 回款周期数据传输对象
     * @return
     * @throws SerException
     */
    default List<CollectionPeriodBO> maps(CollectionPeriodDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }


}