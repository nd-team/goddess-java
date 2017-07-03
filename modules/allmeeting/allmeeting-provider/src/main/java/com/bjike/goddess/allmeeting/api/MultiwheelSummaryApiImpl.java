package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MultiwheelSummaryBO;
import com.bjike.goddess.allmeeting.bo.OrganizeForSummaryBO;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.dto.MultiwheelSummaryDTO;
import com.bjike.goddess.allmeeting.service.MultiwheelSummarySer;
import com.bjike.goddess.allmeeting.to.MultiwheelSummaryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简洁交流讨论纪要业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("multiwheelSummaryApiImpl")
public class MultiwheelSummaryApiImpl implements MultiwheelSummaryAPI {

    @Autowired
    private MultiwheelSummarySer multiwheelSummarySer;

    @Override
    public MultiwheelSummaryBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(multiwheelSummarySer.findById(id),MultiwheelSummaryBO.class);
    }

    @Override
    public Long count(MultiwheelSummaryDTO dto) throws SerException {
        return multiwheelSummarySer.count(dto);
    }

    @Override
    public MultiwheelSummaryBO edit(MultiwheelSummaryTO to) throws SerException {
        return multiwheelSummarySer.updateModel(to);
    }

    @Override
    public List<MultiwheelSummaryBO> pageList(MultiwheelSummaryDTO dto) throws SerException {
        return multiwheelSummarySer.pageList(dto);
    }

    @Override
    public void freeze(String id) throws SerException {
        multiwheelSummarySer.freeze(id);
    }

    @Override
    public OrganizeForSummaryBO organize(String id) throws SerException {
        return multiwheelSummarySer.organize(id);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        multiwheelSummarySer.unfreeze(id);
    }
}