package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.headcount.bo.ConfigCurrentBO;
import com.bjike.goddess.headcount.entity.ConfigCurrent;
import com.bjike.goddess.headcount.dto.ConfigCurrentDTO;
import com.bjike.goddess.headcount.to.ConfigCurrentTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
* 当前部门人数配置业务接口
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:19:57.633 ]
* @Description:	[ 当前部门人数配置业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ConfigCurrentSer extends Ser<ConfigCurrent, ConfigCurrentDTO> {

    default ConfigCurrentBO save(TransactionContext txContext, ConfigCurrentTO configCurrentTO)throws SerException{
        return null;
    }


    default List<ConfigCurrentBO> list()throws SerException{
        return null;
    }

    default void update(ConfigCurrentTO configCurrentTO)throws SerException{

    }

    default void remove(String id)throws SerException{

    }


}