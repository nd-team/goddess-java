package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffmove.dto.StaffMoveImplementBDTO;
import com.bjike.goddess.staffmove.entity.StaffMoveImplementB;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 人员调动实施业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:47 ]
 * @Description: [ 人员调动实施业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmoveSerCache")
@Service
public class StaffMoveImplementBSerImpl extends ServiceImpl<StaffMoveImplementB, StaffMoveImplementBDTO> implements StaffMoveImplementBSer {

}