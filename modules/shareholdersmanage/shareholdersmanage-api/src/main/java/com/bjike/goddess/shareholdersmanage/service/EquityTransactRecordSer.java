package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareAndTypeBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 股权交易记录业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:05 ]
 * @Description: [ 股权交易记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityTransactRecordSer extends Ser<EquityTransactRecord, EquityTransactRecordDTO> {
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
     * 股权交易记录列表总条数
     */
    default Long countTrans(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        return null;
    }

    /**
     * 一个股权交易记录
     *
     * @return class EquityTransactRecordBO
     */
    default EquityTransactRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股权交易记录列表
     *
     * @param equityTransactRecordDTO 股权交易记录dto
     * @return class EquityTransactRecordBO
     * @throws SerException
     */
    default List<EquityTransactRecordBO> findList(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        return null;
    }
    /**
     * 查询交易记录该该股权类型的金额总和
     *
     * @return
     * @throws SerException
     */
    default Double transTotalAmount(String equityType) throws SerException {
        return null;
    }
    /**
     * 修改交易记录
     *
     * @return
     * @throws SerException
     */
    default void updateTrans(EquityTransactRecordTO equityTransactRecordTO) throws SerException {
        return;
    }
     /**
     * 重新修改所有该类型的交易记录占股比例
     *
     * @return
     * @throws SerException
     */
    default void updateTransList() throws SerException {
        return;
    }
    /**
     * 删除交易记录
     */
    default void deleteTransact(String id) throws SerException{
        return;
    }
    /**
     * 根据股东姓名删除交易记录
     */
    default void deleteByName(String shareholderName) throws SerException{
        return;
    }
    /**
     * 根据股东姓名查询股东记录
     *
     * @return class EquityTransactRecordBO
     */
    default EquityTransactRecordBO getByName(String shareholderName) throws SerException {
        return null;
    }
    /**
     * 汇总
     *
     * @return class EquityTransactRecordBO
     */
    default List<EquityTransactRecordBO> summationTrans( ) throws SerException {
        return null;
    }
    /**
     * 查看所有的股权类型
     *
     * @throws SerException
     */
    default List<String> findEquityType() throws SerException {
        return null;
    }
    /**
     * 恢复修改过的数据
     *
     * @throws SerException
     */
    default void reinstate(EquityTransactRecordBO equityTransactRecordBO,EquityTransactRecordDetailBO equityTransactRecordDetailBO)throws SerException{
        return;
    }
    /**
     * 根据股权类型查询股东记录
     *
     * @return class EquityTransactRecordBO
     */
    default List<EquityTransactRecordBO> getByEquityType(String equityType) throws SerException {
        return null;
    }
    /**
     * 根据股权类型查询所有的股东姓名
     *
     */
    default List<String> getNameByEquityType(String equityType) throws SerException {
        return null;
    }
    /**
     * 查询所有的股东姓名
     *
     */
    default List<ShareAndTypeBO> getNameAndType() throws SerException {
        return null;
    }
}
