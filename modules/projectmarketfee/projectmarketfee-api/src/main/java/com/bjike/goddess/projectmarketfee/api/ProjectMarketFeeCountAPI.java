package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeCountBO;
import com.bjike.goddess.projectmarketfee.to.ProjectMarketFeeCountTO;

/**
 * 项目前期的市场活动费汇总业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 01:50 ]
 * @Description: [ 项目前期的市场活动费汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectMarketFeeCountAPI {
    /**
     * 添加
     *
     * @param to 项目前期的市场活动费汇总信息
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default ProjectMarketFeeCountBO save(ProjectMarketFeeCountTO to) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default ProjectMarketFeeCountBO findByID(String id) throws SerException {
        return null;
    }
}