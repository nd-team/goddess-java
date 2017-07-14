package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.enums.OilCardStatus;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 油卡基础信息业务处理类
 *
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:42]
 * @Package:[ com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "oilCardBasicSerImplCache")
@Service
public class OilCardBasicSerImpl extends ServiceImpl<OilCardBasic, OilCardBasicDTO> implements OilCardBasicSer {


    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardBasicBO saveOilCarBasic(OilCardBasicTO to) throws SerException {
        OilCardBasic model = BeanTransform.copyProperties(to, OilCardBasic.class, true);
        model.setCardStatus(OilCardStatus.IDLE);
        model.setBalance(model.getCycleEarlyMoney());
        super.save(model);
        return BeanTransform.copyProperties(model, OilCardBasicBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardBasicBO updateOilCardBasic(OilCardBasicTO to) throws SerException {
        OilCardBasic model = super.findById(to.getId());
        if (model != null) {
            updateModel(to);
            return BeanTransform.copyProperties(to, OilCardBasicBO.class);
        } else {
            throw new SerException("非法Id,油卡信息对象不能为空!");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void freezeOilCardBasic(String id) throws SerException {
        OilCardBasic model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setStatus(Status.CONGEAL);
            } else {
                throw new SerException("该记录无需重复冻结!");
            }
        } else {
            throw new SerException("非法ID,油卡信息对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        OilCardBasic model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("该记录无需重复解冻!");
            }
        } else {
            throw new SerException("非法ID,油卡信息对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<OilCardBasicBO> pageList(OilCardBasicDTO dto) throws SerException {
        if (dto.getStatus() != null) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        List<OilCardBasic> list = super.findByPage(dto);

        return BeanTransform.copyProperties(list, OilCardBasicBO.class);
    }

    @Override
    public OilCardBasicBO findByCode(String oilCardCode) throws SerException {
        OilCardBasicDTO dto = new OilCardBasicDTO();
        dto.getConditions().add(Restrict.eq("oilCardCode", oilCardCode));
        return BeanTransform.copyProperties(super.findOne(dto), OilCardBasicBO.class);
    }

    /**
     * 更新数据（编辑、审核）
     *
     * @param to 油卡基本信息
     * @throws SerException 更新油卡异常
     */
    public void updateModel(OilCardBasicTO to) throws SerException {
        OilCardBasic model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("更新对象不能为空");
        }
    }
}
