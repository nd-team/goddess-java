package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 油卡基础信息业务处理类
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:42]
 * @Package:[ com.bjike.goddess.com.bjike.goddess.com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "oilCardBasicSerImplCache")
@Service
public class OilCardBasicSerImpl extends ServiceImpl<OilCardBasic,OilCardBasicDTO> implements OilCardBasicSer {


    @Transactional(rollbackFor = SerException.class)
    @Override
    public OilCardBasicBO saveOilCarBasic(OilCardBasicTO to) throws SerException {

        if(to.getCycleEarlyMoney() != null ){
            to.setBalance(to.getCycleEarlyMoney());
        }else{
            throw new SerException("期初金额不能为空!");
        }
        OilCardBasic model = BeanTransform.copyProperties(to, OilCardBasic.class ,true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to,OilCardBasicBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OilCardBasicBO updateOilCardBasic(OilCardBasicTO to) throws SerException {
        updateModel(to);
        return BeanTransform.copyProperties(to,OilCardBasicBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void freezeOilCardBasic(String id) throws SerException {
        OilCardBasic model = super.findById(id);
        if(model != null){
            model.setStatus(Status.CONGEAL);
        }else{
            throw new SerException("冻结记录对象不能为空!");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void breakFreeze(String id) throws SerException {
        OilCardBasic model = super.findById(id);
        if(model != null){
            model.setStatus(Status.THAW);
        }else{
            throw new SerException("解冻记录对象不能为空!");
        }
    }

    @Override
    public List<OilCardBasicBO> pageList(OilCardBasicDTO dto) throws SerException {

        List<OilCardBasic> list = super.findByPage(dto);

        return BeanTransform.copyProperties(list,OilCardBasicBO.class);
    }

    /**
     * 更新数据（编辑、审核）
     * @param to 油卡基本信息
     * @throws SerException 更新油卡异常
     */
    public void updateModel(OilCardBasicTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            OilCardBasic model = super.findById(to.getId());
            if(model != null){
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            }else{
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }
}
