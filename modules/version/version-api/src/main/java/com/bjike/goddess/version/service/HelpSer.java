package com.bjike.goddess.version.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.version.bo.HelpBO;
import com.bjike.goddess.version.dto.HelpDTO;
import com.bjike.goddess.version.entity.Help;
import com.bjike.goddess.version.to.AnswerTO;
import com.bjike.goddess.version.to.HelpTO;

import java.util.List;

/**
 * 帮助与解答业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HelpSer extends Ser<Help, HelpDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<HelpBO> list(HelpDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    HelpBO save(HelpTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(HelpTO to) throws SerException;

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
    HelpBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(HelpDTO dto) throws SerException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws SerException
     */
    String findDetail(String id) throws SerException;

    /**
     * 解答
     *
     * @param id
     * @param answerTO
     * @throws SerException
     */
    void answer(String id, AnswerTO answerTO) throws SerException;
}