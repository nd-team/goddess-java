package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.MarketSesponseBO;
import com.bjike.goddess.businessevaluate.dto.MarketSesponseDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.MarketSesponse;
import com.bjike.goddess.businessevaluate.to.MarketSesponseTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场反应和创新能力业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class MarketSesponseSerImpl extends ServiceImpl<MarketSesponse, MarketSesponseDTO> implements MarketSesponseSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MarketSesponseBO addModel(MarketSesponseTO to) throws SerException {
        MarketSesponse model = BeanTransform.copyProperties(to, MarketSesponse.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, MarketSesponseBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MarketSesponseBO updateModel(MarketSesponseTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            MarketSesponse model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, MarketSesponseBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MarketSesponseBO> pageList(MarketSesponseDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<MarketSesponse> list = super.findByPage(dto);
        List<MarketSesponseBO> boList = BeanTransform.copyProperties(list, MarketSesponseBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            for (MarketSesponseBO bo : boList) {
                EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectInfoId());
                if (info != null) {
                    bo.setArea(info.getArea());
                    bo.setProject(info.getProject());
                    bo.setStartTime(info.getStartTime().toString());
                    bo.setEndTime(info.getEndTime().toString());
                }
            }
        }
        return boList;
    }
}