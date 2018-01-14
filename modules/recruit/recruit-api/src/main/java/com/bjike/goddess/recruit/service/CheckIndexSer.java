package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.CheckIndexBO;
import com.bjike.goddess.recruit.dto.CheckIndexDTO;
import com.bjike.goddess.recruit.entity.CheckIndex;

import java.util.List;

/**
 * 考核指标业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CheckIndexSer extends Ser<CheckIndex, CheckIndexDTO> {

    /**
     * 考核指标列表
     *
     * @return
     * @throws SerException
     */
    List<CheckIndexBO> list() throws SerException;

    /**
     * 考核指标添加
     *
     * @param bo
     * @throws SerException
     */
    void add(CheckIndexBO bo) throws SerException;

    /**
     * 考核指标删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 考核指标编辑
     *
     * @param id
     * @return
     * @throws SerException
     */
    CheckIndexBO edit(String id) throws SerException;

}