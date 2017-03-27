package com.bjike.goddess.headcount.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.headcount.bo.ConfigCurrentBO;
import com.bjike.goddess.headcount.entity.ConfigCurrent;
import com.bjike.goddess.headcount.service.ConfigCurrentSer;
import com.bjike.goddess.headcount.to.ConfigCurrentTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 当前部门人数配置业务接口实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:19:57.637 ]
* @Description:	[ 当前部门人数配置业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("configCurrentApiImpl")
public class ConfigCurrentApiImpl implements ConfigCurrentAPI  {
    @Autowired
    private ConfigCurrentSer configCurrentSer;
    @Override
    public ConfigCurrentBO save(TransactionContext txContext,ConfigCurrentTO configCurrentTO)throws SerException{
      return configCurrentSer.save(txContext,configCurrentTO);
    }

    @Override
    public List<ConfigCurrentBO> list()throws SerException{
        return configCurrentSer.list();

    }

    @Override
    public void update(ConfigCurrentTO configCurrentTO)throws SerException{
        configCurrentSer.update(configCurrentTO);

    }

    @Override
    public void remove(String id)throws SerException{
        configCurrentSer.remove(id);
    }

 }