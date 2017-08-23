package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.RelevancyDetailBO;
import com.bjike.goddess.feedback.dto.RelevancyDetailDTO;
import com.bjike.goddess.feedback.entity.RelevancyDetail;
import com.bjike.goddess.feedback.to.RelevancyDetailTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 各模块关联明细业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:23 ]
 * @Description: [ 各模块关联明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class RelevancyDetailSerImpl extends ServiceImpl<RelevancyDetail, RelevancyDetailDTO> implements RelevancyDetailSer {
    @Override
    public Long count(RelevancyDetailDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public RelevancyDetailBO getOne(String id) throws SerException {
        RelevancyDetail relevancyDetail = super.findById(id);
        return BeanTransform.copyProperties(relevancyDetail, RelevancyDetailBO.class);
    }

    @Override
    public List<RelevancyDetailBO> list(RelevancyDetailDTO dto) throws SerException {
        List<RelevancyDetail> relevancyDetails = super.findByCis(dto);
        List<RelevancyDetailBO> relevancyDetailBOS = BeanTransform.copyProperties(relevancyDetails, RelevancyDetailBO.class);
        return relevancyDetailBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public RelevancyDetailBO insert(RelevancyDetailTO to) throws SerException {
        RelevancyDetail relevancyDetail = BeanTransform.copyProperties(to, RelevancyDetail.class, true);
        relevancyDetail.setCreateTime(LocalDateTime.now());
        super.save(relevancyDetail);
        return BeanTransform.copyProperties(relevancyDetail, RelevancyDetailBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public RelevancyDetailBO edit(RelevancyDetailTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            RelevancyDetail relevancyDetail = super.findById(to.getId());
            relevancyDetail.setModifyTime(LocalDateTime.now());
            super.update(relevancyDetail);
            return BeanTransform.copyProperties(relevancyDetail, RelevancyDetailBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<String> getMainFunction() throws SerException {
        String[] fields = new String[]{"mainFunction"};
        List<RelevancyDetailBO> relevancyDetailBOS = super.findBySql("select mainFunction from feedback_relevancydetail group by mainFunction order by mainFunction asc ", RelevancyDetailBO.class, fields);

        List<String> list = relevancyDetailBOS.stream().map(RelevancyDetailBO::getMainFunction)
                .filter(mainFunction -> (mainFunction != null || !"".equals(mainFunction.trim()))).distinct().collect(Collectors.toList());
        return list;
    }
}