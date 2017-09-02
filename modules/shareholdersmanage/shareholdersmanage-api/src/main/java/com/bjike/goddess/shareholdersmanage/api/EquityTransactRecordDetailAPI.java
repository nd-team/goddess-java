package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDetailDTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 股权交易记录详情业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:11 ]
 * @Description: [ 股权交易记录详情业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityTransactRecordDetailAPI {
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
     * 股权交易记录详情列表总条数
     */
    default Long countTranDetail(EquityTransactRecordDetailDTO equityTransactRecordDetailDTO) throws SerException {
        return null;
    }

    /**
     * 一个股权交易记录详情
     *
     * @return class EquityTransactRecordDetailBO
     */
    default EquityTransactRecordDetailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股权交易记录详情列表
     *
     * @param shareholderName 股东名称
     * @return class EquityTransactRecordDetailBO
     * @throws SerException
     */
    default List<EquityTransactRecordDetailBO> findByName(String shareholderName) throws SerException {
        return null;
    }

    /**
     * 股权交易记录详情添加
     *
     * @param equityTransactRecordDetailTO 股权交易记录详情数据to
     * @throws SerException
     */
    default void save(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        return;
    }

    /**
     * 股权交易记录详情编辑
     *
     * @param equityTransactRecordDetailTO 股权交易记录详情数据to
     * @throws SerException
     */
    default void edit(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        return;
    }

    /**
     * 根据id删除股权交易记录详情
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
    /**
     * 根据股东姓名删除交易记录详情
     */
    default void deleteByName(String shareholderName) throws SerException{
        return;
    }
    /**
     * 根据股东姓名查询股东记录明细
     *
     * @return class EquityTransactRecordDetailBO
     */
    default List<EquityTransactRecordDetailBO> getByName(String shareholderName) throws SerException {
        return null;
    }
    /**
     * 根据交易操作id查询所有改记录明细
     *
     * @return class EquityTransactRecordDetailBO
     */
    default List<EquityTransactRecordDetailBO> getByTransactId(String transactId) throws SerException {
        return null;
    }
    /**
     * 根据股东姓名和交易id查询股东记录明细
     *
     * @return class EquityTransactRecordDetailBO
     */
    default EquityTransactRecordDetailBO getByNameId(String shareholderName,String transactId) throws SerException {
        return null;
    }
    /**
     * 根据交易id删除股东记录明细
     *
     */
    default void deleteByTransactId(String transactId) throws SerException {
        return;
    }
}