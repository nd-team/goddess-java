package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.*;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.to.CollectCompareTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.ProgressTO;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;

import java.util.List;
import java.util.Map;

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
     * 回款明细列表总条数
     */
    default Long countReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws SerException {
        return null;
    }

    /**
     * 一个回款明细
     *
     * @return class ReceivableSubsidiaryBO
     */
    default ReceivableSubsidiaryBO getOne(String id) throws SerException {
        return null;
    }

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
     *
     * @param auditTime
     */
    default Map<String,String> auditTime(String auditTime) throws SerException {
        return null;
    }

    /**
     * ERP结算审批时间
     *
     * @param countTime
     */
    default Map<String,String> countTime(String countTime) throws SerException {
        return null;
    }

    /**
     * 发票审核时间
     *
     * @param billTime
     */
    default Map<String,String> billTime(String billTime) throws SerException {
        return null;
    }

    /**
     * 预计支付时间
     *
     * @param planTime
     */
    default Map<String,String> planTime(String planTime) throws SerException {
        return null;
    }


    /**
     * 时间
     *
     * @param receivableSubsidiaryTO
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default ReceivableSubsidiaryBO editTime(ReceivableSubsidiaryTO receivableSubsidiaryTO, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr) throws SerException {
        return null;
    }
    /**
     * 结算进度
     *
     * @param to 结算进度数据to
     * @return class receivableSubsidiaryBO
     * @throws SerException
     */
    default ReceivableSubsidiaryBO progress(ProgressTO to) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }

    /**
     * 获取项目名称
     *
     * @return class String
     */
    default List<String> getInnerName() throws SerException {
        return null;
    }

    /**
     * 获取总包单位
     *
     * @return class Contractor
     */
    default List<String> getContractor() throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @param areas 地区
     * @return class CollectAreaBO
     * @throws SerException
     */
    default List<CollectAreaBO> collectArea(String[] areas) throws SerException {
        return null;
    }

    /**
     * 项目名称汇总
     *
     * @param innerNames 项目名称
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<CollectProjectNameBO> collectInnerName(String[] innerNames) throws SerException {
        return null;
    }

    /**
     * 总包单位汇总
     *
     * @param contractors 总包单位
     * @return class CollectContractorBO
     * @throws SerException
     */
    default List<CollectContractorBO> collectContractor(String[] contractors) throws SerException {
        return null;
    }

    /**
     * 地区汇总详情
     *
     * @param areas 地区
     * @return class CollectAreaDetailBO
     * @throws SerException
     */
    default List<CollectAreaDetailBO> collectAreaDetail(String[] areas) throws SerException {
        return null;
    }

    /**
     * 项目名称汇总详情
     *
     * @param innerNames 项目名称
     * @return class CollectProjectNameDetailBO
     * @throws SerException
     */
    default List<CollectProjectNameDetailBO> collectInnerNameDetail(String[] innerNames) throws SerException {
        return null;
    }

    /**
     * 总包单位汇总详情
     *
     * @param contractors 总包单位
     * @return class CollectContractorDetailBO
     * @throws SerException
     */
    default List<CollectContractorDetailBO> collectContractorDetail(String[] contractors) throws SerException {
        return null;
    }

    /**
     * id获取汇总详情
     *
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default ReceivableSubsidiaryBO collectId(String id) throws SerException {
        return null;
    }

    /**
     * 对比汇总
     *
     * @param to 对比汇总数据to
     * @return class CollectCompareBO
     * @throws SerException
     */
    default List<CollectCompareBO> collectCompare(CollectCompareTO to) throws SerException {
        return null;
    }
    /**
     * 导入
     *
     * @param id id
     * @param receivableSubsidiaryTOS 回款管理
     * @return class ReceivableSubsidiaryBO
     */
    default ReceivableSubsidiaryBO importExcelBack(String id,List<ReceivableSubsidiaryTO> receivableSubsidiaryTOS) throws SerException {
        return null;
    }
    /**
     * Excel下载模板
     *
     * @throws SerException
     */
    byte[] templateExportBack() throws SerException;
    /**
     *  导入
     * @param receivableSubsidiaryTOS 回款管理
     * @return class ReceivableSubsidiaryBO
     */
    default ReceivableSubsidiaryBO importExcel(List<ReceivableSubsidiaryTO> receivableSubsidiaryTOS) throws SerException { return null;}

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(ReceivableSubsidiaryDTO dto) throws SerException;
    /**
     * Excel下载模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;
    /**
     * 根据到账时间拿数据(资金核对)
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return class ReceivableSubsidiaryBO
     * @throws SerException
     */
    default List<ReceivableSubsidiaryBO> receivable(String startTime,String endTime) throws SerException {
        return null;
    }
}