package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualApplyBO;
import com.bjike.goddess.annual.bo.AnnualInfoBO;
import com.bjike.goddess.annual.dto.AnnualApplyDTO;
import com.bjike.goddess.annual.entity.AnnualApply;
import com.bjike.goddess.annual.entity.AnnualInfo;
import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.annual.to.AnnualApplyAuditTo;
import com.bjike.goddess.annual.to.AnnualApplyTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 年假申请业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "annualSerCache")
@Service
public class AnnualApplySerImpl extends ServiceImpl<AnnualApply, AnnualApplyDTO> implements AnnualApplySer {

    @Autowired
    private AnnualInfoSer annualInfoSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionAPI positionAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;

    /**
     * 转换年假申请传输对象
     *
     * @param entity 年假申请实体数据
     * @return
     */
    private AnnualApplyBO transformBO(AnnualApply entity) {
        AnnualApplyBO bo = BeanTransform.copyProperties(entity, AnnualApplyBO.class, true);
        AnnualInfo info = entity.getInfo();
        if (null != info) {
            bo.setInfo_id(info.getId());
            bo.setInfoUsername(info.getUsername());
        }
        return bo;
    }

    /**
     * 转换年假申请传输对象集合
     *
     * @param list 年假申请实体数据集合
     * @return
     */
    private List<AnnualApplyBO> transformBOList(List<AnnualApply> list) {
        List<AnnualApplyBO> bos = new ArrayList<>(list.size());
        for (AnnualApply entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    /**
     * 计算请节哀时间
     *
     * @param entity 年假申请实体数据
     * @return
     */
    private Double countLeave(AnnualApply entity) {
        long hour = entity.getStartTime().until(entity.getEndTime(), ChronoUnit.HOURS);
        //@TODO 计算请假时间
        return 0d;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualApplyBO save(AnnualApplyTO to) throws SerException {
        AnnualApply entity = BeanTransform.copyProperties(to, AnnualApply.class, true);
        entity.setAudit(AuditType.NONE);
        entity.setInfo(annualInfoSer.findById(to.getInfo_id()));
        entity.setLeave(this.countLeave(entity));
        if (entity.getLeave() > entity.getInfo().getSurplus())
            throw new SerException("请不要超出剩余年假数");
        UserBO userBO = userAPI.currentUser();
        if (!userBO.getUsername().equals(entity.getInfo().getUsername()))
            throw new SerException("请不要替他人提交年假申请");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualApplyBO delete(AnnualApplyTO to) throws SerException {
        AnnualApply entity = super.findById(to.getId());
        UserBO userBO = userAPI.currentUser();
        if (!userBO.getUsername().equals(entity.getInfo().getUsername()))
            throw new SerException("请不要对他人的年假申请做处理");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualApplyBO audit(AnnualApplyAuditTo to) throws SerException {
        AnnualApply entity = super.findById(to.getId());
        UserBO auditor = userAPI.currentUser(), user = userAPI.findByUsername(entity.getInfo().getUsername());
        UserDetailBO auditorDetailBO = userDetailAPI.findByUserId(auditor.getId()), userDetailBO = userDetailAPI.findByUserId(user.getId());
        List<PositionBO> positionBOs = positionAPI.findChild(auditorDetailBO.getPositionId());
        boolean adopt = false;
        for (PositionBO position : positionBOs)
            if (position.getId().equals(userDetailBO.getPositionId())) {
                adopt = true;
                break;
            }
        if (!adopt)
            return null;
        entity.setAuditTime(LocalDateTime.now());
        entity.setAuditor(auditor.getUsername());
        entity.setAudit(AuditType.DENIED);
        if (to.isFruit()) {
            entity.setAudit(AuditType.ALLOWED);
            //通过则修改年假信息
            entity.getInfo().setSurplus(entity.getInfo().getSurplus() - entity.getLeave());
            entity.getInfo().isAlready(Boolean.TRUE);
            annualInfoSer.update(entity.getInfo());
        }
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<AnnualApplyBO> findByUsername(String username) throws SerException {
        List<AnnualInfoBO> infoBOList = annualInfoSer.findByUsername(username);
        AnnualApplyDTO dto = new AnnualApplyDTO();
        dto.getConditions().add(Restrict.in("info.id", infoBOList.stream().map(AnnualInfoBO::getId).collect(Collectors.toList()).toArray(new String[0])));
        List<AnnualApply> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<AnnualApplyBO> findByInfo(String info_id) throws SerException {
        AnnualApplyDTO dto = new AnnualApplyDTO();
        dto.getConditions().add(Restrict.eq("info.id", info_id));
        List<AnnualApply> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<AnnualApplyBO> maps(AnnualApplyDTO dto) throws SerException {
        dto.getSorts().add("startTime");
        dto.getSorts().add("info.id");
        List<AnnualApply> list = super.findByPage(dto);
        return this.transformBOList(list);
    }
}