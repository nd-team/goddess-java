package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.ServerCarInfoBO;
import com.bjike.goddess.dispatchcar.dto.ServerCarInfoDTO;

import java.util.List;

/**
* 旧服务器上的出车记录业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-07 06:20 ]
* @Description:	[ 旧服务器上的出车记录业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ServerCarInfoAPI {
    /**
     * 查询所有旧服务器出车管理数据库数据
     */
    List<ServerCarInfoBO> listPage(ServerCarInfoDTO dto) throws SerException;

 }