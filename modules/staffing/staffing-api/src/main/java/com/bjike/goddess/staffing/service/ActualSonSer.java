package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffing.bo.ActualSonBO;
import com.bjike.goddess.staffing.dto.ActualSonDTO;
import com.bjike.goddess.staffing.entity.ActualSon;
import com.bjike.goddess.staffing.to.ActualSonTO;

import java.util.List;

/**
 * 人数配置-实际子表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-03 11:19 ]
 * @Description: [ 人数配置-实际子表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActualSonSer extends Ser<ActualSon, ActualSonDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ActualSonBO> list(ActualSonDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ActualSonBO save(ActualSonTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ActualSonTO to) throws SerException;

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
    ActualSonBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ActualSonDTO dto) throws SerException;
}