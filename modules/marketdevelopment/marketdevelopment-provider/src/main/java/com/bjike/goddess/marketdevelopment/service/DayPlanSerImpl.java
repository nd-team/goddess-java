package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.DayPlanBO;
import com.bjike.goddess.marketdevelopment.dto.DayPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.DayPlan;
import com.bjike.goddess.marketdevelopment.to.DayPlanTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 天计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class DayPlanSerImpl extends ServiceImpl<DayPlan, DayPlanDTO> implements DayPlanSer {

    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO save(DayPlanTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(planManage))
            throw new SerException("您的帐号没有权限");
        DayPlan entity = BeanTransform.copyProperties(to, DayPlan.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DayPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO update(DayPlanTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(planManage))
            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                DayPlan entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, DayPlanBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO delete(DayPlanTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(planManage))
            throw new SerException("您的帐号没有权限");
        DayPlan entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DayPlanBO.class);
    }

    @Override
    public List<DayPlanBO> findByDate(String start, String end) throws SerException {
        LocalDate startDate = LocalDate.parse(start), endDate = LocalDate.parse(end);
        DayPlanDTO dto = new DayPlanDTO();
        dto.getConditions().add(Restrict.gt_eq("time", startDate));
        dto.getConditions().add(Restrict.lt_eq("time", endDate));
        List<DayPlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DayPlanBO.class);
    }

    @Override
    public List<DayPlanBO> findByDate(String date) throws SerException {
        LocalDate time = LocalDate.parse(date);
        DayPlanDTO dto = new DayPlanDTO();
        dto.getConditions().add(Restrict.eq("time", time));
        List<DayPlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DayPlanBO.class);
    }

    @Override
    public List<DayPlanBO> maps(DayPlanDTO dto) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(marketCheck)
                && !marPermissionSer.getMarPermission(planManage) && !marPermissionSer.getMarPermission(planCheck))
            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("time=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), DayPlanBO.class);
    }
}