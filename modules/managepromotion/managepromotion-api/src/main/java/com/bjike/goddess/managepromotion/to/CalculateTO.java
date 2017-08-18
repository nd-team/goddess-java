package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 计算
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 计算 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CalculateTO extends BaseTO {

    /**
     * 金额
     */
    private Integer money;

    /**
     * 主项技能定位-专业
     */
    private String main;
    /**
     * 小项1 技能定位-专业
     */
    private String eventFirst;
    /**
     * 小项2 技能定位-专业
     */
    private String eventSecond;
    /**
     * 小项3 技能定位-专业
     */
    private String eventThird;
    /**
     * 小项4 技能定位-专业
     */
    private String eventFour;
    /**
     * 小项5 技能定位-专业
     */
    private String eventFive;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getEventFirst() {
        return eventFirst;
    }

    public void setEventFirst(String eventFirst) {
        this.eventFirst = eventFirst;
    }

    public String getEventSecond() {
        return eventSecond;
    }

    public void setEventSecond(String eventSecond) {
        this.eventSecond = eventSecond;
    }

    public String getEventThird() {
        return eventThird;
    }

    public void setEventThird(String eventThird) {
        this.eventThird = eventThird;
    }

    public String getEventFour() {
        return eventFour;
    }

    public void setEventFour(String eventFour) {
        this.eventFour = eventFour;
    }

    public String getEventFive() {
        return eventFive;
    }

    public void setEventFive(String eventFive) {
        this.eventFive = eventFive;
    }
}