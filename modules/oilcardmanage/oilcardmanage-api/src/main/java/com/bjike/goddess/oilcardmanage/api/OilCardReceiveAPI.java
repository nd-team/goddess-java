package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.oilcardmanage.bo.CusPermissionOperateBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardReceiveBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.entity.CusPermissionOperate;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;
import com.bjike.goddess.organize.bo.AreaBO;

import java.util.List;

/**
 * 油卡领用信息对外发布接口
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:33]
 * @Package:[ com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardReceiveAPI {


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
     * 新增领用油卡信息
     *
     * @param to 领用油卡信息
     * @return 领用油卡信息
     * @throws SerException 领用油卡异常
     */
    OilCardReceiveBO saveOilCardReceive(OilCardReceiveTO to) throws SerException;

    /**
     * 编辑领用油卡信息
     *
     * @param to 编辑油卡信息
     * @return 编辑油卡信息
     * @throws SerException 编辑领用油卡异常
     */
    OilCardReceiveBO updateOilCardReceive(OilCardReceiveTO to) throws SerException;

    /**
     * 审核领用油卡信息
     */
    void auditOilCardReceive(String id, String auditSuggestion, OilCardReceiveResult oilCardReceiveResult) throws SerException;

    /**
     * 删除领用油卡信息
     *
     * @param id 油卡领用ID
     * @throws SerException 删除领用油卡异常
     */
    void deleteOilCardReceive(String id) throws SerException;

    /**
     * 归还领用油卡
     *
     * @param id 油卡领用id
     * @throws SerException 归还领用油卡异常
     */
    void returnOilCardReceive(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 查询条件
     * @throws SerException 分页查询业务异常
     */
    List<OilCardReceiveBO> pageList(OilCardReceiveDTO dto) throws SerException;

    /**
     * 根据iD查询油卡领用信息
     *
     * @param id id
     */
    OilCardReceiveBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(OilCardReceiveDTO dto) throws SerException;

    /**
     * 查询所有未冻结的油卡
     * @throws SerException
     */
    List<OilCardBasicBO> findOilCard() throws SerException;

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
    List<CusPermissionOperateBO> findOperate() throws SerException;
}
