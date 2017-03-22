package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.OilCardReceiveBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.entity.OilCardReceive;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;
import com.bjike.goddess.oilcardmanage.enums.OilCardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 油卡领用业务处理类
 * @Author: [Jason]
 * @Date: [17-3-14 下午5:13]
 * @Package:[ com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class OilCardReceiveSerImpl extends ServiceImpl<OilCardReceive,OilCardReceiveDTO> implements OilCardReceiveSer {

    @Autowired
    private OilCardBasicSer oilCardBasicSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardReceiveBO saveOilCardReceive(OilCardReceiveTO to) throws SerException {

        if(!StringUtils.isEmpty(to.getOilCardBasicId())){
            //油卡
            OilCardBasic oilCardBasic = oilCardBasicSer.findById(to.getOilCardBasicId());

            if(oilCardBasic != null ){
                OilCardReceive model = BeanTransform.copyProperties(to, OilCardReceive.class ,true);
                super.save(model);
                to.setId(model.getId());

                //设置油卡状态-正在使用
                oilCardBasic.setCardStatus(OilCardStatus.USE);
                oilCardBasic.setModifyTime(LocalDateTime.now());
                oilCardBasicSer.update(oilCardBasic);
                return BeanTransform.copyProperties(to,OilCardReceiveBO.class);
            }else{
                throw new SerException("油卡不存在!");
            }

        }else{
            throw new SerException("油卡基本信息ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardReceiveBO updateOilCardReceive(OilCardReceiveTO to) throws SerException {
        updateModel(to);
        return BeanTransform.copyProperties(to,OilCardReceiveBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardReceiveBO auditOilCardReceive(OilCardReceiveTO to) throws SerException {
        updateModel(to);
        return BeanTransform.copyProperties(to,OilCardReceiveBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void returnOilCardReceive(String id) throws SerException {
        OilCardReceive model = super.findById(id);
        if(model != null ){
            OilCardBasic oilCardBasic = oilCardBasicSer.findById(model.getOilCardBasicId());
            //设置油卡状态-闲置
            if(oilCardBasic != null ){
                oilCardBasic.setCardStatus(OilCardStatus.IDLE);
                oilCardBasic.setModifyTime(LocalDateTime.now());
            }else{
                throw new SerException("油卡记录不存在!");
            }
            oilCardBasicSer.update(oilCardBasic);
        }else{
            throw new SerException("油卡领用ID对应记录不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<OilCardReceiveBO> pageList(OilCardReceiveDTO dto) throws SerException {

        List<OilCardReceive> list = super.findByPage(dto);

        return BeanTransform.copyProperties(list,OilCardReceiveBO.class);
    }

    /**
     * 更新数据（编辑、审核）
     * @param to 油卡基本信息
     * @throws SerException 更新油卡异常
     */
    public void updateModel(OilCardReceiveTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            OilCardReceive model = super.findById(to.getId());
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
