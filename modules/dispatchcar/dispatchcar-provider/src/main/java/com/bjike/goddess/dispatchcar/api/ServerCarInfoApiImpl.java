package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.ServerCarInfoBO;
import com.bjike.goddess.dispatchcar.dto.ServerCarInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 旧服务器上的出车记录业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-07 06:20 ]
* @Description:	[ 旧服务器上的出车记录业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("carInfoApiImpl")
public class ServerCarInfoApiImpl implements ServerCarInfoAPI {
    @Override
    public List<ServerCarInfoBO> listPage(ServerCarInfoDTO dto) throws SerException {
        return null;
    }
}