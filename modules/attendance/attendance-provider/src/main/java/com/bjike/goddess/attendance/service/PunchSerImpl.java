package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.entity.Punch;
import com.bjike.goddess.attendance.to.PunchTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 打卡业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:21 ]
 * @Description: [ 打卡业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class PunchSerImpl extends ServiceImpl<Punch, PunchDTO> implements PunchSer {

}