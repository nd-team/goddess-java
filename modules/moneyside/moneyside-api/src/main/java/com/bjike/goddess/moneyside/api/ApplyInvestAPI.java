package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.ApplyInvestBO;
import com.bjike.goddess.moneyside.bo.CollectApplyInvestBO;
import com.bjike.goddess.moneyside.dto.ApplyInvestDTO;
import com.bjike.goddess.moneyside.to.ApplyInvestTO;
import com.bjike.goddess.moneyside.to.CollectApplyInvestTO;

import java.util.List;

/**
 * 申请投资业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:23 ]
 * @Description: [ 申请投资业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ApplyInvestAPI {
    /**
     * 申请投资总条数
     */
    default Long countApplyInvest(ApplyInvestDTO applyInvestDTO) throws SerException {
        return null;
    }

    /**
     * 一个申请投资
     *
     * @return class ApplyInvestBO
     */
    default ApplyInvestBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 申请投资
     *
     * @param applyInvestDTO 申请投资dto
     * @return class ApplyInvestBO
     * @throws SerException
     */
    default List<ApplyInvestBO> findListApplyInvest(ApplyInvestDTO applyInvestDTO) throws SerException {
        return null;
    }

    /**
     * 添加申请投资
     *
     * @param applyInvestTO 申请投资数据to
     * @return class ApplyInvestBO
     * @throws SerException
     */
    default ApplyInvestBO insertApplyInvest(ApplyInvestTO applyInvestTO) throws SerException {
        return null;
    }

    /**
     * 编辑申请投资
     *
     * @param applyInvestTO 申请投资数据to
     * @return class ApplyInvestBO
     * @throws SerException
     */
    default ApplyInvestBO editApplyInvest(ApplyInvestTO applyInvestTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除申请投资
     *
     * @param id
     * @throws SerException
     */
    default void removeApplyInvest(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param to 查询条件封装类
     * @return 汇总结果集
     */
    default List<CollectApplyInvestBO> collectApplyInvest(CollectApplyInvestTO to) throws SerException{
        return null;
    }

}