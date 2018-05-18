package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.StencilBO;
import com.bjike.goddess.rentutilitiespay.dto.StencilDTO;
import com.bjike.goddess.rentutilitiespay.entity.Stencil;
import com.bjike.goddess.rentutilitiespay.to.StencilTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* 模板业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="rentutilitiespaySerCache")
@Service
public class StencilSerImpl extends ServiceImpl<Stencil, StencilDTO> implements StencilSer {

    @Override
    public void add(StencilTO stencilTO) throws SerException {
        Stencil model = BeanTransform.copyProperties(stencilTO,Stencil.class,true);
        super.save(model);
    }

    @Override
    public void edit(StencilTO stencilTO) throws SerException {
        Stencil model = super.findById(stencilTO.getId());
        if (model != null){
            BeanTransform.copyProperties(stencilTO,model,true,"createTime","modifyTime");
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        }
    }

    @Override
    public List<StencilBO> pageList(StencilDTO dto) throws SerException {
        List<Stencil> stencils = super.findByPage(dto);
        List<StencilBO> boList = BeanTransform.copyProperties(stencils,StencilBO.class,false);
        return boList;
    }

    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            Stencil model = super.findById(id);
            if (model != null){
                super.remove(model);
            }else {
                throw new SerException("数据库中没有该数据库");
            }
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public StencilBO findOne(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            Stencil model = super.findById(id);
            StencilBO bo = BeanTransform.copyProperties(model,StencilBO.class,false);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }
}