package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.AccrualAllotBO;
import com.bjike.goddess.moneyside.bo.CallInfoBO;
import com.bjike.goddess.moneyside.dto.AccrualAllotDTO;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.entity.CallInfo;
import com.bjike.goddess.moneyside.to.AccrualAllotTO;
import com.bjike.goddess.moneyside.to.CallInfoTO;

import java.util.List;

/**
 * 招投信息列表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CallInfoSer extends Ser<CallInfo, CallInfoDTO> {

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

}