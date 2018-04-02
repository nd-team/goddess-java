package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公司联系人
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-05 09:00 ]
 * @Description: [ 公司联系人 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "abilitydisplay_companycon")
public class CompanyCon extends BaseEntity {

    /**
     * 公司联系人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司联系人'")
    private String name;

    /**
     * 所在地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所在地区'")
    private String area;

    /**
     * 联系电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String phone;

    /**
     * 岗位
     */
    @Column(name = "post", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String post;

    /**
     * 籍贯
     */
    @Column(name = "placeORI", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '籍贯'")
    private String placeORI;

    /**
     * 工作经历
     */
    @Column(name = "workEXP", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作经历'")
    private String workEXP;

    /**
     * 求学与培训经历
     */
    @Column(name = "learnEXP", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '求学与培训经历'")
    private String learnEXP;

    /**
     * 爱好
     */
    @Column(name = "hobby", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '爱好'")
    private String hobby;

    /**
     * 性格评价
     */
    @Column(name = "characterEVA", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '性格评价'")
    private String characterEVA;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPlaceORI() {
        return placeORI;
    }

    public void setPlaceORI(String placeORI) {
        this.placeORI = placeORI;
    }

    public String getWorkEXP() {
        return workEXP;
    }

    public void setWorkEXP(String workEXP) {
        this.workEXP = workEXP;
    }

    public String getLearnEXP() {
        return learnEXP;
    }

    public void setLearnEXP(String learnEXP) {
        this.learnEXP = learnEXP;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCharacterEVA() {
        return characterEVA;
    }

    public void setCharacterEVA(String characterEVA) {
        this.characterEVA = characterEVA;
    }

    @Override
    public String toString() {
        return "CompanyCon{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", phone='" + phone + '\'' +
                ", post='" + post + '\'' +
                ", placeORI='" + placeORI + '\'' +
                ", workEXP='" + workEXP + '\'' +
                ", learnEXP='" + learnEXP + '\'' +
                ", hobby='" + hobby + '\'' +
                ", characterEVA='" + characterEVA + '\'' +
                '}';
    }
}