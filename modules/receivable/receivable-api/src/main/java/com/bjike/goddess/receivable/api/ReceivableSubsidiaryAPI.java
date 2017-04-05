package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.ReceivableSubsidiaryBO;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;

import java.util.List;

/**
 * 回款明细业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReceivableSubsidiaryAPI {
    /**
     * 获取回款明细
     *
     * @param receivableSubsidiaryDTO 回款明细dto
     * @return class receivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> findListReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws SerException {
        return null;
    }

    /**
     * 数据录入回款明细
     *
     * @param receivableSubsidiaryTO 回款明细数据to
     * @throws SerException
     */
    default ReceivableSubsidiaryBO insertReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return null;
    }

    /**
     * 编辑回款明细
     *
     * @param receivableSubsidiaryTO 回款明细数据to
     * @return class receivableSubsidiaryBO
     * @throws SerException
     */
    default ReceivableSubsidiaryBO editReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除回款明细
     *
     * @param id
     * @throws SerException
     */
    default void removeReceivableSubsidiary(String id) throws SerException {

    }
    /**
     * 签字审批时间
     * @param auditDate
     */
    default List<String> auditDate(String auditDate) throws SerException{
        return null;
    }
    /**
     * ERP结算审批时间
     * @param countDate
     */
    default List<String> countDate(String countDate) throws SerException{
        return null;
    }
    /**
     * 发票审核时间
     * @param billDate
     */
    default List<String> billDate(String billDate) throws SerException{
        return  null;
    }
    /**
     * 预计支付时间
     * @param planDate
     */
    default String planDate(String planDate)throws SerException{
        return null;
    }
    /**
     * 时间
     * @param receivableSubsidiary
     */
    default void editDate(ReceivableSubsidiary receivableSubsidiary, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr)throws SerException{

    }
    /**
     * 导出
     *
     * @throws SerException
     */
    default String exportExcel(String area,String start,String end) throws SerException {
        return null;
    }


    /**
     * 导入
     */
    default void input() throws SerException {
        return;

    }
    /**
     * 地区汇总
     *
     * @param area area
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> collectArea(String[] area) throws SerException {
        return null;
    }
    /**
     * 地区汇总详情
     *
     * @param area area
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> collectAreaDetail(String[] area) throws SerException {
        return null;
    }
    /**
     * 项目名称汇总
     *
     * @param innerName innerName
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> collectInnerName(String[] innerName) throws SerException {
        return null;
    }
    /**
     * 项目名称汇总详情
     *
     * @param innerName innerName
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> collectInnerNameDetail(String[] innerName) throws SerException {
        return null;
    }
    /**
     * 总包单位汇总
     *
     * @param contractor contractor
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> collectContractor(String[] contractor) throws SerException {
        return null;
    }
    /**
     * 总包单位汇总详情
     *
     * @param contractor contractor
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> collectContractorDetail(String[] contractor) throws SerException {
        return null;
    }
    /**
     * 对比汇总
     *
     * @param receivableSubsidiaryTO 对比汇总数据to
     * @return class receivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> collectCompare(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return null;
    }
    /**
     * 发送邮件
     *
     * @return class String
     */
    default ReceivableSubsidiaryBO sendReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return null;
    }


}