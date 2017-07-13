package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundcheck.bo.StockMoneyBO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.entity.StockMoney;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.StockMoneyCollectTO;
import com.bjike.goddess.fundcheck.to.StockMoneyTO;

import java.util.List;

/**
 * 收到股东款业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StockMoneySer extends Ser<StockMoney, StockMoneyDTO> {
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
     * 收到股东款列表总条数
     */
    default Long count(StockMoneyDTO stockMoneyDTO) throws SerException {
        return null;
    }

    /**
     * 一个收到股东款
     *
     * @return class StockMoneyBO
     */
    default StockMoneyBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 收到股东款
     *
     * @param stockMoneyDTO 收到股东款dto
     * @return class StockMoneyBO
     * @throws SerException
     */
    default List<StockMoneyBO> findList(StockMoneyDTO stockMoneyDTO) throws SerException {
        return null;
    }

    /**
     * 添加收到股东款
     *
     * @param stockMoneyTO 收到股东款数据to
     * @return class StockMoneyBO
     * @throws SerException
     */
    default StockMoneyBO insert(StockMoneyTO stockMoneyTO) throws SerException {
        return null;
    }

    /**
     * 编辑收到股东款
     *
     * @param stockMoneyTO 收到股东款款数据to
     * @return class StockMoneyBO
     * @throws SerException
     */
    default StockMoneyBO edit(StockMoneyTO stockMoneyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除收到股东款
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
     * @throws SerException
     */
    default List<StockMoneyBO> collect(StockMoneyCollectTO to) throws SerException {
        return null;
    }
    /**
     * 查询所有一级科目
     *
     * @return String
     * @throws SerException
     */
    default List<String> listFirstSubject() throws SerException {
        return null;
    }

    /**
     * 根据一级科目查询二级科目
     *
     * @param firstSub 一级科目
     * @return String
     * @throws SerException
     */
    default List<String> listSubByFirst(String firstSub) throws SerException {
        return null;
    }

    /**
     * 根据一级二级查询三级科目
     *
     * @param firstSub  一级科目
     * @param secondSub 二级科目
     * @return String
     * @throws SerException
     */
    default List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return null;
    }
    /**
     * 导入
     *
     * @param stockMoneyTOS 收到股东款
     * @return class StockMoneyBO
     */
    default StockMoneyBO importExcel(List<StockMoneyTO> stockMoneyTOS) throws SerException {
        return null;
    }
    /**
     * 导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;

}