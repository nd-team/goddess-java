package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.GroupInsureBO;
import com.bjike.goddess.businsurance.dto.GroupByInsurerDTO;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.entity.GroupByInsurer;
import com.bjike.goddess.businsurance.entity.GroupInsure;
import com.bjike.goddess.businsurance.to.GroupInsureTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.entity.GroupInsure;
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
 * 团体意外险信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class GroupInsureSerImpl extends ServiceImpl<GroupInsure, GroupInsureDTO> implements GroupInsureSer {

    @Autowired
    private GroupByInsurerSer groupByInsurerSer;

    @Override
    public Long countGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {
        if (StringUtils.isNotBlank(groupInsureDTO.getContractor())){
            groupInsureDTO.getConditions().add(Restrict.like("contractor",groupInsureDTO.getContractor()));
        }
        Long count = super.count(groupInsureDTO);
        return count;
    }

    @Override
    public List<GroupInsureBO> listGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {
        if (StringUtils.isNotBlank(groupInsureDTO.getContractor())){
            groupInsureDTO.getConditions().add(Restrict.like("contractor",groupInsureDTO.getContractor()));
        }
        groupInsureDTO.getSorts().add("createTime=desc");
        List<GroupInsure> list = super.findByCis(groupInsureDTO,true);

        return BeanTransform.copyProperties(list, GroupInsureBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GroupInsureBO addGroupInsure(GroupInsureTO groupInsureTO) throws SerException {
        GroupInsure groupInsure = BeanTransform.copyProperties(groupInsureTO,GroupInsure.class,true);
        groupInsure.setCreateTime(LocalDateTime.now());
        super.save( groupInsure );
        return BeanTransform.copyProperties(groupInsure, GroupInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GroupInsureBO editGroupInsure(GroupInsureTO groupInsureTO) throws SerException {
        GroupInsure groupInsure = BeanTransform.copyProperties(groupInsureTO,GroupInsure.class,true);
        GroupInsure cusLevel = super.findById( groupInsureTO.getId() );

        BeanUtils.copyProperties(groupInsure , cusLevel ,"id","createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(groupInsure, GroupInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteGroupInsure(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }

        GroupByInsurerDTO groupByInsurerDTO = new GroupByInsurerDTO();
        groupByInsurerDTO.getConditions().add(Restrict.eq("groupInsureId", id ));
        List<GroupByInsurer> groupByInsurerList = groupByInsurerSer.findByCis( groupByInsurerDTO  );
        if( groupByInsurerDTO != null && groupByInsurerList.size()>0 ){
            groupByInsurerSer.remove( groupByInsurerList );
        }
        super.remove( id );
    }

    @Override
    public GroupInsureBO getGroupInsure(String id) throws SerException {
        GroupInsure groupInsure = super.findById(id);
        return BeanTransform.copyProperties(groupInsure , GroupInsureBO.class);
    }
}