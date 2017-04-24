package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.GroupByInsurerBO;
import com.bjike.goddess.businsurance.dto.GroupByInsurerDTO;
import com.bjike.goddess.businsurance.entity.GroupByInsurer;
import com.bjike.goddess.businsurance.to.GroupByInsurerTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.GroupByInsurerDTO;
import com.bjike.goddess.businsurance.entity.GroupByInsurer;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 团体意外险被保险人信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:07 ]
 * @Description: [ 团体意外险被保险人信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class GroupByInsurerSerImpl extends ServiceImpl<GroupByInsurer, GroupByInsurerDTO> implements GroupByInsurerSer {


    @Override
    public Long countGroupByInsurer(GroupByInsurerDTO groupByInsurerDTO) throws SerException {
        if(StringUtils.isBlank(groupByInsurerDTO.getGroupInsureId())){
            throw new SerException("团体意外险id不能为空");
        }
        Long count = super.count(groupByInsurerDTO);
        return count;
    }

    @Override
    public List<GroupByInsurerBO> listGroupByInsurer(GroupByInsurerDTO groupByInsurerDTO) throws SerException {
        if(StringUtils.isBlank(groupByInsurerDTO.getGroupInsureId())){
            throw new SerException("团体意外险id不能为空");
        }
        groupByInsurerDTO.getSorts().add("createTime=asc");
        List<GroupByInsurer> list = super.findByCis(groupByInsurerDTO,true);

        return BeanTransform.copyProperties(list, GroupByInsurerBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GroupByInsurerBO addGroupByInsurer(GroupByInsurerTO groupByInsurerTO) throws SerException {
        if(StringUtils.isBlank(groupByInsurerTO.getGroupInsureId())){
            throw new SerException("团体意外险id不能为空");
        }
        GroupByInsurer groupByInsurer = BeanTransform.copyProperties(groupByInsurerTO,GroupByInsurer.class,true);
        groupByInsurer.setCreateTime(LocalDateTime.now());
        super.save( groupByInsurer );
        return BeanTransform.copyProperties(groupByInsurer, GroupByInsurerBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GroupByInsurerBO editGroupByInsurer(GroupByInsurerTO groupByInsurerTO) throws SerException {
        if(StringUtils.isBlank(groupByInsurerTO.getGroupInsureId())){
            throw new SerException("团体意外险id不能为空");
        }
        GroupByInsurer groupByInsurer = BeanTransform.copyProperties(groupByInsurerTO,GroupByInsurer.class,true);
        GroupByInsurer cusLevel = super.findById( groupByInsurerTO.getId() );

        BeanUtils.copyProperties(groupByInsurer , cusLevel ,"id","createTime","groupInsureId");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(groupByInsurer, GroupByInsurerBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteGroupByInsurer(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove( id );
    }

    @Override
    public GroupByInsurerBO getGroupByInsurer(String id) throws SerException {
        GroupByInsurer groupInsure = super.findById(id);
        return BeanTransform.copyProperties(groupInsure , GroupByInsurerBO.class);
    }
}