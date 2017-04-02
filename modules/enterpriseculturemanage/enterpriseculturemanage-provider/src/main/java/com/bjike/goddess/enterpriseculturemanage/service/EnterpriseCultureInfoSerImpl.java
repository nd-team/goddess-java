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
import com.bjike.goddess.enterpriseculturemanage.entity.EnterpriseCultureInfo;
import com.bjike.goddess.enterpriseculturemanage.entity.PeriodicalProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.entity.PublicizeProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.enums.UpdateType;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EnterpriseCultureInfoBO insertModel(EnterpriseCultureInfoTO to) throws SerException {
        //需要检查theme是否与解冻状态记录存在相同
        EnterpriseCultureInfoDTO dto = new EnterpriseCultureInfoDTO();
        if (StringUtils.isEmpty(to.getTheme())) {
            throw new SerException("主题不能为空!");
        }
        dto.getConditions().add(Restrict.eq("theme", to.getTheme()));
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EnterpriseCultureInfoBO updateModel(EnterpriseCultureInfoTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
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
        } else {
            throw new SerException("更新ID不能为空!");
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
        PublicizeProgramInfo publicizeProgramInfo = publicizeProgramInfoSer.findById(id);
        if (publicizeProgramInfo != null) {
            return BeanTransform.copyProperties(publicizeProgramInfo, PublicizeProgramInfoBO.class);
        }
        return new PublicizeProgramInfoBO();
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PeriodicalProgramInfoBO findPeriodical(String id) throws SerException {
        PeriodicalProgramInfo publicizeProgramInfo = periodicalProgramInfoSer.findById(id);
        if (publicizeProgramInfo != null) {
            return BeanTransform.copyProperties(publicizeProgramInfo, PeriodicalProgramInfoBO.class);
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