package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardRecharge;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;

import java.util.List;

/**
 * 油卡充值对外发布接口
 * @Author: [Jason]
 * @Date: [17-3-15 上午10:49]
 * @Package:[ com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardRechargeAPI {

    /**
     * 新增油卡充值信息
     * @param to 油卡充值信息
     * @return 油卡充值信息
     * @throws SerException 油卡充值异常
     */
    OilCardRechargeBO saveOilCardRecharge(OilCardRechargeTO to) throws SerException;

    /**
     * 编辑油卡充值信息
     * @param to 油卡充值信息
     * @return 油卡充值信息
     * @throws SerException 编辑油卡充值异常
     */
    OilCardRechargeBO updateOilCardRecharge(OilCardRechargeTO to) throws SerException;

    /**
     * 油卡充值汇总
     * @param id 油卡id
     * @return 油卡充值汇总
     * @throws SerException 油卡充值异常
     */
    List<OilCardRechargeBO> collect(String id,String startTime,String endTime) throws SerException;

    /**
     * 分页查询
     * @param dto 查询条件
     * @throws SerException 分页查询业务异常
     */
    List<OilCardRechargeBO> pageList(OilCardRechargeDTO dto) throws SerException;
}
