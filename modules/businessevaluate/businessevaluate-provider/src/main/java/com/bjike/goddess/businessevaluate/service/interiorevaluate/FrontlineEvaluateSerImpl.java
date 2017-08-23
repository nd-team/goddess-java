package com.bjike.goddess.businessevaluate.service.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.FrontlineEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.FrontlineEvaluateDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.interiorevaluate.FrontlineEvaluate;
import com.bjike.goddess.businessevaluate.service.EvaluateProjectInfoSer;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.FrontlineEvaluateTO;
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
 * 一线体系评价业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:00 ]
 * @Description: [ 一线体系评价业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class FrontlineEvaluateSerImpl extends ServiceImpl<FrontlineEvaluate, FrontlineEvaluateDTO> implements FrontlineEvaluateSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FrontlineEvaluateBO insertModel(FrontlineEvaluateTO to) throws SerException {
        FrontlineEvaluate model = BeanTransform.copyProperties(to, FrontlineEvaluate.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, FrontlineEvaluateBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FrontlineEvaluateBO updateModel(FrontlineEvaluateTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            FrontlineEvaluate model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, FrontlineEvaluateBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FrontlineEvaluateBO> pageList(FrontlineEvaluateDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<FrontlineEvaluate> list = super.findByPage(dto);
        List<FrontlineEvaluateBO> boList = BeanTransform.copyProperties(list, FrontlineEvaluateBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            for (FrontlineEvaluateBO bo : boList) {
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