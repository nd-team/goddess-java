package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 会议讨论意见
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 03:10 ]
 * @Description: [ 会议讨论意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingDiscussionTO extends BaseTO {

    /**
     * 一轮意见
     */
    @NotBlank(message = "一轮意见不能为空",groups = {ADD.class, EDIT.class})
    private String firstDis;

    /**
     * 二轮意见
     */
    @NotBlank(message = "二轮意见不能为空",groups = {ADD.class, EDIT.class})
    private String secondDis;

    /**
     * 发言人
     */
    @NotBlank(message = "发言人不能为空",groups = {ADD.class, EDIT.class})
    private String user;

    /**
     * 发言人员工编号
     */
    @NotBlank(message = "发言人员工编号不能为空",groups = {ADD.class, EDIT.class})
    private String userNum;

    /**
     * 纪要Id
     */
    @NotBlank(message = "纪要Id不能为空",groups = {ADD.class, EDIT.class})
    private String summaryId;


    public String getFirstDis() {
        return firstDis;
    }

    public void setFirstDis(String firstDis) {
        this.firstDis = firstDis;
    }

    public String getSecondDis() {
        return secondDis;
    }

    public void setSecondDis(String secondDis) {
        this.secondDis = secondDis;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}