package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.IndexContentDTO;
import com.bjike.goddess.recruit.entity.IndexContent;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 考核指标内容业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-14 02:46 ]
 * @Description: [ 考核指标内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class IndexContentSerImpl extends ServiceImpl<IndexContent, IndexContentDTO> implements IndexContentSer {


}