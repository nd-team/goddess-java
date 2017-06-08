package com.bjike.goddess.voucher.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.voucher.bo.PartBO;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;

import java.util.List;

/**
 * 记账凭证生成业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VoucherGenerateAPI {

    /**
     * 记账凭证列表总条数
     */
    default Long countVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 记账凭证列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> listVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }
    /**
     * 不分页记账凭证列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> listNoPage(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param voucherGenerateTO 记账凭证信息
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> addVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param voucherGenerateTO 记账凭证信息
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO editVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteVoucherGenerate(String id) throws SerException {
        return;
    }

    ;

    /**
     * 审核列表总条数
     */
    default Long countAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 审核列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> listAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 拆分
     *
     * @param voucherGenerateTO 记账凭证信息
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO split(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return null;
    }

    /**
     * 审核
     *
     * @param id 记账凭证信息id
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO audit(String id) throws SerException {
        return null;
    }


    /**
     * 已审核列表总条数
     */
    default Long countAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已审核列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> listAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 过账
     *
     * @param voucherGenerateTO 记账凭证信息voucherGenerateTO
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO posting(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return null;
    }

    /**
     * 反审核
     *
     * @param id 记账凭证信息id
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO antiAudit(String id) throws SerException {
        return null;
    }

    /**
     * 已审核科目汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> collectSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已审核地区列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> collectArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已审核项目组汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> collectGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已审核项目名称
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> collectPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 已过账列表总条数
     */
    default Long countChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已过账列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> listChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 反过账
     *
     * @param id 记账凭证信息id
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO antiPosting(String id) throws SerException {
        return null;
    }

    /**
     * 结账
     *
     * @param voucherGenerateTO 记账凭证信息voucherGenerateTO
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO checkAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return null;
    }


    /**
     * 已过账科目汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctTransSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已过账地区列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctTransArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已过账项目组汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctTransGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已过账项目名称
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctTransPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 已过账列表总条数
     */
    default Long countCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 已过账列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> listCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 结帐记录科目汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctCkSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 结帐记录地区列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctCkArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 结帐记录项目组汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctCkGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 结帐记录项目名称
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctCkPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 记账凭证列表总条数
     */
    default Long countRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 记账凭证列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> listRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }


    /**
     * 记账凭证记录科目汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctReSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 记账凭证记录地区列表
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctReArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 记账凭证记录项目组汇总
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctReGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 记账凭证记录项目名称
     *
     * @return class VoucherGenerateBO
     */
    default List<VoucherGenerateBO> ctRePname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return null;
    }

    /**
     * 查询所有汇总一级科目
     *
     * @return
     * @throws SerException
     */
    default List<String> listFirstSubject() throws SerException {
        return null;
    }

    /**
     * 根据一级科目查询二级科目
     *
     * @param firstSub 一级科目
     * @return
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
     * @return
     * @throws SerException
     */
    default List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return null;
    }

    /**
     * 查询所有汇总地区
     *
     * @return
     * @throws SerException
     */
    default List<String> listArea() throws SerException {
        return null;
    }

    /**
     * 查询所有汇总项目
     *
     * @return
     * @throws SerException
     */
    default List<String> listProject() throws SerException {
        return null;
    }

    /**
     * 查询所有汇总项目组
     *
     * @return
     * @throws SerException
     */
    default List<String> listGroup() throws SerException {
        return null;
    }

    /**
     * 根据id查找记账凭证
     *
     * @param id id
     */
    default VoucherGenerateBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 根据日期、项目组、地区、项目统计记账凭证
     *
     */
    default List<VoucherGenerateBO> listStatistic(VoucherGenerateDTO voucherGenerateDTO,String condition) throws SerException {
        return null;
    }

    /**
     * 查询资金流水记录即一级科目为银行存款或库存现金
     *
     * @return 资金流水记录
     */
    List<VoucherGenerateBO> findFundRecord(VoucherGenerateDTO dto) throws SerException;

    /**
     * 地区分析
     *
     * @return 地区分析结果
     */
    List<VoucherGenerateBO> areaAnalyze(Integer year, Integer month, String area) throws SerException;

    /**
     * 项目组分析
     *
     * @return 地区分析结果
     */
    List<VoucherGenerateBO> groupAnalyze(Integer year, Integer month, String group) throws SerException;

    /**
     * 项目分析
     *
     * @return 地区分析结果
     */
    List<VoucherGenerateBO> projectAnalyze(Integer year, Integer month, String project) throws SerException;

    /**
     * chenjunhao
     * 查询所有一级科目为销售费用的记录
     * @return class VoucherGenerateBO
     * @throws SerException
     */
    default List<VoucherGenerateBO> allSales() throws SerException{
        return null;
    }

    /**
     * xiazhili
     * 在已过账记录里面根据二级或三级统计金额
     * @return class PartBO
     * @throws SerException
     */
    default List<PartBO> findByCondition( String condition ) throws SerException{
        return null;
    }
}