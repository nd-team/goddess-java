package com.bjike.goddess.bankrecords.api;

import com.bjike.goddess.bankrecords.bo.*;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.io.InputStream;
import java.util.List;

/**
 * 银行流水业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BankRecordAPI {

    /**
     * 检查导入的Excel标题
     *
     * @version v1
     */
    List<ExcelTitleBO> check(List<InputStream> inputStreams) throws SerException;

    /**
     * 导入银行流水
     *
     * @param to 银行流水
     * @return 银行流水
     */
    void upload(BankRecordTO to) throws SerException;

    /**
     * 根据id汇总银行流水
     *
     * @param id 银行流水id
     * @return 银行流水信息
     */
    BankRecordBO find(String id) throws SerException;

    /**
     * 查询记录总条数
     *
     * @param dto 查询条件
     * @return 记录总条数
     */
    Long count(BankRecordDTO dto) throws SerException;

    /**
     * 列表
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    List<BankRecordBO> pageList(BankRecordDTO dto) throws SerException;

    /**
     * 删除银行流水记录
     *
     * @param id 银行流水id
     */
    void delete(String id) throws SerException;

    /**
     * 汇总
     *
     * @param year        年份
     * @param month       月份
     * @param accountName 账户名称
     * @return 汇总结果集
     */
    List<BankRecordCollectBO> collect(Integer year, Integer month, String accountName) throws SerException;

    /**
     * 分析
     *
     * @param year        年份
     * @param month       月份
     * @param accountName 账户名称
     * @return 汇总结果集
     */
    BankRecordAnalyzeBO analyze(Integer year, Integer month, String accountName) throws SerException;

    /**
     * 对比
     *
     * @param year  年份
     * @param month 月份
     * @return 对比结果
     */
    BankRecordCompareBO compare(Integer year, Integer month) throws SerException;

    /**
     * 根据条件查询列表信息
     *
     * @param year 年份
     * @param month 月份
     * @param number 账号
     * @version v1
     */
    List<BankRecordBO> findByCondition(Integer year,Integer month ,String number) throws SerException;

    /**
     * 根据条件汇总
     *
     * @param year 年份
     * @param month 月份
     * @param number 账号
     * @version v1
     */
    List<BankRecordCollectBO> collectByCondition(Integer year,Integer month ,String number) throws SerException;

    /**
     * 根据id查询银行流水
     *
     * @param id 银行流水id
     * @return 银行流水信息
     */
    BankRecordPageListBO findById(String id) throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
}