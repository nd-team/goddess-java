package com.bjike.goddess.abilitydisplay.api;

import com.bjike.goddess.abilitydisplay.bo.SummaryBO;
import com.bjike.goddess.abilitydisplay.service.SummarySer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 能力展示汇总业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-08 02:04 ]
 * @Description: [ 能力展示汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("summaryApiImpl")
public class SummaryApiImpl implements SummaryAPI {

    @Autowired
    private SummarySer summarySer;
    @Override
    public List<SummaryBO> getSum(String date) throws SerException {
        return summarySer.getSum(date);
    }
}