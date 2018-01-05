package com.bjike.goddess.democraticmeet.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.democraticmeet.entity.MeetDesign;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 民主生活会议组织内容
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemocraticContentTO extends BaseTO {

    public interface testAdd{}
    public interface testEdit{}

    /**
     * 会议类型
     */
    @NotBlank(groups = {DemocraticContentTO.testEdit.class} , message = "会议类型不能为空")
    private String meetType;

    /**
     * 会议议题
     */
    @NotBlank(groups = {DemocraticContentTO.testAdd.class,DemocraticContentTO.testEdit.class} , message = "会议议题不能为空")
    private String meetTitle;

    /**
     * 会议内容
     */
    @NotBlank(groups = {DemocraticContentTO.testAdd.class,DemocraticContentTO.testEdit.class} , message = "会议内容不能为空")
    private String meetContent;


    /**
     * 备注
     */
    private String remark;


    /**
     * 会议组织部分内容数组
     */
    private List<MeetDesignTO> meetDesignTOList;


    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
    }

    public String getMeetContent() {
        return meetContent;
    }

    public void setMeetContent(String meetContent) {
        this.meetContent = meetContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public List<MeetDesignTO> getMeetDesignTOList() {
        return meetDesignTOList;
    }

    public void setMeetDesignTOList(List<MeetDesignTO> meetDesignTOList) {
        this.meetDesignTOList = meetDesignTOList;
    }
}