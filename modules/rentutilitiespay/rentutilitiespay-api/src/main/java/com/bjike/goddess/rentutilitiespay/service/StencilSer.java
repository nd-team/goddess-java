package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentutilitiespay.bo.StencilBO;
import com.bjike.goddess.rentutilitiespay.entity.Stencil;
import com.bjike.goddess.rentutilitiespay.dto.StencilDTO;
import com.bjike.goddess.rentutilitiespay.to.StencilTO;

import java.util.List;

/**
* 模板业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface StencilSer extends Ser<Stencil, StencilDTO> {
    /**
     * 添加
     */
    void add(StencilTO stencilTO) throws SerException;

    /**
     * 修改
     */
    void edit(StencilTO stencilTO) throws SerException;

    /**
     * 列表
     */
    List<StencilBO> pageList(StencilDTO dto) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 根据id查询单条数据
     */
    StencilBO findOne(String id) throws SerException;

    /**
     * 查询列表总条数
     */
    Long count(StencilDTO dto) throws SerException;

 }