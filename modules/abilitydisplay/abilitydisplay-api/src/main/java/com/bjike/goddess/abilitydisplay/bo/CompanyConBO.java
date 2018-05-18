package com.bjike.goddess.abilitydisplay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 公司联系人业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-05 09:00 ]
 * @Description: [ 公司联系人业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyConBO extends BaseBO {

    /**
     * 公司联系人
     */
    private String name;

    /**
     * 所在地区
     */
    private String area;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 岗位
     */
    private String post;

    /**
     * 籍贯
     */
    private String placeORI;

    /**
     * 工作经历
     */
    private String workEXP;

    /**
     * 求学与培训经历
     */
    private String learnEXP;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 性格评价
     */
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
        return "CompanyConBO{" +
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