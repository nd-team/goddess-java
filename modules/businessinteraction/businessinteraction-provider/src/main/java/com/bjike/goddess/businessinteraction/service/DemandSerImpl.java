package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.DemandBO;
import com.bjike.goddess.businessinteraction.bo.DemandObjectBO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.entity.Demand;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 互动平台需求描述业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:21 ]
 * @Description: [ 互动平台需求描述业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class DemandSerImpl extends ServiceImpl<Demand, DemandDTO> implements DemandSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public Long countInter(DemandDTO demandDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        RpcTransmit.transmitUserToken( userToken);
        if ( !permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }

        if( StringUtils.isNotBlank( demandDTO.getName())){
            demandDTO.getConditions().add(Restrict.like("name",demandDTO.getName()));
        }
        Long count =  super.count(demandDTO);
        return count;
    }

    @Override
    public DemandBO getOneById(String id) throws SerException {


        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        Demand demand = super.findById(id);

        return BeanTransform.copyProperties(demand, DemandBO.class);
    }
    
    
    @Override
    public List<DemandBO> listDemand(DemandDTO demandDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        RpcTransmit.transmitUserToken( userToken);
        if ( !permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }

        if( StringUtils.isNotBlank( demandDTO.getName())){
            demandDTO.getConditions().add(Restrict.like("name",demandDTO.getName()));
        }
        List<Demand> list = super.findByCis(demandDTO, true);

        return BeanTransform.copyProperties(list, DemandBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandBO addDemand(DemandTO demandTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加操作");
        }

        Demand demand = null;
        try {
            demand = BeanTransform.copyProperties(demandTO,Demand.class,true);
            demand.setCreateTime(LocalDateTime.now());
            super.save( demand );
        } catch (SerException e) {
            throw new SerException("互动平台需求描述添加失败");
        }
        return BeanTransform.copyProperties(demand, DemandBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandBO editDemand(DemandTO demandTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑操作");
        }


        Demand demandTarget = null;
        try {
            Demand demand = BeanTransform.copyProperties(demandTO,Demand.class,true);

            demandTarget = super.findById( demandTO.getId() );
            BeanUtils.copyProperties(demand,demandTarget,"id","createTime");
            demandTarget.setModifyTime(LocalDateTime.now());
            super.save(demandTarget );
        } catch (SerException e) {
            throw new SerException("互动平台需求描述更新失败");
        }
        return BeanTransform.copyProperties(demandTarget,DemandBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteDemand(String id) throws SerException {
        //商务模块删除权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }


        if (StringUtils.isNotBlank(id)){
            super.remove(id);
        }else{
            throw new SerException("互动平台需求描述id不能为空");
        }
    }

    @Override
    public List<DemandBO> searchList(DemandDTO demandDTO) throws SerException {
        if( StringUtils.isNotBlank(demandDTO.getName())){
            demandDTO.getConditions().add(Restrict.like("name",demandDTO.getName()));
        }

        List<Demand> list = super.findByCis(demandDTO, true);

        return BeanTransform.copyProperties(list, DemandBO.class );
    }

    @Override
    public List<DemandObjectBO> searchDemand(DemandDTO demandDTO) throws SerException {
        //TODO : tanghaixiang 2017-03-29 从市场信息管理根据规模和行业拿公司名称，地区
        //TODO : tanghaixiang 2017-03-29 从竞争对手管理根据业务类型拿公司名称，地区
        List<DemandObjectBO> list = new ArrayList<>();
        if( StringUtils.isNotBlank(demandDTO.getBusinessTarget())){
            demandDTO.getConditions().add(Restrict.like("businessTarget",demandDTO.getBusinessTarget()));
        }
        if( StringUtils.isNotBlank(demandDTO.getSize())) {
            demandDTO.getConditions().add(Restrict.like("size",demandDTO.getSize()));
        }
        if( StringUtils.isNotBlank(demandDTO.getProfession())) {
            demandDTO.getConditions().add(Restrict.like("profession",demandDTO.getProfession()));
        }
//        List<Demand> list = super.findByCis(demandDTO, true);

        return BeanTransform.copyProperties(list, DemandObjectBO.class );
    }
}