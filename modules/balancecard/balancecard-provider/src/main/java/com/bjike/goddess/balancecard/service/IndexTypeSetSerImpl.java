package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.IndexTypeSetBO;
import com.bjike.goddess.balancecard.dto.IndexTypeSetDTO;
import com.bjike.goddess.balancecard.entity.IndexTypeSet;
import com.bjike.goddess.balancecard.to.IndexTypeSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指标类型业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 指标类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class IndexTypeSetSerImpl extends ServiceImpl<IndexTypeSet, IndexTypeSetDTO> implements IndexTypeSetSer {


    @Autowired
    private UserAPI userAPI;

    @Override
    public Long countIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        if (StringUtils.isNotBlank(indexTypeSetDTO.getTypeName())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("typeName", indexTypeSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(indexTypeSetDTO.getPerson())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("person", indexTypeSetDTO.getPerson()));
        }
        Long count = super.count(indexTypeSetDTO);
        return count;
    }

    @Override
    public IndexTypeSetBO getOneById(String id) throws SerException {

        return null;
    }

    @Override
    public List<IndexTypeSetBO> listIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        if (StringUtils.isNotBlank(indexTypeSetDTO.getTypeName())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("typeName", indexTypeSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(indexTypeSetDTO.getPerson())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("person", indexTypeSetDTO.getPerson()));
        }
        List<IndexTypeSet> list = super.findByCis(indexTypeSetDTO, true);
        List<IndexTypeSetBO> boList = BeanTransform.copyProperties(list, IndexTypeSetBO.class);
        return boList;
    }

    @Override
    public IndexTypeSetBO addIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        if (StringUtils.isBlank(indexTypeSetTO.getTypeName())) {
            throw new SerException("指标类型不能为空");
        }
        IndexTypeSet temp = BeanTransform.copyProperties( indexTypeSetTO , IndexTypeSet.class,true);
        temp.setPerson( userAPI.currentUser().getUsername() );
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save( temp );
        return BeanTransform.copyProperties( temp , IndexTypeSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndexTypeSetBO editIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        if( StringUtils.isBlank(indexTypeSetTO.getId())){
            throw new SerException("失败，id不能为空");
        }
        if( StringUtils.isBlank(indexTypeSetTO.getTypeName())){
            throw new SerException("失败，指标类型不能为空");
        }
        IndexTypeSet temp = super.findById( indexTypeSetTO.getId() );
        BeanTransform.copyProperties(indexTypeSetTO,temp,true);
        temp.setTypeName( indexTypeSetTO.getTypeName());
        temp.setDescribtion( indexTypeSetTO.getDescribtion() );
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties( temp , IndexTypeSetBO.class);
    }

    @Override
    public void deleteIndexTypeSet(String id) throws SerException {
        if( StringUtils.isBlank( id )){
            throw new SerException("失败，id不能为空");
        }
        super.remove( id );
    }


    @Override
    public List<String> listName() throws SerException {
        List<String> list = new ArrayList<>();
        String[] field = new String[]{"typeName"};
        String sql = "select typeName from balancecard_indextypeset group by typeName order by typeName desc ";
        List<IndexTypeSetBO> listBO = super.findBySql(sql , IndexTypeSetBO.class,field);
        list = listBO.stream().filter(str->StringUtils.isNotBlank(str.getTypeName())).map(IndexTypeSetBO::getTypeName).collect(Collectors.toList());
        return list;
    }


}