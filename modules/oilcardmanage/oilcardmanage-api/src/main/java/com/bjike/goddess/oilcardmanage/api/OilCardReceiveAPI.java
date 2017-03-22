package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardmanage.bo.OilCardReceiveBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;

import java.util.List;

/**
 * 油卡领用信息对外发布接口
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:33]
 * @Package:[ com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardReceiveAPI {

    /**
     * 新增领用油卡信息
     * @param to 领用油卡信息
     * @return 领用油卡信息
     * @throws SerException 领用油卡异常
     */
    OilCardReceiveBO saveOilCardReceive(OilCardReceiveTO to) throws SerException;

    /**
     * 编辑领用油卡信息
     * @param to 编辑油卡信息
     * @return 编辑油卡信息
     * @throws SerException 编辑领用油卡异常
     */
    OilCardReceiveBO updateOilCardReceive(OilCardReceiveTO to) throws SerException;

    /**
     * 审核领用油卡信息
     * @param to 审核油卡信息
     * @return 审核油卡信息
     * @throws SerException 审核领用油卡异常
     */
    OilCardReceiveBO auditOilCardReceive(OilCardReceiveTO to) throws SerException;

    /**
     * 删除领用油卡信息
     * @param id 油卡领用ID
     * @throws SerException 删除领用油卡异常
     */
    void deleteOilCardReceive(String id) throws SerException;

    /**
     * 归还领用油卡
     * @param id 油卡领用id
     * @throws SerException 归还领用油卡异常
     */
    void returnOilCardReceive(String id) throws SerException;

    /**
     * 分页查询
     * @param dto 查询条件
     * @throws SerException 分页查询业务异常
     */
    List<OilCardReceiveBO> pageList(OilCardReceiveDTO dto) throws SerException;

}
