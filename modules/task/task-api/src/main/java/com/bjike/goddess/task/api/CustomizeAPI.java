package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.to.CustomizeTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CustomizeAPI {
    /**
     * 列表
     *
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

//    /**
//     * 启用或者关闭定时任务
//     * @param id
//     * @param enable
//     * @throws SerException
//     */
//    default void enable(String id, boolean enable) throws SerException {
//
//    }
//
//    /**
//     * 执行任务
//     *
//     * @throws SerException
//     */
//    default void executeTask() throws SerException {
//
//    }

    /**
     * 获取实际完成规模数
     *
     * @param tableIds
     * @return
     * @throws SerException
     */
    Double get(String[] tableIds) throws SerException;

    /**
     * 设置通报
     *
     * @param to
     * @throws SerException
     */
    void notice(CustomizeTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    CustomizeBO one(String id) throws SerException;

    /**
     * 定时发送
     *
     * @throws SerException
     */
    void quartz() throws SerException;

    /**
     * 查看详情
     * @param id
     * @return
     * @throws SerException
     */
    String detail(String id) throws SerException;


}
