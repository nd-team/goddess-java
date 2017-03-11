package com.bjike.goddess.staffentry.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.staffentry.entity.EntryRegister;

import javax.persistence.*;
import java.util.List;

/**
 * 家庭成员业务传输对象
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 14:32]
 * @Description: [家庭成员业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FamilyMemberBO extends BaseBO {

    /**
     * 称谓
     */
    private String title;

    /**
     *姓名
     */
    private String name;

    /**
     *年龄
     */
    private Integer age;

    /**
     *单位
     */
    private String unit;

    /**
     *职务
     */
    private String position;

    /**
     *联系方式
     */
    private String phone;

    /**
     * 入职登记外键
     */
    private EntryRegister entryRegister;


    /**
     * 称谓集合
     */
    private List<String> titles;

    /**
     *姓名集合
     */
    private List<String> names;

    /**
     *年龄集合
     */
    private List<Integer> ages;

    /**
     *单位集合
     */
    private List<String> units;

    /**
     *职务集合
     */
    private List<String> positions;

    /**
     *联系方式集合
     */
    private List<String> phones;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EntryRegister getEntryRegister() {
        return entryRegister;
    }

    public void setEntryRegister(EntryRegister entryRegister) {
        this.entryRegister = entryRegister;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Integer> getAges() {
        return ages;
    }

    public void setAges(List<Integer> ages) {
        this.ages = ages;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
