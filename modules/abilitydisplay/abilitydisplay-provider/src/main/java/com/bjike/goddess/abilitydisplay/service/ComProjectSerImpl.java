package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.dto.ComProjectDTO;
import com.bjike.goddess.abilitydisplay.entity.ComProject;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 公司项目业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:05 ]
 * @Description: [ 公司项目业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "abilitydisplaySerCache")
@Service
public class ComProjectSerImpl extends ServiceImpl<ComProject, ComProjectDTO> implements ComProjectSer {

}