package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.bo.SummaryBO;
import com.bjike.goddess.abilitydisplay.dto.SummaryDTO;
import com.bjike.goddess.abilitydisplay.entity.Summary;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 能力展示汇总业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-08 02:04 ]
 * @Description: [ 能力展示汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SummarySer extends Ser<Summary, SummaryDTO> {


    List<SummaryBO> getSum() throws SerException;

}