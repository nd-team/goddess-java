package com.bjike.goddess.version.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.version.bo.AnswerBO;
import com.bjike.goddess.version.dto.AnswerDTO;
import com.bjike.goddess.version.to.AnswerTO;

import java.util.List;

/**
 * 答案业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:10 ]
 * @Description: [ 答案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnswerAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AnswerBO> list(AnswerDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    AnswerBO save(AnswerTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(AnswerTO to) throws SerException;

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
    AnswerBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AnswerDTO dto) throws SerException;
}