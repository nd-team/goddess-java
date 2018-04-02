package com.bjike.goddess.abilitydisplay.api;

import com.bjike.goddess.abilitydisplay.bo.SummaryBO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface SummaryAPI {

    List<SummaryBO> getSum(String date) throws SerException;

}