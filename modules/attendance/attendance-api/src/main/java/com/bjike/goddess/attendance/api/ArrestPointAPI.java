package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.ArrestPointBO;
import com.bjike.goddess.attendance.dto.ArrestPointDTO;
import com.bjike.goddess.attendance.to.ArrestPointTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;
import java.util.Set;

/**
 * 驻点设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArrestPointAPI {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ArrestPointBO save(ArrestPointTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ArrestPointTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ArrestPointBO> list(ArrestPointDTO dto) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 根据id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    ArrestPointBO findByID(String id) throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ArrestPointDTO dto) throws SerException;

    /**
     * 启用
     *
     * @param id
     * @throws SerException
     */
    void start(String id) throws SerException;

    /**
     * 禁用
     *
     * @param id
     * @throws SerException
     */
    void stop(String id) throws SerException;

    /**
     * 查找所有驻点地点
     *
     * @return
     * @throws SerException
     */
    Set<String> pointAreas() throws SerException;
}