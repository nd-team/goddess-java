package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.rotation.bo.RecommendRotationBO;
import com.bjike.goddess.rotation.dto.RecommendRotationDTO;
import com.bjike.goddess.rotation.entity.RecommendRotation;
import com.bjike.goddess.rotation.enums.AuditType;
import com.bjike.goddess.rotation.to.RecommendRotationTO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位轮换推荐业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:28 ]
 * @Description: [ 岗位轮换推荐业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class RecommendRotationSerImpl extends ServiceImpl<RecommendRotation, RecommendRotationDTO> implements RecommendRotationSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private RegularizationAPI regularizationAPI;


    @Autowired
    private SubsidyStandardSer subsidyStandardSer;

    private RecommendRotationBO transformBO(RecommendRotation entity) throws SerException {
        RecommendRotationBO bo = BeanTransform.copyProperties(entity, RecommendRotation.class);
        UserBO user = userAPI.findByUsername(entity.getUsername());
        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        dto.getConditions().add(Restrict.eq(USERNAME, entity.getUsername()));
        List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.listEntryBasicInfo(dto);
        RegularizationDTO regularizationDTO = new RegularizationDTO();
        regularizationDTO.getConditions().add(Restrict.eq("name", entity.getUsername()));
        List<RegularizationBO> regularizationBOs = regularizationAPI.list(regularizationDTO);
        if (entryBasicInfoBOs.size() > 0) {
            EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
            bo.setEntryTime(entryBasicInfoBO.getEntryTime());
        }
        if (regularizationBOs.size() > 0) {
            RegularizationBO regularizationBO = regularizationBOs.get(0);
            bo.setEntryTime(regularizationBO.getHiredate());
            bo.setRegularTime(regularizationBO.getPositiveDate());
        }
        if (null != entity.getApplyLevel()) {
            bo.setApplyLevelId(entity.getApplyLevel().getId());
            bo.setApplyLevelArrangement(entity.getApplyLevel().getArrangement());
        }
        if (null != entity.getRotationLevel()) {
            bo.setRotationLevelId(entity.getRotationLevel().getId());
            bo.setRotationLevelArrangement(entity.getRotationLevel().getArrangement());
        }
        return bo;
    }

    private List<RecommendRotationBO> transformBOList(List<RecommendRotation> list) throws SerException {
        List<RecommendRotationBO> bos = new ArrayList<>(0);
        for (RecommendRotation entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public RecommendRotationBO save(RecommendRotationTO to) throws SerException {
        RecommendRotation entity = BeanTransform.copyProperties(to, RecommendRotation.class, true);
        List<PositionDetailBO> bos = positionDetailUserAPI.findPositionByUser(userAPI.findByUsername(to.getUsername()).getId()).stream()
                .sorted(Comparator.comparing(PositionDetailBO::getArea)
                        .thenComparing(PositionDetailBO::getDepartmentId))
                .collect(Collectors.toList());
        StringBuilder area = new StringBuilder(), department = new StringBuilder(), position = new StringBuilder(), arrangement = new StringBuilder();
        String tempArea = "", tempDepartment = "", tempArrangement = "";
        for (PositionDetailBO positionDetailBO : bos) {
            if (!tempArea.equals(positionDetailBO.getArea())) {
                tempArea = positionDetailBO.getArea();
                area.append(tempArea + ",");
            }
            if (!tempDepartment.equals(positionDetailBO.getDepartmentName())) {
                tempDepartment = positionDetailBO.getDepartmentName();
                department.append(tempDepartment + ",");
            }
            position.append(positionDetailBO.getPosition());
        }
        for (String s : bos.stream()
                .sorted(Comparator.comparing(PositionDetailBO::getArrangementName))
                .map(PositionDetailBO::getArrangementName).collect(Collectors.toList()))
            if (!tempArrangement.equals(s)) {
                tempArrangement = s;
                arrangement.append(s);
            }

        entity.setRecommend(userAPI.currentUser().getUsername());
        entity.setRecommendTime(LocalDate.now());
        entity.setArea(area.toString());
        entity.setPosition(position.toString());
        entity.setArrangement(arrangement.toString());
        entity.setDepartment(department.toString());
        entity.setAudit(AuditType.NONE);
        entity.setApplyLevel(subsidyStandardSer.findById(to.getApplyLevelId()));
        if (null == entity.getApplyLevel())
            throw new SerException("推荐的层级不存在");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public RecommendRotationBO update(RecommendRotationTO to) throws SerException {
        RecommendRotation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        UserBO user = userAPI.currentUser();
        if (user.getUsername().equals(entity.getRecommend()))
            throw new SerException("不能修改他人的轮换推荐");
        BeanTransform.copyProperties(to, entity, true);
        List<PositionDetailBO> bos = positionDetailUserAPI.findPositionByUser(userAPI.findByUsername(to.getUsername()).getId()).stream()
                .sorted(Comparator.comparing(PositionDetailBO::getArea)
                        .thenComparing(PositionDetailBO::getDepartmentId))
                .collect(Collectors.toList());
        StringBuilder area = new StringBuilder(), department = new StringBuilder(), position = new StringBuilder(), arrangement = new StringBuilder();
        String tempArea = "", tempDepartment = "", tempArrangement = "";
        for (PositionDetailBO positionDetailBO : bos) {
            if (!tempArea.equals(positionDetailBO.getArea())) {
                tempArea = positionDetailBO.getArea();
                area.append(tempArea + ",");
            }
            if (!tempDepartment.equals(positionDetailBO.getDepartmentName())) {
                tempDepartment = positionDetailBO.getDepartmentName();
                department.append(tempDepartment + ",");
            }
            position.append(positionDetailBO.getPosition());
        }
        for (String s : bos.stream()
                .sorted(Comparator.comparing(PositionDetailBO::getArrangementName))
                .map(PositionDetailBO::getArrangementName).collect(Collectors.toList()))
            if (!tempArrangement.equals(s)) {
                tempArrangement = s;
                arrangement.append(s);
            }

        entity.setRecommend(userAPI.currentUser().getUsername());
        entity.setRecommendTime(LocalDate.now());
        entity.setArea(area.toString());
        entity.setPosition(position.toString());
        entity.setArrangement(arrangement.toString());
        entity.setDepartment(department.toString());
        entity.setModifyTime(LocalDateTime.now());
        entity.setApplyLevel(subsidyStandardSer.findById(to.getApplyLevelId()));
        if (null == entity.getApplyLevel())
            throw new SerException("推荐的层级不存在");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public RecommendRotationBO delete(String id) throws SerException {
        RecommendRotation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public RecommendRotationBO opinion(RecommendRotationTO to) throws SerException {
        RecommendRotation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setGeneral(userAPI.currentUser().getUsername());
        entity.setAudit(to.getPass() ? AuditType.ALLOWED : AuditType.DENIED);
        entity.setRotationLevel(subsidyStandardSer.findById(to.getRotationLevelId()));
        if (to.getPass() && null == entity.getRotationLevel())
            throw new SerException("选择的层级不存在");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public RecommendRotationBO getById(String id) throws SerException {
        RecommendRotation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public List<RecommendRotationBO> maps(RecommendRotationDTO dto) throws SerException {
        dto.getSorts().add("audit=asc");
        dto.getSorts().add("rotationDate=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        RecommendRotationDTO dto = new RecommendRotationDTO();
        return super.count(dto);
    }

    @Override
    public List<RecommendRotationBO> findByUserArrangement(String username, String arrangementId) throws SerException {
        RecommendRotationDTO dto = new RecommendRotationDTO();
        dto.getConditions().add(Restrict.eq(USERNAME, username));
        dto.getConditions().add(Restrict.eq("audit", AuditType.ALLOWED));
        dto.getConditions().add(Restrict.eq("rotationLevel.id", arrangementId));
        dto.getSorts().add("rotationDate=desc");
        return this.transformBOList(super.findByCis(dto));
    }
}