package com.bjike.goddess.financeinit.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


/**
 * 会计科目导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountanCourseExport extends BaseBO {


    /**
     * 一级科目代码
     */
    @ExcelHeader(name="一级科目代码",notNull = true)
    private String oneCode;

    /**
     * 一级科目会计科目名称
     */
    @ExcelHeader(name="一级会计科目名称",notNull = true)
    private String oneAccountanName;

    /**
     * 一级科目所属类别
     */
    @ExcelHeader(name="一级科目所属类别",notNull = true)
    private CategoryName oneBelongCategory;

    /**
     * 一级科目余额方向
     */
    @ExcelHeader(name="一级科目余额方向",notNull = true)
    private BalanceDirection oneBalanceDirection;

    /**
     * 二级科目代码
     */
    @ExcelHeader(name="二级科目代码",notNull = true)
    private String sendCode;

    /**
     * 二级科目会计科目名称
     */
    @ExcelHeader(name="二级会计科目名称",notNull = true)
    private String sendAccountanName;

    /**
     * 二级科目所属类别
     */
    @ExcelHeader(name="二级科目所属类别",notNull = true)
    private CategoryName sendBelongCategory;

    /**
     * 二级科目余额方向
     */
    @ExcelHeader(name="二级科目余额方向",notNull = true)
    private BalanceDirection sendBalanceDirection;

    /**
     * 三级科目代码
     */
    @ExcelHeader(name="三级科目代码",notNull = true)
    private String thirdCode;

    /**
     * 三级科目会计科目名称
     */
    @ExcelHeader(name="三级会计科目名称",notNull = true)
    private String thirdAccountanName;

    /**
     * 三级科目所属类别
     */
    @ExcelHeader(name="三级科目所属类别",notNull = true)
    private CategoryName thirdBelongCategory;

    /**
     * 三级科目余额方向
     */
    @ExcelHeader(name="三级科目余额方向",notNull = true)
    private BalanceDirection thirdBalanceDirection;

    public String getOneCode() {
        return oneCode;
    }

    public void setOneCode(String oneCode) {
        this.oneCode = oneCode;
    }

    public String getOneAccountanName() {
        return oneAccountanName;
    }

    public void setOneAccountanName(String oneAccountanName) {
        this.oneAccountanName = oneAccountanName;
    }

    public CategoryName getOneBelongCategory() {
        return oneBelongCategory;
    }

    public void setOneBelongCategory(CategoryName oneBelongCategory) {
        this.oneBelongCategory = oneBelongCategory;
    }

    public BalanceDirection getOneBalanceDirection() {
        return oneBalanceDirection;
    }

    public void setOneBalanceDirection(BalanceDirection oneBalanceDirection) {
        this.oneBalanceDirection = oneBalanceDirection;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    public String getSendAccountanName() {
        return sendAccountanName;
    }

    public void setSendAccountanName(String sendAccountanName) {
        this.sendAccountanName = sendAccountanName;
    }

    public CategoryName getSendBelongCategory() {
        return sendBelongCategory;
    }

    public void setSendBelongCategory(CategoryName sendBelongCategory) {
        this.sendBelongCategory = sendBelongCategory;
    }

    public BalanceDirection getSendBalanceDirection() {
        return sendBalanceDirection;
    }

    public void setSendBalanceDirection(BalanceDirection sendBalanceDirection) {
        this.sendBalanceDirection = sendBalanceDirection;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getThirdAccountanName() {
        return thirdAccountanName;
    }

    public void setThirdAccountanName(String thirdAccountanName) {
        this.thirdAccountanName = thirdAccountanName;
    }

    public CategoryName getThirdBelongCategory() {
        return thirdBelongCategory;
    }

    public void setThirdBelongCategory(CategoryName thirdBelongCategory) {
        this.thirdBelongCategory = thirdBelongCategory;
    }

    public BalanceDirection getThirdBalanceDirection() {
        return thirdBalanceDirection;
    }

    public void setThirdBalanceDirection(BalanceDirection thirdBalanceDirection) {
        this.thirdBalanceDirection = thirdBalanceDirection;
    }


}