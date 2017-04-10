package com.bjike.goddess.bonus.service;

import com.bjike.goddess.bonus.bo.*;
import com.bjike.goddess.bonus.dto.DisciplineRecordDTO;
import com.bjike.goddess.bonus.entity.DisciplineRecord;
import com.bjike.goddess.bonus.to.CollectFilterTO;
import com.bjike.goddess.bonus.to.DisciplineRecordTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 奖罚记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-10 11:54 ]
 * @Description: [ 奖罚记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bonusSerCache")
@Service
public class DisciplineRecordSerImpl extends ServiceImpl<DisciplineRecord, DisciplineRecordDTO> implements DisciplineRecordSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @Override
    public DisciplineRecordBO save(DisciplineRecordTO to) throws SerException {
        DisciplineRecord entity = BeanTransform.copyProperties(to, DisciplineRecord.class, true);
        UserBO user = userAPI.findByUsername(to.getUsername());
        if (null == user)
            throw new SerException("该用户不存在");
        UserDetailBO userDetail = userDetailAPI.findByUserId(user.getId());
        entity.setSerialNumber(user.getEmployeeNumber());
        if (null != userDetail) {
            entity.setProject(userDetail.getDepartmentName());
            DepartmentDetailBO departmentDetail = departmentDetailAPI.findByDepartment(userDetail.getDepartmentId());
            if (departmentDetail != null)
                entity.setArea(departmentDetail.getArea());
        }
        if (StringUtils.isBlank(entity.getLaunch()))
            entity.setLaunch(userAPI.currentUser().getUsername());
        if (entity.getOccurrence() == null)
            entity.setOccurrence(LocalDateTime.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    @Override
    public DisciplineRecordBO update(DisciplineRecordTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            DisciplineRecord entity = super.findById(to.getId());
            if (null == entity)
                throw new SerException("数据对象不能为空");
            UserBO user = userAPI.currentUser();
            if (!user.getUsername().equals(entity.getLaunch()))
                throw new SerException("不要修改他人的数据");
            BeanTransform.copyProperties(to, entity, true);
            user = userAPI.findByUsername(to.getUsername());
            if (null == user)
                throw new SerException("该用户不存在");
            UserDetailBO userDetail = userDetailAPI.findByUserId(user.getId());
            entity.setSerialNumber(user.getEmployeeNumber());
            if (null != userDetail) {
                entity.setProject(userDetail.getDepartmentName());
                DepartmentDetailBO departmentDetail = departmentDetailAPI.findByDepartment(userDetail.getDepartmentId());
                if (departmentDetail != null)
                    entity.setArea(departmentDetail.getArea());
            }
            if (entity.getOccurrence() == null)
                entity.setOccurrence(LocalDateTime.now());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);

        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public DisciplineRecordBO delete(String id) throws SerException {
        DisciplineRecord entity = super.findById(id);
        UserBO user = userAPI.currentUser();
        if (!user.getUsername().equals(entity.getLaunch()))
            throw new SerException("不要修改他人的数据");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    @Override
    public List<DisciplineRecordRankBO> projectRewardRank(CollectFilterTO to) throws SerException {
        List<DisciplineRecordBO> list = this.findByFilter(to).stream()
                .filter(d -> d.getStatus() == Boolean.TRUE)
                .sorted(Comparator.comparing(DisciplineRecordBO::getArea).thenComparing(DisciplineRecordBO::getProject))
                .collect(Collectors.toList());


        return null;
    }

    @Override
    public List<DisciplineRecordRankBO> projectPushRank(CollectFilterTO to) throws SerException {
        return null;
    }

    @Override
    public List<DisciplineRecordRankBO> personalRewardRank(CollectFilterTO to) throws SerException {
        return null;
    }

    @Override
    public List<DisciplineRecordRankBO> personalPushRank(CollectFilterTO to) throws SerException {
        return null;
    }

    @Override
    public List<DisciplineRecordDetailBO> disciplineDetailCollect(CollectFilterTO to) throws SerException {
        return null;
    }

    @Override
    public List<DisciplineRecordQuantityBO> disciplineQuantityCollect(CollectFilterTO to) throws SerException {
        return null;
    }

    @Override
    public List<DisciplineRecordScoreBO> disciplineScoreCollect(CollectFilterTO to) throws SerException {
        return null;
    }

    @Override
    public List<DisciplineRecordBO> findByFilter(CollectFilterTO to) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd())) {
            LocalDateTime start = LocalDateTime.parse(to.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    end = LocalDateTime.parse(to.getEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime[] time = {start, end};
            dto.getConditions().add(Restrict.between("occurrence", time));
        }
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getProject()))
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        if (StringUtils.isNotBlank(to.getTarget()))
            dto.getConditions().add(Restrict.eq("name", to.getTarget()));
        List<DisciplineRecord> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DisciplineRecordBO.class);
    }
}