package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.bo.OtherIncomeBO;
import com.bjike.goddess.fundcheck.bo.OtherSpendBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.dto.OtherSpendDTO;
import com.bjike.goddess.fundcheck.entity.OtherSpend;
import com.bjike.goddess.fundcheck.to.*;

import java.util.List;

/**
 * 其他支出业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:57 ]
 * @Description: [ 其他支出业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OtherSpendSer extends Ser<OtherSpend, OtherSpendDTO> {
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
     * 其他支出列表总条数
     */
    default Long count(OtherSpendDTO otherSpendDTO) throws SerException {
        return null;
    }

    /**
     * 一个其他支出
     *
     * @return class OtherSpendBO
     */
    default OtherSpendBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 其他支出
     *
     * @param otherSpendDTO 其他支出dto
     * @return class OtherSpendBO
     * @throws SerException
     */
    default List<OtherSpendBO> findListBack(OtherSpendDTO otherSpendDTO) throws SerException {
        return null;
    }

    /**
     * 添加其他支出
     *
     * @param otherSpendTO 其他支出数据to
     * @return class OtherSpendBO
     * @throws SerException
     */
    default OtherSpendBO insert(OtherSpendTO otherSpendTO) throws SerException {
        return null;
    }

    /**
     * 编辑其他支出
     *
     * @param otherSpendTO 其他支出数据to
     * @return class OtherSpendBO
     * @throws SerException
     */
    default OtherSpendBO edit(OtherSpendTO otherSpendTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除其他支出
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
     * @return OtherSpendBO
     * @throws SerException
     */
    default List<OtherSpendBO> collect(OtherSpendCollectTO to) throws SerException {
        return null;
    }
}