package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.VacateCountSetBO;
import com.bjike.goddess.attendance.dto.VacateCountSetDTO;
import com.bjike.goddess.attendance.to.VacateCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 请假汇总通报设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:19 ]
 * @Description: [ 请假汇总通报设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VacateCountSetAPI {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    VacateCountSetBO save(VacateCountSetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(VacateCountSetTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateCountSetBO> list(VacateCountSetDTO dto) throws SerException;

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
    VacateCountSetBO findByID(String id) throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(VacateCountSetDTO dto) throws SerException;

    /**
     * 定时检测发送
     * @throws SerException
     */
    void send() throws SerException;
}