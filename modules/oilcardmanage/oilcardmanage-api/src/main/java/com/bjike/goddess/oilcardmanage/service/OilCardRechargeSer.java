package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.oilcardmanage.bo.AnalyzeBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardRecharge;
import com.bjike.goddess.oilcardmanage.excel.SonPermissionObject;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;

import java.util.List;

/**
 * 油卡充值业务接口
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午10:49]
 * @Package:[ com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardRechargeSer extends Ser<OilCardRecharge, OilCardRechargeDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
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
     * @param startTime      查询开始时间
     * @param endTime        查询结束时间
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

    List<OilCardRechargeBO> findByBasicId(String id) throws SerException;

    AnalyzeBO analyze(String oilCardCode, Integer year, Integer month) throws SerException;

    /**
     * 删除
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 根据Id查询油卡充值记录
     *
     * @param id
     */
    OilCardRechargeBO findBy(String id) throws SerException;

}
