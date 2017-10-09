package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.FinishCountEmailBO;
import com.bjike.goddess.taskallotment.dto.FinishCountEmailDTO;
import com.bjike.goddess.taskallotment.to.FinishCountEmailTO;

import java.util.List;

/**
 * 完成情况汇总设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:31 ]
 * @Description: [ 完成情况汇总设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FinishCountEmailAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinishCountEmailBO> list(FinishCountEmailDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    FinishCountEmailBO save(FinishCountEmailTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(FinishCountEmailTO to) throws SerException;

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
    FinishCountEmailBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(FinishCountEmailDTO dto) throws SerException;

    /**
     * 定时发送邮件
     *
     * @throws SerException
     */
    void send() throws SerException;
}