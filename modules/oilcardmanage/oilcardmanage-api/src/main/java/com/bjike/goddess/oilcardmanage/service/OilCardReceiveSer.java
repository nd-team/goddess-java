package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.oilcardmanage.bo.CusPermissionOperateBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardReceiveBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.entity.CusPermissionOperate;
import com.bjike.goddess.oilcardmanage.entity.OilCardReceive;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;
import com.bjike.goddess.oilcardmanage.excel.SonPermissionObject;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;
import com.bjike.goddess.organize.bo.AreaBO;

import java.util.List;

/**
 * 油卡领用业务接口
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:37]
 * @Package:[ com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardReceiveSer extends Ser<OilCardReceive, OilCardReceiveDTO> {
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
     * 新增油卡领用记录
     *
     * @param to 油卡领用信息
     * @return 油卡领用信息
     * @throws SerException 新增油卡领用异常
     */
    OilCardReceiveBO insertModel(OilCardReceiveTO to) throws SerException;

    /**
     * 编辑油卡领用记录
     *
     * @param to 油卡领用信息
     * @return 油卡领用信息
     * @throws SerException 编辑油卡领用异常
     */
    OilCardReceiveBO updateModel(OilCardReceiveTO to) throws SerException;

    /**
     * 归还领用的油卡
     *
     * @param id 领用油卡记录Id
     * @throws SerException 归还领用油卡异常
     */
    void returnOilCardReceive(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<OilCardReceiveBO> pageList(OilCardReceiveDTO dto) throws SerException;

    /**
     * 审核
     *
     * @param id                   id
     * @param auditSuggestion      审核意见
     * @param auditResult 审核结果
     */
    void audit(String id, String auditSuggestion, OilCardReceiveResult auditResult) throws SerException;

    /**
     * 查询所有未冻结的油卡
     * @throws SerException
     */
    List<String> findOilCard() throws SerException;

    /**
     * 查询所有地区
     * @throws SerException
     */
    List<AreaBO> findArea() throws SerException;

    /**
     * 查询所有审核人
     * @return
     * @throws SerException
     */
    List<String> findOperate() throws SerException;

    /**
     * 根据iD查询油卡领用信息
     *
     * @param id id
     */
    OilCardReceiveBO findOne(String id) throws SerException;

}
