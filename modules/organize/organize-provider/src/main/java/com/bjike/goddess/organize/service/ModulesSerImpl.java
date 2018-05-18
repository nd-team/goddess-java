package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.dto.ModulesDTO;
import com.bjike.goddess.organize.entity.Modules;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 岗位工作明细表-模块表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:58 ]
 * @Description: [ 岗位工作明细表-模块表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "organizeSerCache")
@Service
public class ModulesSerImpl extends ServiceImpl<Modules, ModulesDTO> implements ModulesSer {
    @Override
    public Map<String, String> findModuleAndPost(String username) throws SerException {
        String sql = "select e.module, if(d.position=null,' ',d.position)as position from (select  c.* from organize_position_detail_user a,organize_position_user_detail b," +
                "   organize_position_detail c " +
                " where a.id = b.userId and b.workStatus=0 and b.positionId= c.id " +
                " and a.name='" + username + "'" +
                " ) d left join organize_moduletype e on d.module_id=e.id ";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            Object[] o = (Object[]) objects.get(0);
            Map<String, String> map = new HashMap<>(1);
            map.put("module",String.valueOf(o[0]));
            map.put("position", String.valueOf(o[1]));
            return map;
        }
        return null;
    }
}