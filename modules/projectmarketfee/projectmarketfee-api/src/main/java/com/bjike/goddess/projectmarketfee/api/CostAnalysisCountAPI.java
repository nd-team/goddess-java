package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisCountTO;
import com.bjike.goddess.projectmarketfee.to.GuidePermissionTO;

/**
 * 费用效益分析业务汇总业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 03:13 ]
 * @Description: [ 费用效益分析业务汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostAnalysisCountAPI {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 添加
     *
     * @param to to
     * @return class CostAnalysisCountBO
     * @throws SerException
     */
    default CostAnalysisCountBO save(CostAnalysisCountTO to) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class CostAnalysisCountBO
     * @throws SerException
     */
    default CostAnalysisCountBO findByID(String id) throws SerException {
        return null;
    }
}