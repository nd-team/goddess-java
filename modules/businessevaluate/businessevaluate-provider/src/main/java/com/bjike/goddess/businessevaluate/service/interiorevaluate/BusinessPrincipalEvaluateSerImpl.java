package com.bjike.goddess.businessevaluate.service.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.BusinessPrincipalEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.BusinessPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.interiorevaluate.BusinessPrincipalEvaluate;
import com.bjike.goddess.businessevaluate.service.EvaluateProjectInfoSer;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.BusinessPrincipalEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商务负责人评价业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:34 ]
 * @Description: [ 商务负责人评价业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluate11SerCache")
@Service
public class BusinessPrincipalEvaluateSerImpl extends ServiceImpl<BusinessPrincipalEvaluate, BusinessPrincipalEvaluateDTO> implements BusinessPrincipalEvaluateSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;


    @Override
    public BusinessPrincipalEvaluateBO insertModel(BusinessPrincipalEvaluateTO to) throws SerException {
        BusinessPrincipalEvaluate model = BeanTransform.copyProperties(to, BusinessPrincipalEvaluate.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, BusinessPrincipalEvaluateBO.class);
    }

    @Override
    public BusinessPrincipalEvaluateBO updateModel(BusinessPrincipalEvaluateTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            BusinessPrincipalEvaluate model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, BusinessPrincipalEvaluateBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    public List<BusinessPrincipalEvaluateBO> pageList(BusinessPrincipalEvaluateDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<BusinessPrincipalEvaluate> list = super.findByPage(dto);
        List<BusinessPrincipalEvaluateBO> boList = BeanTransform.copyProperties(list, BusinessPrincipalEvaluateBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            for (BusinessPrincipalEvaluateBO bo : boList) {
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