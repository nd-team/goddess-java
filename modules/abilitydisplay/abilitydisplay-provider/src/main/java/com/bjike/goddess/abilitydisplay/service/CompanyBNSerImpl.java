package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.dto.CompanyBNDTO;
import com.bjike.goddess.abilitydisplay.entity.CompanyBN;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 公司业务业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:15 ]
 * @Description: [ 公司业务业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "abilitydisplaySerCache")
@Service
public class CompanyBNSerImpl extends ServiceImpl<CompanyBN, CompanyBNDTO> implements CompanyBNSer {

}