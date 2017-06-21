package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ConciseSummaryBO;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.service.ConciseSummarySer;
import com.bjike.goddess.allmeeting.to.ConciseSummaryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简洁交流讨论纪要业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:19 ]
 * @Description: [ 简洁交流讨论纪要业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("conciseSummaryApiImpl")
public class ConciseSummaryApiImpl implements ConciseSummaryAPI {

    @Autowired
    private ConciseSummarySer conciseSummarySer;

    @Override
    public ConciseSummaryBO edit(ConciseSummaryTO to) throws SerException {
        return conciseSummarySer.updateModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        conciseSummarySer.freeze(id);
    }

    @Override
    public List<ConciseSummaryBO> pageList(ConciseSummaryDTO dto) throws SerException {
        return conciseSummarySer.pageList(dto);
    }

    @Override
    public Long count(ConciseSummaryDTO dto) throws SerException {
        return conciseSummarySer.count(dto);
    }

    @Override
    public ConciseSummaryBO findById(String id) throws SerException {
        return conciseSummarySer.findAndSet(id);
    }
}