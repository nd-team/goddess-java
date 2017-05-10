package com.bjike.goddess.projectissuehandle.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Map;

/**
 * 汇总项目执行中的问题受理及处理结果业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-27 10:49 ]
 * @Description: [ 汇总项目执行中的问题受理及处理结果业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectBO extends BaseBO {
    /**
     * 合同外部项目名称
     */
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    private String internalProjectName;

    public String getExternalContractProjectName() {
        return externalContractProjectName;
    }

    public void setExternalContractProjectName(String externalContractProjectName) {
        this.externalContractProjectName = externalContractProjectName;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    /**
     * 地区
     */
    private String area;

    /**
     * 描述
     */
    private String remark;
    /**
     * 个数
     */
    private int counts;
    /**
     * 数据库枚举转换
     */
    private int enumConvert;
    /**
     * 地区汇总集合
     */
    private List<Map<String, String>> areaMap;

    /**
     * 问题类型汇总集合
     */
    private List<Map<String, String>> problemTypes;
    /**
     * 问题紧急程度汇总集合
     */
    private List<Map<String, String>> problemEmergencyDegree;
    /**
     * 问题对象汇总集合
     */
    private List<Map<String, String>> problemObject;
    /**
     * 问题处理结果汇总集合
     */
    private List<Map<String, String>> problemProcessingResult;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getEnumConvert() {
        return enumConvert;
    }

    public void setEnumConvert(int enumConvert) {
        this.enumConvert = enumConvert;
    }

    public List<Map<String, String>> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(List<Map<String, String>> areaMap) {
        this.areaMap = areaMap;
    }

    public List<Map<String, String>> getProblemTypes() {
        return problemTypes;
    }

    public void setProblemTypes(List<Map<String, String>> problemTypes) {
        this.problemTypes = problemTypes;
    }

    public List<Map<String, String>> getProblemEmergencyDegree() {
        return problemEmergencyDegree;
    }

    public void setProblemEmergencyDegree(List<Map<String, String>> problemEmergencyDegree) {
        this.problemEmergencyDegree = problemEmergencyDegree;
    }

    public List<Map<String, String>> getProblemObject() {
        return problemObject;
    }

    public void setProblemObject(List<Map<String, String>> problemObject) {
        this.problemObject = problemObject;
    }

    public List<Map<String, String>> getProblemProcessingResult() {
        return problemProcessingResult;
    }

    public void setProblemProcessingResult(List<Map<String, String>> problemProcessingResult) {
        this.problemProcessingResult = problemProcessingResult;
    }
}