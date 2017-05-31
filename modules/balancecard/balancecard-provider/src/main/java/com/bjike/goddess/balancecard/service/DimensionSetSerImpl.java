package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.DimensionSetBO;
import com.bjike.goddess.balancecard.to.DimensionSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.balancecard.dto.DimensionSetDTO;
import com.bjike.goddess.balancecard.entity.DimensionSet;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 维度设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 维度设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class DimensionSetSerImpl extends ServiceImpl<DimensionSet, DimensionSetDTO> implements DimensionSetSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public Long countDimensionSet(DimensionSetDTO dimensionSetDTO) throws SerException {
        if (StringUtils.isNotBlank(dimensionSetDTO.getTypeName())) {
            dimensionSetDTO.getConditions().add(Restrict.like("typeName", dimensionSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(dimensionSetDTO.getPerson())) {
            dimensionSetDTO.getConditions().add(Restrict.like("person", dimensionSetDTO.getPerson()));
        }
        Long count = super.count(dimensionSetDTO);
        return count;
    }

    @Override
    public DimensionSetBO getOneById(String id) throws SerException {

        return null;
    }

    @Override
    public List<DimensionSetBO> listDimensionSet(DimensionSetDTO dimensionSetDTO) throws SerException {
        if (StringUtils.isNotBlank(dimensionSetDTO.getTypeName())) {
            dimensionSetDTO.getConditions().add(Restrict.like("typeName", dimensionSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(dimensionSetDTO.getPerson())) {
            dimensionSetDTO.getConditions().add(Restrict.like("person", dimensionSetDTO.getPerson()));
        }
        List<DimensionSet> list = super.findByCis(dimensionSetDTO, true);
        List<DimensionSetBO> boList = BeanTransform.copyProperties(list, DimensionSetBO.class);
        return boList;
    }

    @Override
    public DimensionSetBO addDimensionSet(DimensionSetTO dimensionSetTO) throws SerException {
        if (StringUtils.isBlank(dimensionSetTO.getTypeName())) {
            throw new SerException("维度名称不能为空");
        }
        DimensionSet temp = BeanTransform.copyProperties( dimensionSetTO , DimensionSet.class,true);
        temp.setPerson( userAPI.currentUser().getUsername() );
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save( temp );
        return BeanTransform.copyProperties( temp , DimensionSetBO.class);
    }

    @Override
    public DimensionSetBO editDimensionSet(DimensionSetTO dimensionSetTO) throws SerException {
        if( StringUtils.isBlank(dimensionSetTO.getId())){
            throw new SerException("失败，id不能为空");
        }if( StringUtils.isBlank(dimensionSetTO.getTypeName())){
            throw new SerException("失败，维度名称不能为空");
        }
        DimensionSet temp = super.findById( dimensionSetTO.getId() );
        temp.setTypeName( dimensionSetTO.getTypeName());
        temp.setDescribtion( dimensionSetTO.getDescribtion() );
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties( temp , DimensionSetBO.class);
    }

    @Override
    public void deleteDimensionSet(String id) throws SerException {
        if( StringUtils.isBlank( id )){
            throw new SerException("失败，id不能为空");
        }
        super.remove( id );
    }

    @Override
    public List<String> listName() throws SerException {
        List<String> list = new ArrayList<>();
        String[] field = new String[]{"typeName"};
        String sql = "select typeName from balancecard_dimensionset group by typeName order by typeName desc ";
        List<DimensionSetBO> listBO = super.findBySql(sql , DimensionSetBO.class,field);
        list = listBO.stream().filter(str->StringUtils.isNotBlank(str.getTypeName())).map(DimensionSetBO::getTypeName).collect(Collectors.toList());
        return list;
    }
}