package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.ConstructTeam;
import com.bjike.goddess.enterpriseculturemanage.entity.EnterpriseCultureInfo;
import com.bjike.goddess.enterpriseculturemanage.entity.PeriodicalProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.entity.PublicizeProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.enums.UpdateType;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoEditTO;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 企业文化信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "enterpriseculturemanageSerCache")
@Service
public class EnterpriseCultureInfoSerImpl extends ServiceImpl<EnterpriseCultureInfo, EnterpriseCultureInfoDTO> implements EnterpriseCultureInfoSer {

    @Autowired
    private PublicizeProgramInfoSer publicizeProgramInfoSer;
    @Autowired
    private PeriodicalProgramInfoSer periodicalProgramInfoSer;
    @Autowired
    private ConstructTeamSer constructTeamSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EnterpriseCultureInfoBO insertModel(EnterpriseCultureInfoTO to) throws SerException {

        onOfTeam();
        //需要检查theme是否与解冻状态记录存在相同
        EnterpriseCultureInfoDTO dto = new EnterpriseCultureInfoDTO();
        dto.getConditions().add(Restrict.eq("theme", to.getTheme()));
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<EnterpriseCultureInfo> infoList = super.findByCis(dto);
        if (infoList != null && !infoList.isEmpty()) {
            throw new SerException("主题已经存在!");
        }
        EnterpriseCultureInfo model = BeanTransform.copyProperties(to, EnterpriseCultureInfo.class);
        model.setStatus(Status.THAW);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, EnterpriseCultureInfoBO.class);
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EnterpriseCultureInfoBO updateModel(EnterpriseCultureInfoEditTO to) throws SerException {
        onOfTeam();
        EnterpriseCultureInfo newmodel = super.findById(to.getId());
        if (newmodel != null) {
            //覆盖相当于盘普通编辑，保留即copy新增旧记录并且冻结状态
            if (to.getUpdateType() == UpdateType.RESERVE) {
                EnterpriseCultureInfo oldModel = new EnterpriseCultureInfo();
                BeanUtils.copyProperties(newmodel, oldModel);
                oldModel.setId(null);
                oldModel.setStatus(Status.CONGEAL);
                super.save(oldModel);
            }
            BeanTransform.copyProperties(to, newmodel, true);
            newmodel.setModifyTime(LocalDateTime.now());
            super.update(newmodel);
            return BeanTransform.copyProperties(to, EnterpriseCultureInfoBO.class);
        } else {
            throw new SerException("更新对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<EnterpriseCultureInfoBO> pageList(EnterpriseCultureInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<EnterpriseCultureInfo> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, EnterpriseCultureInfoBO.class);
    }

    @Override
    public PublicizeProgramInfoBO findPublicize(String id) throws SerException {
        EnterpriseCultureInfo model = super.findById(id);
        if (model != null) {
            PublicizeProgramInfo publicizeProgramInfo = publicizeProgramInfoSer.findById(model.getId());
            if (publicizeProgramInfo != null) {
                return BeanTransform.copyProperties(publicizeProgramInfo, PublicizeProgramInfoBO.class);
            }
        } else {
            throw new SerException("非法Id,企业文化对象不存在!");
        }
        return new PublicizeProgramInfoBO();
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PeriodicalProgramInfoBO findPeriodical(String id) throws SerException {
        EnterpriseCultureInfo model = super.findById(id);
        if (model != null) {
            PeriodicalProgramInfoDTO programInfoDTO = new PeriodicalProgramInfoDTO();
            programInfoDTO.getConditions().add(Restrict.eq("infoId", model.getId()));
            PeriodicalProgramInfo publicizeProgramInfo = periodicalProgramInfoSer.findOne(programInfoDTO);
            if (publicizeProgramInfo != null) {
                return BeanTransform.copyProperties(publicizeProgramInfo, PeriodicalProgramInfoBO.class);
            }
        } else {
            throw new SerException("非法Id,企业文化对象不存在!");
        }
        return new PeriodicalProgramInfoBO();
    }

    @Override
    public List<EnterpriseCultureInfoBO> findThawAll() throws SerException {
        EnterpriseCultureInfoDTO dto = new EnterpriseCultureInfoDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<EnterpriseCultureInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, EnterpriseCultureInfoBO.class);
    }
}