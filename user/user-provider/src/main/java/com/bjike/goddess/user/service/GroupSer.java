package com.bjike.goddess.user.service;


import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.GroupDTO;
import com.bjike.goddess.user.entity.Group;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: [部门业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class GroupSer extends ServiceImpl<Group, GroupDTO> implements GroupAPI {

}
