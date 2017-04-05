package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.EnterpriseCultureInfo;
import com.bjike.goddess.enterpriseculturemanage.entity.PeriodicalProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.to.PeriodicalProgramInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PeriodicalProgramInfoBO insertModel(PeriodicalProgramInfoTO to) throws SerException {

        PeriodicalProgramInfo model = BeanTransform.copyProperties(to, PeriodicalProgramInfo.class, true);
        model.setAuditResult(AuditResult.NOTDEAL);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, PeriodicalProgramInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PeriodicalProgramInfoBO updateModel(PeriodicalProgramInfoTO to) throws SerException {
        updateModule(to);
        return BeanTransform.copyProperties(to, PeriodicalProgramInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(PeriodicalProgramInfoTO to) throws SerException {
        updateModule(to);
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

        if (!StringUtils.isEmpty(to.getId())) {
            PeriodicalProgramInfo model = super.findById(to.getId());
            if (model != null) {
                if(!model.getInfoId().equals(to.getInfoId())){
                    //设置企业文化信息关联
                    EnterpriseCultureInfo oldInfo = enterpriseCultureInfoSer.findById(model.getInfoId());
                    oldInfo.setPublicizeId(null);
                    enterpriseCultureInfoSer.update(oldInfo);

                    EnterpriseCultureInfo newInfo = enterpriseCultureInfoSer.findById(to.getInfoId());
                    newInfo.setPublicizeId(model.getId());
                    enterpriseCultureInfoSer.update(newInfo);
                }
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }
}