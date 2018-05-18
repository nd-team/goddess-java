package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.dto.PersonnelDTO;
import com.bjike.goddess.abilitydisplay.entity.Personnel;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 人员组成业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 02:13 ]
 * @Description: [ 人员组成业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "abilitydisplaySerCache")
@Service
public class PersonnelSerImpl extends ServiceImpl<Personnel, PersonnelDTO> implements PersonnelSer {

}