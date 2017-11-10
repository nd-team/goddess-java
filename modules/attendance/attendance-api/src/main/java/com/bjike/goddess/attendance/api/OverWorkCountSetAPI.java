package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.OverWorkCountSetBO;
import com.bjike.goddess.attendance.dto.OverWorkCountSetDTO;
import com.bjike.goddess.attendance.to.OverWorkCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 加班汇总通报设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:16 ]
 * @Description: [ 加班汇总通报设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OverWorkCountSetAPI {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    OverWorkCountSetBO save(OverWorkCountSetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(OverWorkCountSetTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<OverWorkCountSetBO> list(OverWorkCountSetDTO dto) throws SerException;

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
    OverWorkCountSetBO findByID(String id) throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(OverWorkCountSetDTO dto) throws SerException;

    /**
     * 定时检测发送
     * @throws SerException
     */
    void send() throws SerException;
}