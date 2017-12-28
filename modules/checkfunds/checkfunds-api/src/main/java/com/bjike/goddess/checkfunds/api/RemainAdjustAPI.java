package com.bjike.goddess.checkfunds.api;


import com.bjike.goddess.checkfunds.bo.RemainAdjustBO;
import com.bjike.goddess.checkfunds.dto.RemainAdjustDTO;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.to.RemainAdjustTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 余额调节业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RemainAdjustAPI {
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
     * 添加
     *
     * @param to 余额调节to
     * @throws SerException
     */
    void save(RemainAdjustTO to) throws SerException;

    /**
     * 通过dto查找
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RemainAdjustBO> findByDTO(RemainAdjustDTO dto) throws SerException;

    /**
     * 加资金流水项目
     *
     * @param to 余额调节to
     * @param id 银企对账id
     * @return
     * @throws SerException
     */
    List<RemainAdjustBO> addFund(RemainAdjustTO to, String id) throws SerException;

    /**
     * 减资金流水项目
     *
     * @param to 余额调节to
     * @param id 银企对账id
     * @return
     * @throws SerException
     */
    List<RemainAdjustBO> removeFund(RemainAdjustTO to, String id) throws SerException;

    /**
     * 加银行流水项目
     *
     * @param to 余额调节to
     * @param id 银企对账id
     * @return
     * @throws SerException
     */
    List<RemainAdjustBO> addBank(RemainAdjustTO to, String id) throws SerException;

    /**
     * 减银行流水项目
     *
     * @param to 余额调节to
     * @param id 银企对账id
     * @return
     * @throws SerException
     */
    List<RemainAdjustBO> removeBank(RemainAdjustTO to, String id) throws SerException;

    /**
     * 确认余额调节
     *
     * @param id          银企对账id
     * @param fundBalance 资金流水余额
     * @param bankBalance 银行流水余额
     * @throws SerException
     */
    void confirmAdjust(String id, Double fundBalance, Double bankBalance) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long countNum(RemainAdjustDTO dto) throws SerException;
}

