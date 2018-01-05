package com.bjike.goddess.organize.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionWorkDetailsExport implements Serializable {

    /**
     * 排序
     */
    @ExcelHeader(name = "序号", notNull = true)
    private Integer seqNum; 

    /**
     * 公司目标
     */
    @ExcelHeader(name = "公司目标", notNull = true)
    private String goals;

    /**
     * 公司
     */
    @ExcelHeader(name = "公司", notNull = true)
    private String company;

    /**
     * -------------33---------
     */
    /**
     * （对接）规划模块名
     */
    @ExcelHeader(name = "（对接）规划模块id", notNull = true)
    private String id1;
    /**
     * （对接）规划模块名
     */
    @ExcelHeader(name = "（对接）规划模块名", notNull = true)
    private String name1;
    /**
     * （对接）规划模块
     */
    @ExcelHeader(name = "（对接）规划模块", notNull = true)
    private Boolean hasConnet1;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "规划指标序号", notNull = true)
    private String number1;

    //-----------------------（对接）福利模块---------19---52-----

    /**
     * （对接）福利模块名
     */
    @ExcelHeader(name = "（对接）福利模块id", notNull = true)
    private String id2;
    /**
     * （对接）福利模块
     */
    @ExcelHeader(name = "（对接）福利模块名", notNull = true)
    private String name2;
    /**
     * （对接）福利模块
     */
    @ExcelHeader(name = "（对接）福利模块", notNull = true)
    private Boolean hasConnet2;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "福利指标序号", notNull = true)
    private String number2;


    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public Boolean getHasConnet1() {
        return hasConnet1;
    }

    public void setHasConnet1(Boolean hasConnet1) {
        this.hasConnet1 = hasConnet1;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Boolean getHasConnet2() {
        return hasConnet2;
    }

    public void setHasConnet2(Boolean hasConnet2) {
        this.hasConnet2 = hasConnet2;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }
}