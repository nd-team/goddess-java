package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.AnotherCostBO;
import com.bjike.goddess.businessevaluate.dto.AnotherCostDTO;
import com.bjike.goddess.businessevaluate.entity.AnotherCost;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.to.AnotherCostTO;
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
 * 其它成本业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class AnotherCostSerImpl extends ServiceImpl<AnotherCost, AnotherCostDTO> implements AnotherCostSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public AnotherCostBO insertModel(AnotherCostTO to) throws SerException {
        AnotherCost model = BeanTransform.copyProperties(to, AnotherCost.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, AnotherCostBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public AnotherCostBO updateModel(AnotherCostTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            AnotherCost model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, AnotherCostBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AnotherCostBO> pageList(AnotherCostDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<AnotherCost> list = super.findByPage(dto);
        List<AnotherCostBO> boList = BeanTransform.copyProperties(list, AnotherCostBO.class);
        //设置项目信息
        for (AnotherCostBO bo : boList) {
            EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectInfoId());
            if (info != null) {
                bo.setArea(info.getArea());
                bo.setProject(info.getProject());
                bo.setStartTime(info.getStartTime().toString());
                bo.setEndTime(info.getEndTime().toString());
            }
        }
        return boList;
    }
}