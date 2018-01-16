package com.bjike.goddess.financeinit.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;


/**
 * 会计科目导出模板
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目导出模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountanCourseExportTemple extends BaseBO {
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
    private String oneBelongCategory;

    /**
     * 一级科目余额方向
     */
    @ExcelHeader(name="一级科目余额方向",notNull = true)
    private String oneBalanceDirection;

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
    private String sendBelongCategory;

    /**
     * 二级科目余额方向
     */
    @ExcelHeader(name="二级科目余额方向",notNull = true)
    private String sendBalanceDirection;

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
    private String thirdBelongCategory;

    /**
     * 三级科目余额方向
     */
    @ExcelHeader(name="三级科目余额方向",notNull = true)
    private String thirdBalanceDirection;

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

    public String getOneBelongCategory() {
        return oneBelongCategory;
    }

    public void setOneBelongCategory(String oneBelongCategory) {
        this.oneBelongCategory = oneBelongCategory;
    }

    public String getOneBalanceDirection() {
        return oneBalanceDirection;
    }

    public void setOneBalanceDirection(String oneBalanceDirection) {
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

    public String getSendBelongCategory() {
        return sendBelongCategory;
    }

    public void setSendBelongCategory(String sendBelongCategory) {
        this.sendBelongCategory = sendBelongCategory;
    }

    public String getSendBalanceDirection() {
        return sendBalanceDirection;
    }

    public void setSendBalanceDirection(String sendBalanceDirection) {
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

    public String getThirdBelongCategory() {
        return thirdBelongCategory;
    }

    public void setThirdBelongCategory(String thirdBelongCategory) {
        this.thirdBelongCategory = thirdBelongCategory;
    }

    public String getThirdBalanceDirection() {
        return thirdBalanceDirection;
    }

    public void setThirdBalanceDirection(String thirdBalanceDirection) {
        this.thirdBalanceDirection = thirdBalanceDirection;
    }
}