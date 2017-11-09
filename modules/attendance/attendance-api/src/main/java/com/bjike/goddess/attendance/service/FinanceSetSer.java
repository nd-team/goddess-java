package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.FinanceSetBO;
import com.bjike.goddess.attendance.dto.FinanceSetDTO;
import com.bjike.goddess.attendance.dto.FinanceSetDTO;
import com.bjike.goddess.attendance.entity.FinanceSet;
import com.bjike.goddess.attendance.to.FinanceSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 财务出勤设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:15 ]
 * @Description: [ 财务出勤设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FinanceSetSer extends Ser<FinanceSet, FinanceSetDTO> {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    FinanceSetBO save(FinanceSetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(FinanceSetTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceSetBO> list(FinanceSetDTO dto) throws SerException;

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
    FinanceSetBO findByID(String id) throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(FinanceSetDTO dto) throws SerException;

    /**
     * 定时检测发送
     * @throws SerException
     */
    void send() throws SerException;
}