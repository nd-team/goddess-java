package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.bo.ConfigCurrentBO;
import com.bjike.goddess.headcount.dto.ConfigCurrentDTO;
import com.bjike.goddess.headcount.entity.ConfigCurrent;
import com.bjike.goddess.headcount.entity.plancost.GroupInfo;
import com.bjike.goddess.headcount.to.ConfigCurrentTO;
import com.bjike.goddess.headcount.to.GroupInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
* 当前部门人数配置业务实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:19:57.638 ]
* @Description:	[ 当前部门人数配置业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="configurationCurrentSerCache")
@Service
public class ConfigurationCurrentSerImpl extends ServiceImpl<ConfigCurrent, ConfigCurrentDTO> implements ConfigCurrentSer {
       @Transactional(rollbackFor = SerException.class)
       @Override
       public  ConfigCurrentBO save(TransactionContext txContext, ConfigCurrentTO configCurrentTO)throws SerException{
           ConfigCurrent configCurrent = BeanTransform.copyProperties(configCurrentTO,ConfigCurrent.class,true);
           super.save(configCurrent);
           configCurrentTO.setId(configCurrent.getId());
           return BeanTransform.copyProperties(configCurrentTO,ConfigCurrentBO.class);
       }

       @Override
        public List<ConfigCurrentBO> list()throws SerException{
           List<ConfigCurrent> configCurrents = super.findAll();
           List<ConfigCurrentBO> configCurrentBOs =BeanTransform.copyProperties(configCurrents,ConfigCurrentBO.class);
           return configCurrentBOs;
        }

       @Transactional(rollbackFor = SerException.class)
       @Override
       public void update(ConfigCurrentTO configCurrentTO)throws SerException{
           ConfigCurrent configCurrent = super.findById(configCurrentTO.getId());
           BeanTransform.copyProperties(configCurrentTO,configCurrent,true);
           configCurrent.setModifyTime(LocalDateTime.now());
           super.update(configCurrent);
       }

        @Override
        @Transactional(rollbackFor = SerException.class)
        public void  remove(String id) throws SerException{
            super.remove(id);
        }

 }