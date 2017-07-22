package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardmanage.bo.AnalyzeBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;

import java.util.List;

/**
 * 油卡充值对外发布接口
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午10:49]
 * @Package:[ com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardRechargeAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 新增油卡充值信息
     *
     * @param to 油卡充值信息
     * @return 油卡充值信息
     * @throws SerException 油卡充值异常
     */
    OilCardRechargeBO saveOilCardRecharge(OilCardRechargeTO to) throws SerException;

    /**
     * 编辑油卡充值信息
     *
     * @param to 油卡充值信息
     * @return 油卡充值信息
     * @throws SerException 编辑油卡充值异常
     */
    OilCardRechargeBO updateOilCardRecharge(OilCardRechargeTO to) throws SerException;

    /**
     * 油卡充值汇总
     *
     * @param oilCardBasicId 油卡id
     * @return 油卡充值汇总
     * @throws SerException 油卡充值异常
     */
    List<OilCardRechargeBO> collect(String oilCardBasicId, String startTime, String endTime) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 查询条件
     * @throws SerException 分页查询业务异常
     */
    List<OilCardRechargeBO> pageList(OilCardRechargeDTO dto) throws SerException;

    /**
     * 通过油卡信息Id查询油卡充值记录
     */
    List<OilCardRechargeBO> findByBasicId(String id) throws SerException;

    /**
     * 根据Id查询油卡充值记录
     *
     * @param id
     */
    OilCardRechargeBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(OilCardRechargeDTO dto) throws SerException;

    AnalyzeBO analyze(String oilCardCode, Integer year, Integer month) throws SerException;
}
