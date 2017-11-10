package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.CaseCountSetBO;
import com.bjike.goddess.attendance.dto.CaseCountSetDTO;
import com.bjike.goddess.attendance.entity.CaseCountSet;
import com.bjike.goddess.attendance.to.CaseCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 考勤情况汇总设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-19 06:18 ]
 * @Description: [ 考勤情况汇总设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CaseCountSetSer extends Ser<CaseCountSet, CaseCountSetDTO> {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    CaseCountSetBO save(CaseCountSetTO to) throws SerException;

    /**
     * 编辑
     * @param to
     * @throws SerException
     */
    void edit(CaseCountSetTO to) throws SerException;

    /**
     * 列表
     * @param dto
     * @return
     * @throws SerException
     */
    List<CaseCountSetBO> list(CaseCountSetDTO dto) throws SerException;

    /**
     * 删除
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     * @param id
     * @return
     * @throws SerException
     */
    CaseCountSetBO findByID(String id) throws SerException;

    /**
     * 总条数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(CaseCountSetDTO dto) throws SerException;

    /**
     * 定时邮件发送
     * @throws SerException
     */
    void send() throws SerException;
}