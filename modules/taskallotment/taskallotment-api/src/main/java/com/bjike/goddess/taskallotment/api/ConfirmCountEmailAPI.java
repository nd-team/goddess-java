package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.ConfirmCountEmailBO;
import com.bjike.goddess.taskallotment.dto.ConfirmCountEmailDTO;
import com.bjike.goddess.taskallotment.to.ConfirmCountEmailTO;

import java.util.List;

/**
 * 分配及确认汇总设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:44 ]
 * @Description: [ 分配及确认汇总设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConfirmCountEmailAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ConfirmCountEmailBO> list(ConfirmCountEmailDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ConfirmCountEmailBO save(ConfirmCountEmailTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ConfirmCountEmailTO to) throws SerException;

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
    ConfirmCountEmailBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ConfirmCountEmailDTO dto) throws SerException;

    /**
     * 定时检测发送
     *
     * @throws SerException
     */
    void send() throws SerException;
}