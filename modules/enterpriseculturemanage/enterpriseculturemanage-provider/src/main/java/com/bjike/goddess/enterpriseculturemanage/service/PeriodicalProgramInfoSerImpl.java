package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.ConstructTeam;
import com.bjike.goddess.enterpriseculturemanage.entity.EnterpriseCultureInfo;
import com.bjike.goddess.enterpriseculturemanage.entity.PeriodicalProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.to.PeriodicalProgramInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 刊物方案信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-01 09:07 ]
 * @Description: [ 刊物方案信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "enterpriseculturemanageSerCache")
@Service
public class PeriodicalProgramInfoSerImpl extends ServiceImpl<PeriodicalProgramInfo, PeriodicalProgramInfoDTO> implements PeriodicalProgramInfoSer {

    @Autowired
    private EnterpriseCultureInfoSer enterpriseCultureInfoSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ConstructTeamSer constructTeamSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PeriodicalProgramInfoBO insertModel(PeriodicalProgramInfoTO to) throws SerException {
        onOfTeam();

        EnterpriseCultureInfo info = enterpriseCultureInfoSer.findById(to.getInfoId());
        if (info != null) {
            PeriodicalProgramInfo model = BeanTransform.copyProperties(to, PeriodicalProgramInfo.class, true);
            if (isExist(model)) {
                throw new SerException("该主题的宣传方案信息已存在!");
            }
            model.setTheme(info.getTheme());
            model.setAuditResult(AuditResult.NOTDEAL);
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(to, PeriodicalProgramInfoBO.class);
        } else {
            throw new SerException("非法企业文化信息Id,企业文化信息对象不能为空!");
        }
    }

    //检查当前用户是否为建设小组人员
    public void onOfTeam() throws SerException {

        UserBO userBO = userAPI.currentUser();

        List<ConstructTeam> teamList = constructTeamSer.findAll();
        if (!CollectionUtils.isEmpty(teamList)) {
            List<String> users = new ArrayList<String>();
            for (ConstructTeam team : teamList) {
                users.add(team.getUserNumber());
            }
            if (!users.contains(userBO.getEmployeeNumber())) {
                throw new SerException("只有建设小组可以管理公司文化!");
            }
        } else {
            throw new SerException("请先添加建设小组人员!");
        }
    }

    public Boolean isExist(PeriodicalProgramInfo model) throws SerException {

        PeriodicalProgramInfoDTO dto = new PeriodicalProgramInfoDTO();
        dto.getConditions().add(Restrict.eq("infoId", model.getInfoId()));
        dto.setLimit(1);
        List<PeriodicalProgramInfo> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            if (StringUtils.isEmpty(model.getId())) {
                return true;
            } else {
                if (!model.getId().equals(list.get(0).getId())) {
                    return true;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PeriodicalProgramInfoBO updateModel(PeriodicalProgramInfoTO to) throws SerException {
        onOfTeam();
        updateModule(to);
        return BeanTransform.copyProperties(to, PeriodicalProgramInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, AuditResult auditResult, String auditSuggestion) throws SerException {
        PeriodicalProgramInfo model = super.findById(id);
        if (model != null) {
            model.setAuditResult(auditResult);
            super.update(model);
        } else {
            throw new SerException("非法Id,刊物方案信息对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<PeriodicalProgramInfoBO> pageList(PeriodicalProgramInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<PeriodicalProgramInfo> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, PeriodicalProgramInfoBO.class);
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 刊物方案信息
     */
    public void updateModule(PeriodicalProgramInfoTO to) throws SerException {
        EnterpriseCultureInfo info = enterpriseCultureInfoSer.findById(to.getInfoId());
        if (info != null) {
            PeriodicalProgramInfo model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                if (isExist(model)) {
                    throw new SerException("该主题的宣传方案信息已存在!");
                }
                model.setTheme(info.getTheme());
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("非法Id,刊物方案信息对象不能为空!");
            }
        } else {
            throw new SerException("非法企业文化信息Id,企业文化信息对象不能为空!");
        }

    }
}