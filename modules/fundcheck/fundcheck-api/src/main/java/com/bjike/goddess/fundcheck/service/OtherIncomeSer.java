package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundcheck.bo.OtherIncomeBO;
import com.bjike.goddess.fundcheck.bo.StockMoneyBO;
import com.bjike.goddess.fundcheck.dto.OtherIncomeDTO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.entity.OtherIncome;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeCollectTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeTO;
import com.bjike.goddess.fundcheck.to.StockMoneyTO;

import java.util.List;

/**
 * 其他收入业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:53 ]
 * @Description: [ 其他收入业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OtherIncomeSer extends Ser<OtherIncome, OtherIncomeDTO> {
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
     * 其他收入列表总条数
     */
    default Long count(OtherIncomeDTO otherIncomeDTO) throws SerException {
        return null;
    }

    /**
     * 一个其他收入
     *
     * @return class OtherIncomeBO
     */
    default OtherIncomeBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 其他收入
     *
     * @param otherIncomeDTO 其他收入dto
     * @return class OtherIncomeBO
     * @throws SerException
     */
    default List<OtherIncomeBO> findList(OtherIncomeDTO otherIncomeDTO) throws SerException {
        return null;
    }

    /**
     * 添加其他收入
     *
     * @param otherIncomeTO 其他收入数据to
     * @return class OtherIncomeBO
     * @throws SerException
     */
    default OtherIncomeBO insert(OtherIncomeTO otherIncomeTO) throws SerException {
        return null;
    }

    /**
     * 编辑其他收入
     *
     * @param otherIncomeTO 其他收入数据to
     * @return class OtherIncomeBO
     * @throws SerException
     */
    default OtherIncomeBO edit(OtherIncomeTO otherIncomeTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除其他收入
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param to
     * @return OtherIncomeBO
     * @throws SerException
     */
    default List<OtherIncomeBO> collect(OtherIncomeCollectTO to) throws SerException {
        return null;
    }

}