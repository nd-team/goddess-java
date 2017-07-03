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
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Override
    public DisciplineRecordBO save(DisciplineRecordTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        DisciplineRecord entity = BeanTransform.copyProperties(to, DisciplineRecord.class, true);
        entity = this.checkEntity(entity);
        if (StringUtils.isBlank(entity.getLaunch()))
            entity.setLaunch(user.getUsername());
        if (StringUtils.isBlank(entity.getName()))
            entity.setName(" ");
        if (StringUtils.isBlank(entity.getProject()))
            entity.setProject(" ");
        if (StringUtils.isBlank(entity.getArea()))
            entity.setArea(" ");
        super.save(entity);
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    /**
     * 检测奖罚记录实体规范
     *
     * @param entity 奖罚记录实体数据
     * @return
     * @throws SerException
     */
    private DisciplineRecord checkEntity(DisciplineRecord entity) throws SerException {
        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (null == user)
            throw new SerException("该用户不存在");
        entity.setSerialNumber(user.getEmployeeNumber());
        PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.getId());
        entity.setArea("");
        entity.setProject("");
        if (null != detailBO)
            for (String id : detailBO.getPositionIds().split(",")) {
                PositionDetailBO position = positionDetailAPI.findBOById(id);
                entity.setProject(entity.getProject() + "," + position.getDepartmentName());
                entity.setArea(entity.getArea() + "," + position.getArea());
            }
        if (entity.getStatus()) {//检测奖罚分数填写是否符合规范 true 为奖励 false 为处罚
            if (entity.getBallot() < 0)
                entity.setBallot(-entity.getBallot());
        } else {
            if (entity.getBallot() > 0)
                entity.setBallot(-entity.getBallot());
        }
        if (entity.getOccurrence() == null)
            entity.setOccurrence(LocalDateTime.now());
        return entity;
    }

    @Override
    public DisciplineRecordBO update(DisciplineRecordTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        if (StringUtils.isNotBlank(to.getId())) {
            DisciplineRecord entity = super.findById(to.getId());
            if (null == entity)
                throw new SerException("数据对象不能为空");
            if (!user.getUsername().equals(entity.getLaunch()))
                throw new SerException("不要修改他人的数据");
            BeanTransform.copyProperties(to, entity, true);
            entity = this.checkEntity(entity);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public DisciplineRecordBO delete(String id) throws SerException {
        UserBO user = userAPI.currentUser();
        DisciplineRecord entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        if (!user.getUsername().equals(entity.getLaunch()))
            throw new SerException("不要修改他人的数据");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    @Override
    public List<DisciplineRecordRankBO> projectRank(CollectFilterTO to, Boolean status) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("project=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto).stream()
                .filter(d -> d.getStatus() == status)
                .collect(Collectors.toList());
        List<DisciplineRecordRankBO> rankBOs = new ArrayList<>(0);
        String project = "";
        for (DisciplineRecord entity : list)
            if (!entity.getProject().equals(project)) {
                project = entity.getProject();
                DisciplineRecordRankBO rank = new DisciplineRecordRankBO();
                rank.setArea(entity.getArea());
                rank.setDepartment(entity.getProject());
                rank.setStart(to.getStart());
                rank.setEnd(to.getEnd());
                List<DisciplineRecord> countList = list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()))
                        .collect(Collectors.toList());
                rank.setFrequency(countList.size());
                rank.setTotal(countList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                rankBOs.add(rank);
            }
        if (status)
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal).reversed()
                            .thenComparing(DisciplineRecordRankBO::getFrequency))
                    .collect(Collectors.toList());
        else
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal)
                            .thenComparing(DisciplineRecordRankBO::getFrequency))
                    .collect(Collectors.toList());
        for (int i = 0, size = rankBOs.size(); i < size; )
            rankBOs.get(i).setRank(++i);
        return rankBOs;
    }

    @Override
    public List<DisciplineRecordRankBO> personalRank(CollectFilterTO to, Boolean status) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("username=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto).stream()
                .filter(d -> d.getStatus() == status)
                .collect(Collectors.toList());
        List<DisciplineRecordRankBO> rankBOs = new ArrayList<>(0);
        String username = "";
        for (DisciplineRecord entity : list)
            if (!entity.getUsername().equals(entity.getProject())) {
                DisciplineRecordRankBO rank = new DisciplineRecordRankBO();
                rank.setArea(entity.getArea());
                rank.setDepartment(entity.getProject());
                rank.setStart(to.getStart());
                rank.setEnd(to.getEnd());
                rank.setSerialNumber(entity.getSerialNumber());
                rank.setUsername(entity.getUsername());
                List<DisciplineRecord> countList = list.stream()
                        .filter(d -> d.getUsername().equals(entity.getUsername()))
                        .collect(Collectors.toList());
                rank.setFrequency(countList.size());
                rank.setTotal(countList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                rankBOs.add(rank);
            }
        if (status)
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal).reversed())
                    .collect(Collectors.toList());
        else
            rankBOs = rankBOs.stream()
                    .sorted(Comparator.comparing(DisciplineRecordRankBO::getTotal))
                    .collect(Collectors.toList());
        for (int i = 0, size = rankBOs.size(); i < size; )
            rankBOs.get(i).setRank(++i);
        return rankBOs;
    }

    @Override
    public List<DisciplineRecordDetailBO> disciplineDetailCollect(CollectFilterTO to) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("occurrence=asc");
        dto.getSorts().add("username=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto);
        List<DisciplineRecordDetailBO> detailBOs = new ArrayList<>(0);
        String occurrence = "", username = "";
        for (DisciplineRecord entity : list)
            if (!entity.getOccurrence().toLocalDate().toString().equals(occurrence) || !entity.getUsername().equals(username)) {
                occurrence = entity.getOccurrence().toLocalDate().toString();
                username = entity.getUsername();
                DisciplineRecordDetailBO detail = BeanTransform.copyProperties(entity, DisciplineRecordDetailBO.class);
                String time = occurrence;
                List<DisciplineRecord> pushList = list.stream()
                        .filter(d -> d.getOccurrence().toLocalDate().toString().equals(time)
                                && d.getUsername().equals(entity.getUsername())
                                && d.getStatus() == Boolean.FALSE)
                        .collect(Collectors.toList()), rewardList = list.stream()
                        .filter(d -> d.getOccurrence().toLocalDate().toString().equals(time)
                                && d.getUsername().equals(entity.getUsername())
                                && d.getStatus() == Boolean.TRUE)
                        .collect(Collectors.toList());
                detail.setOccurrence(occurrence);
                detail.setPush(pushList.size());
                detail.setPushTotal(pushList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                detail.setReward(rewardList.size());
                detail.setRewardTotal(rewardList.stream().mapToDouble(DisciplineRecord::getBallot).sum());
                detail.setTotal(detail.getPushTotal() + detail.getRewardTotal());
                detailBOs.add(detail);
            }
        return detailBOs;
    }

    @Override
    public List<DisciplineRecordQuantityBO> disciplineQuantityCollect(CollectFilterTO to) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("area=asc");
        dto.getSorts().add("project=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto);
        List<DisciplineRecordQuantityBO> quantityBOs = new ArrayList<>(0);
        String area = "", project = "";
        for (DisciplineRecord entity : list)
            if (!entity.getArea().equals(area) || !entity.getProject().equals(project)) {
                project = entity.getProject();
                area = entity.getArea();
                DisciplineRecordQuantityBO quantity = new DisciplineRecordQuantityBO();
                quantity.setStart(to.getStart());
                quantity.setEnd(to.getEnd());
                quantity.setDepartment(entity.getProject());
                quantity.setArea(entity.getArea());
                quantity.setReward(list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()) && d.getStatus() == Boolean.TRUE)
                        .collect(Collectors.toList()).size());
                quantity.setPush(list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()) && d.getStatus() == Boolean.FALSE)
                        .collect(Collectors.toList()).size());
                quantity.setTotal(list.stream()
                        .filter(d -> d.getProject().equals(entity.getProject()))
                        .mapToDouble(DisciplineRecord::getBallot).sum());
                quantityBOs.add(quantity);
            }
        return quantityBOs;
    }

    @Override
    public List<DisciplineRecordScoreBO> disciplineScoreCollect(CollectFilterTO to) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("area=asc");
        dto.getSorts().add("project=asc");
        dto.getSorts().add("username=asc");
        dto.getSorts().add("name=asc");
        List<DisciplineRecord> list = this.getListByFilter(to, dto);
        List<DisciplineRecordScoreBO> scoreBOs = new ArrayList<>(0);
        String area = "", project = "", username = "", target = "";
        for (DisciplineRecord entity : list)
            if (!entity.getArea().equals(area) || !entity.getProject().equals(project)
                    || !entity.getUsername().equals(username) || !entity.getName().equals(target)) {
                area = entity.getArea();
                project = entity.getProject();
                username = entity.getUsername();
                target = entity.getName();
                DisciplineRecordScoreBO score = BeanTransform.copyProperties(entity, DisciplineRecordScoreBO.class, true);
                score.setPush(0);
                score.setPushTotal(0d);
                score.setReward(0);
                score.setRewardTotal(0d);
                for (DisciplineRecord reward : list)
                    if (entity.getArea().equals(reward.getArea()) && entity.getName().equals(reward.getName())
                            && entity.getProject().equals(reward.getProject()) && entity.getStatus() == Boolean.TRUE
                            && entity.getUsername().equals(reward.getUsername())) {
                        score.setReward(score.getReward() + 1);
                        score.setRewardTotal(score.getRewardTotal() + reward.getBallot());
                    }
                for (DisciplineRecord push : list)
                    if (entity.getArea().equals(push.getArea()) && entity.getName().equals(push.getName())
                            && entity.getProject().equals(push.getProject()) && entity.getStatus().equals(Boolean.TRUE)
                            && entity.getUsername().equals(push.getUsername())) {
                        score.setPush(score.getPush() + 1);
                        score.setPushTotal(score.getPushTotal() + push.getBallot());
                    }
                score.setName(target);
                score.setArea(area);
                score.setProject(project);
                score.setUsername(username);
                score.setTotal(score.getPushTotal() + score.getRewardTotal());
                scoreBOs.add(score);
            }
        return scoreBOs;
    }

    /**
     * 根据过滤条件传获取数据
     *
     * @param to 过滤条件传输对象
     * @return
     * @throws SerException
     */
    private List<DisciplineRecord> getListByFilter(CollectFilterTO to, DisciplineRecordDTO dto) throws SerException {
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
        return super.findByCis(dto);
    }

    @Override
    public List<DisciplineRecordBO> findByFilter(CollectFilterTO to) throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getSorts().add("occurrence=desc");
        return BeanTransform.copyProperties(this.getListByFilter(to, dto), DisciplineRecordBO.class);
    }

    @Override
    public List<DisciplineRecordBO> rewardMaps(DisciplineRecordDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("status", !Boolean.TRUE));
        return BeanTransform.copyProperties(super.findByPage(dto), DisciplineRecordBO.class);
    }

    @Override
    public List<DisciplineRecordBO> pushMaps(DisciplineRecordDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("status", !Boolean.FALSE));
        return BeanTransform.copyProperties(super.findByPage(dto), DisciplineRecordBO.class);
    }

    @Override
    public DisciplineRecordBO getById(String id) throws SerException {
        DisciplineRecord entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, DisciplineRecordBO.class);
    }

    @Override
    public Long getRewardTotal() throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getConditions().add(Restrict.eq("status", !Boolean.TRUE));
        return super.count(dto);
    }

    @Override
    public Long getPushTotal() throws SerException {
        DisciplineRecordDTO dto = new DisciplineRecordDTO();
        dto.getConditions().add(Restrict.eq("status", !Boolean.FALSE));
        return super.count(dto);
    }
}