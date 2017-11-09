package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.to.CustomizeTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CustomizeSer extends Ser<Customize, CustomizeDTO> {
    /**
     * 自定义定时任务列表
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CustomizeBO> list(CustomizeDTO dto) throws SerException {
        return null;
    }

    default Long count(CustomizeDTO dto) throws SerException {
        return null;
    }

    default void add(CustomizeTO to) throws SerException {

    }

    void edit(CustomizeTO to) throws SerException;

    /**
     * 启用或者关闭定时任务
     * @param id
     * @param enable
     * @throws SerException
     */
    default void enable(String id, boolean enable) throws SerException {

    }

    /**
     * 执行任务
     *
     * @throws SerException
     */
    default void executeTask() throws SerException {

    }

    /**
     *
     * @param project
     * @return
     * @throws SerException
     */
    Double get(String project) throws SerException;
}
