package com.bjike.goddess.moneyprepare.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.moneyprepare.dto.ProjectDetailsDTO;
import com.bjike.goddess.moneyprepare.entity.ProjectDetails;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 项目分配业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:30 ]
 * @Description: [ 项目分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneyprepareSerCache")
@Service
public class ProjectDetailsSerImpl extends ServiceImpl<ProjectDetails, ProjectDetailsDTO> implements ProjectDetailsSer {

}