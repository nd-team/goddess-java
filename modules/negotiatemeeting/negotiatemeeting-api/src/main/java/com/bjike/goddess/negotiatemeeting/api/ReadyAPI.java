package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.ReadyBO;
import com.bjike.goddess.negotiatemeeting.dto.ReadyDTO;
import com.bjike.goddess.negotiatemeeting.to.ReadyTO;

import java.util.List;

/**
 * 协商前准备信息业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:39 ]
 * @Description: [ 协商前准备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReadyAPI {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ReadyBO save(ReadyTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ReadyTO to) throws SerException;

    /**
     * 当前用户的准备列表
     *
     * @return
     * @throws SerException
     */
    List<ReadyBO> currentList() throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ReadyBO> list(ReadyDTO dto) throws SerException;

    /**
     * 查找当前用户准备信息总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ReadyDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    ReadyBO findByID(String id) throws SerException;
}