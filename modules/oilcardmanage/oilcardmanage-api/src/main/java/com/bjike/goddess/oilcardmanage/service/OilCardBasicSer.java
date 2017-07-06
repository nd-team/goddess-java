package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;

import java.util.List;

/**
 * 油卡基础信息业务接口
 *
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:35]
 * @Package:[ com.bjike.goddess.com.bjike.goddess.com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardBasicSer extends Ser<OilCardBasic, OilCardBasicDTO> {

    /**
     * 新增油卡基本信息
     *
     * @param to 油卡基本信息
     * @return 油卡基本信息
     * @throws SerException 新增业务异常
     */
    OilCardBasicBO saveOilCarBasic(OilCardBasicTO to) throws SerException;

    /**
     * 编辑油卡基本信息
     *
     * @param to 油卡基本信息
     * @return 油卡基本信息
     * @throws SerException 编辑业务异常
     */
    OilCardBasicBO updateOilCardBasic(OilCardBasicTO to) throws SerException;

    /**
     * 冻结油卡基本信息
     *
     * @param id 油卡信息记录ID
     * @throws SerException 冻结油卡业务异常
     */
    void freezeOilCardBasic(String id) throws SerException;

    /**
     * 解冻油卡基本信息
     *
     * @param id 油卡信息记录Id
     * @throws SerException 解冻Yuka业务异常
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页数据
     * @return 油卡基本
     * @throws SerException
     */
    List<OilCardBasicBO> pageList(OilCardBasicDTO dto) throws SerException;

    OilCardBasicBO findByCode(String oilCardCode) throws SerException;
}
