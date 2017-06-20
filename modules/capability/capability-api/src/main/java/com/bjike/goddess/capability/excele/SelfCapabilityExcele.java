package com.bjike.goddess.capability.excele;

import com.bjike.goddess.capability.enums.CompletePro;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午4:38
 * @Description: [个人能力展示]
 * @Version: [1.0.0]
 */
public class SelfCapabilityExcele extends BaseTO{

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名",notNull = true)
    private String name;

    /**
     * 个人资质
     */
    @ExcelHeader(name = "个人资质",notNull = true)
    private String capacity;

    /**
     * 个人职称
     */
    @ExcelHeader(name = "个人职称",notNull = true)
    private String selfJobTitle;

    /**
     * 岗位职称
     */
    @ExcelHeader(name = "岗位职称",notNull = true)
    private String positionTitle;

    /**
     * 工作年限
     */
    @ExcelHeader(name = "工作年限",notNull = true)
    private String workYear;

    /**
     * 个人经手项目
     */
    @ExcelHeader(name = "个人经手项目",notNull = true)
    private String selfProject;

    /**
     * 个人社交资源姓名
     */
    @ExcelHeader(name = "个人社交资源姓名",notNull = true)
    private String contactName;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSelfJobTitle() {
        return selfJobTitle;
    }

    public void setSelfJobTitle(String selfJobTitle) {
        this.selfJobTitle = selfJobTitle;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getSelfProject() {
        return selfProject;
    }

    public void setSelfProject(String selfProject) {
        this.selfProject = selfProject;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


}
