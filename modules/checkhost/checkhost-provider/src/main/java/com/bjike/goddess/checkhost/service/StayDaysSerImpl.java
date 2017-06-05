package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.CollectNameBO;
import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;
import com.bjike.goddess.checkhost.entity.StayDays;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 员工住宿天数汇总业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class StayDaysSerImpl extends ServiceImpl<StayDays, StayDaysDTO> implements StayDaysSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    public Long countStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        Long count = super.count(stayDaysDTO);
        return count;
    }
    @Override
    public StayDaysBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        StayDays stayDays = super.findById(id);
        return BeanTransform.copyProperties(stayDays,StayDaysBO.class);
    }
    @Override
    public List<StayDaysBO> findListStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        List<StayDays> stayDaysList = super.findByCis(stayDaysDTO, true);
        return BeanTransform.copyProperties(stayDaysList, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayDaysBO insertStayDays(StayDaysTO stayDaysTO) throws SerException {
        StayDays stayDays = BeanTransform.copyProperties(stayDaysTO, StayDays.class, true);
        stayDays.setCreateTime(LocalDateTime.now());
        super.save(stayDays);
        return BeanTransform.copyProperties(stayDays, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayDaysBO editStayDays(StayDaysTO stayDaysTO) throws SerException {
        if (!StringUtils.isEmpty(stayDaysTO.getId())) {
            StayDays stayDays = super.findById(stayDaysTO.getId());
            BeanTransform.copyProperties(stayDaysTO, stayDays, true);
            stayDays.setModifyTime(LocalDateTime.now());
            super.update(stayDays);
        } else {
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(stayDaysTO, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayDays(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public StayDaysBO auditStayDays(StayDaysTO stayDaysTO) throws SerException {
        stayDaysTO.setComprehensiveVerify(userAPI.currentUser().getUsername());
        StayDays stayDays = BeanTransform.copyProperties(stayDaysTO, StayDays.class, true);
        super.update(stayDays);

        StayDaysBO stayDaysBO = BeanTransform.copyProperties(stayDays, StayDaysBO.class);
        return stayDaysBO;
    }
    @Override
    public List<CollectNameBO> collectName(String[] names) throws SerException {
        String[] namesTemp = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            namesTemp[i] = "'" + names[i] + "'";
        }
        String namesStr = StringUtils.join(namesTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT name,num AS num,area as area,projectGroup as projectGroup,address AS address, ");
        sb.append(" stayTime AS stayTime,hostTime AS hostTime,is_receiveKey AS receiveKey,is_bed AS bed ");
        sb.append(" FROM checkhost_staydays WHERE name IN (%s) ");
        sb.append(" GROUP BY num,area,projectGroup,address,stayTime,hostTime,receiveKey,bed, ");
        sb.append(" name ORDER BY name ");
        String sql = sb.toString();
        sql = String.format(sql, namesStr);
        String[] fields = new String[]{"name", "num", "area", "projectGroup", "address", "stayTime",
                "hostTime", "receiveKey", "bed"};
        List<CollectNameBO> collectNameBOS = super.findBySql(sql, CollectNameBO.class, fields);
        return collectNameBOS;

    }

    @Override
    public List<String> getNames() throws SerException {
        String[] fields = new String[]{"name"};
        List<StayDaysBO> stayDaysBOS = super.findBySql("select distinct name from checkhost_staydays group by name order by name asc ", StayDaysBO.class, fields);

        List<String> nameList = stayDaysBOS.stream().map(StayDaysBO::getName)
                .filter(name -> (StringUtils.isNotBlank(name))).distinct().collect(Collectors.toList());


        return nameList;
    }
}