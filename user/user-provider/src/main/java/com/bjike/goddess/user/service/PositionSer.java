package com.bjike.goddess.user.service;


import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.PositionDTO;
import com.bjike.goddess.user.entity.Position;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * 职位业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service("positionSer")
public class PositionSer extends ServiceImpl<Position, PositionDTO> implements PositionAPI {

}
