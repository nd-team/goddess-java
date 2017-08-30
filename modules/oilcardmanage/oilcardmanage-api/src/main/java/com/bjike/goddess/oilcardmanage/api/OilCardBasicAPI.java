package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.excel.SonPermissionObject;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;

import java.util.List;

/**
 * 油卡基本信息对外提供接口
 *
 * @Author: [Jason]
 * @Date: [17-3-13 上午11:12]
 * @Package:[ com.bjike.goddess.com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OilCardBasicAPI {


    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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
     * @throws SerException
     */
    OilCardBasicBO updateOilCardBasic(OilCardBasicTO to) throws SerException;

    /**
     * 冻结油卡基本信息
     *
     * @param id 油卡信息记录Id
     * @throws SerException 冻结油卡记录业务异常
     */
    void freezeOilCardBasic(String id) throws SerException;

    /**
     * 解冻油卡基本信息
     *
     * @param id 油卡信息记录Id
     * @throws SerException 解冻油卡记录业务异常
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 删除油卡基本信息
     *
     * @param id 删除信息记录Id
     * @throws SerException 删除油卡记录业务异常
     */
    void deleteOilCardBasic(String id) throws SerException;


    /**
     * 分页查询
     *
     * @param dto 查询条件
     * @throws SerException 分页查询业务异常
     */
    List<OilCardBasicBO> pageList(OilCardBasicDTO dto) throws SerException;

    /**
     * 通过油卡编号查询油卡信息
     *
     * @param oilCardCode 油卡编号
     * @return 油卡信息
     */
    OilCardBasicBO findByCode(String oilCardCode) throws SerException;

    /**
     * 根据Id查询油卡基础信息
     *
     * @param id id
     */
    OilCardBasicBO find(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(OilCardBasicDTO dto) throws SerException;

    /**
     * 保存油卡基本信息
     */
    void updateOliCardBasic(OilCardBasicBO basicBO) throws SerException;

    /**
     * 查询未冻结的油卡
     * @throws SerException
     */
    List<OilCardBasicBO> findOilCard() throws SerException;


}
