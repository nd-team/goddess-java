package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.bo.ServerCarInfoBO;
import com.bjike.goddess.dispatchcar.dto.ServerCarInfoDTO;
import com.bjike.goddess.dispatchcar.entity.ServerCarInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 旧服务器上的出车记录业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-07 06:20 ]
* @Description:	[ 旧服务器上的出车记录业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="dispatchcarSerCache")
@Service
public class ServerCarInfoSerImpl extends ServiceImpl<ServerCarInfo, ServerCarInfoDTO> implements ServerCarInfoSer {
    @Override
    public List<ServerCarInfoBO> listPage(ServerCarInfoDTO dto) throws SerException {
        List<ServerCarInfo> carInfos = super.findAll();
        List<ServerCarInfoBO> boList = BeanTransform.copyProperties(carInfos,ServerCarInfoBO.class);
        return boList;
    }
}