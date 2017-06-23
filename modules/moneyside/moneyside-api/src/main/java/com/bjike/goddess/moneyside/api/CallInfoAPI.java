package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.ApplyInvestBO;
import com.bjike.goddess.moneyside.bo.CallInfoBO;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.to.ApplyInvestTO;
import com.bjike.goddess.moneyside.to.CallInfoTO;

import java.util.List;
import java.util.Set;

/**
 * 招投信息列表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CallInfoAPI {

    /**
     * 招投信息列表总条数
     */
    default Long countCallInfo(CallInfoDTO callInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个招投信息列表
     *
     * @return class CallInfoBO
     */
    default CallInfoBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 招投信息列表
     *
     * @param CallInfoDTO 招投信息列表dto
     * @return class CallInfoBO
     * @throws SerException
     */
    default List<CallInfoBO> findListCallInfo(CallInfoDTO CallInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加招投信息列表
     *
     * @param callInfoTO 招投信息列表数据to
     * @return class CallInfoBO
     * @throws SerException
     */
    default CallInfoBO insertCallInfo(CallInfoTO callInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑招投信息列表
     *
     * @param callInfoTO 招投信息列表数据to
     * @return class CallInfoBO
     * @throws SerException
     */
    default CallInfoBO editCallInfo(CallInfoTO callInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除招投信息列表
     *
     * @param id
     * @throws SerException
     */
    default void removeCallInfo(String id) throws SerException {

    }
    /**
     * 申请投资
     *
     * @param callInfoTO 申请投资数据to
     * @return class CallInfoBO
     * @throws SerException
     */
    default CallInfoBO applyInvest(CallInfoTO callInfoTO) throws SerException {
        return null;
    }
    /**
     * 根据项目名称拿值
     *
     * @param innerProject 内部项目名称
     * @return class CallInfoBO
     * @throws SerException
     */
    default CallInfoBO getInnerProject(String innerProject) throws SerException {
        return null;
    }
    /**
     * 获取所有内部项目名称
     *
     * @return class String
     * @throws SerException
     */
    default Set<String> getInnerProject() throws SerException {
        return null;
    }



}