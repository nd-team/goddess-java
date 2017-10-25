package com.bjike.goddess.rentutilitiespay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rentutilitiespay.bo.StencilBO;
import com.bjike.goddess.rentutilitiespay.dto.StencilDTO;
import com.bjike.goddess.rentutilitiespay.service.StencilSer;
import com.bjike.goddess.rentutilitiespay.to.StencilTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 模板业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("stencilApiImpl")
public class StencilApiImpl implements StencilAPI  {
    @Autowired
    private StencilSer stencilSer;

    @Override
    public void add(StencilTO stencilTO) throws SerException {
        stencilSer.add(stencilTO);
    }

    @Override
    public void edit(StencilTO stencilTO) throws SerException {
        stencilSer.edit(stencilTO);
    }

    @Override
    public List<StencilBO> pageList(StencilDTO dto) throws SerException {
        return stencilSer.pageList(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        stencilSer.delete(id);
    }

    @Override
    public StencilBO findOne(String id) throws SerException {
        return stencilSer.findOne(id);
    }

    @Override
    public Long count(StencilDTO dto) throws SerException {
        return stencilSer.count(dto);
    }
}