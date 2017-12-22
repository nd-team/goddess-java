package com.bjike.goddess.voucher.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.voucher.bo.*;
import com.bjike.goddess.voucher.dto.*;
import com.bjike.goddess.voucher.excel.SonPermissionObject;
import com.bjike.goddess.voucher.to.AnalysisTO;
import com.bjike.goddess.voucher.to.ExportSubjectCollectTO;
import com.bjike.goddess.voucher.to.GuidePermissionTO;
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
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermissionAccount() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }


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
     * @param voucherGenerateTO 记账凭证信息
     */
    default void audit(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return;
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
    default Long posting(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return null;
    }

    /**
     * 反审核
     *
     * @param voucherGenerateTO 记账凭证信息
     */
    default void antiAudit(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return ;
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
     * 根据一级科目代码查询二级科目
     *
     * @param firstSub 一级科目
     * @return
     * @throws SerException
     */
    default List<String> listSubByFirst(String firstSub) throws SerException {
        return null;
    }

    /**
     * 根据一级科目代码查询三级科目(参数二级科目传过后台不作任何处理)
     *
     * @param firstSub  一级科目
     * @param secondSub 二级科目(科目汇总表数据修改后,方便前端不修改接口,参数不删除,但传过后台不做处理)
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
     */
    default List<VoucherGenerateBO> listStatistic(VoucherGenerateDTO voucherGenerateDTO, String condition) throws SerException {
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
     *
     * @return class VoucherGenerateBO
     * @throws SerException
     */
    default List<VoucherGenerateBO> allSales() throws SerException {
        return null;
    }

    /**
     * 根据日期地区项目组项目名称科目汇总明细帐
     *
     * @param dto
     * @return class AccountInfoBO
     * @throws SerException
     */
    default List<AccountInfoBO> accountCollect(VoucherGenerateDTO dto) throws SerException {
        return null;
    }

    /**
     * 明细账导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcelAccount(VoucherGenerateDTO dto) throws SerException;

    /**
     * 从已过账那里获取所有地区
     *
     * @throws SerException
     */
    default List<String> accountArea() throws SerException {
        return null;
    }

    /**
     * 从已过账那里获取所有项目名称
     *
     * @throws SerException
     */
    default List<String> accountProjectName() throws SerException {
        return null;
    }

    /**
     * 从已过账那里获取所有项目组部门
     *
     * @throws SerException
     */
    default List<String> accountProjectGroup() throws SerException {
        return null;
    }

    /**
     * 从已过账那里获取所有科目
     *
     * @throws SerException
     */
    default List<String> accountSubject() throws SerException {
        return null;
    }

    /**
     * 从已过账那里根据一级获取所有二级
     *
     * @throws SerException
     */
    default List<String> subSubject(String firstSubject) throws SerException {
        return null;
    }

    /**
     * 从已过账那里根据一级,二级获取所有三级
     *
     * @throws SerException
     */
    default List<String> thirdSubject(String firstSubject, String subSubject) throws SerException {
        return null;
    }

    /**
     * xiazhili
     * 在已过账记录里面根据二级或三级统计金额
     *
     * @return class PartBO
     * @throws SerException
     */
    default List<PartBO> findByMoney(VoucherGenerateDTO dto) throws SerException {
        return null;
    }

    /**
     * xiazhili
     * 在资金和对里面根据一级和二级和三级统计借方金额和贷方金额
     *
     * @return class PartOptionBO
     * @throws SerException
     */
    default PartOptionBO findMoneyByCondition(String first, String second, String third) throws SerException {
        return null;
    }


    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(VoucherGenerateExportDTO dto) throws SerException;

    /**
     * 导出Excel导入模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;


    /**
     * 导入
     *
     * @param voucherGenerateTO 记账凭证导入
     * @return class VoucherGenerateBO
     */
    default VoucherGenerateBO importExcel(List<VoucherGenerateTO> voucherGenerateTO) throws SerException {
        return null;
    }

    /**
     * chenjunhao
     *
     * @param id
     * @return
     * @throws SerException
     */
    VoucherGenerateBO getByIdCJh(String id) throws SerException;

    /**
     * 反结账
     *
     * @param voucherGenerateTO
     * @return
     * @throws SerException
     */
    default void antiCheckAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return;
    }

    /**
     * 查看月度,季度,年度的结账记录
     *
     * @param month
     * @param quart
     * @param year
     * @return
     * @throws SerException
     */
    default List<VoucherGenerateBO> findCkRecordByTime(String month, Integer quart, String year) throws SerException {
        return null;
    }

    /**
     * 记账凭证记录分析
     *
     * @param to
     * @return
     * @throws SerException
     */
    default List<AnalysisBO> analysis(AnalysisTO to) throws SerException {
        return null;
    }

    /**
     * 记账凭证记录科目汇总柱状图
     */
    default OptionBO ctReSubHistogram() throws SerException {
        return null;
    }

    /**
     * 根据时间查询对应的借方和贷方金额
     * zhuangkaiqin
     *
     * @param subjectCollectDTO
     * @return
     * @throws SerException
     */
    default SubjectCollectBO getSum(SubjectCollectDTO subjectCollectDTO, String time, Boolean tar) throws SerException {
        return null;
    }

    default Double getCurrent(SubjectCollectDTO subjectCollectDTO, String s, String s1, Boolean tar) throws SerException {
        return null;
    }

    /**
     * 获取所有的项目名称
     */
    default List<String> findProjectName() throws SerException {
        return null;
    }

    /**
     * 根据一级科目和时间获取本期发生额和本年累计数
     *
     * @param firstSubject
     * @param startTime
     * @param endTime
     * @return
     * @throws SerException
     */
    default SubjectCollectBO findCurrentAndYear(String firstSubject, String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 获取所有一级科目为现金或者银行存款的数据
     */
    default List<VoucherGenerateBO> findByCourseName() throws SerException {
        return null;
    }

    /**
     * 科目汇总列表
     */
    List<FirstSubjectBO> collect(SubjectCollectsDTO dto) throws SerException;

    /**
     * 科目汇总导出
     *
     * @param to
     * @throws SerException
     */
    byte[] exportExcel(ExportSubjectCollectTO to) throws SerException;

    /**
     * 获取所有会计科目
     *
     * @return
     * @throws SerException
     */
    default List<String> findFirstSubject() throws SerException { return null;}

        /**
     * 根据科目,时间 获取本期累计
     *
     * @param firstSubject
     * @param startTime
     * @param endTime
     * @param tar          true,获取本期借方-贷方累计
     * @return
     * @throws SerException
     */
    //tar:true,获取借方,false,获取贷方
    default Double getCurrent(String firstSubject, String startTime, String endTime, Boolean tar) throws SerException {
        return null;
    }
}