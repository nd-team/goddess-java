package com.bjike.goddess.democraticmeet.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 会议纪要
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryTO extends BaseTO {

    public interface TestAdd{}
    public interface TestEdit{}

    /**
     * 会议类型
     */
    @NotBlank(groups = {SummaryTO.TestEdit.class} , message = "会议类型不能为空")
    private String typeName;

    /**
     * 会议编号
     */
    private String meetNumber;
    /**
     * 会议议题id
     */
    @NotBlank(groups = {SummaryTO.TestAdd.class,SummaryTO.TestEdit.class} , message = "会议议题id不能为空")
    private String meetTitleId;

    /**
     * 实际会议时间
     */
    @NotBlank(groups = {SummaryTO.TestAdd.class,SummaryTO.TestEdit.class} , message = "实际会议时间不能为空")
    private String actualTime;

    /**
     * 实际参会人员
     */
    private String[] actualPersons;

    /**
     * 新增参会人员
     */
    private String[] newPersons;

    /**
     * 未参会人员
     */
    private String[] nonePersons;

    /**
     * 会议记录人
     */
    @NotBlank(groups = {SummaryTO.TestAdd.class,SummaryTO.TestEdit.class} , message = "会议记录人不能为空")
    private String recorder;

    /**
     * 参会人数
     */
    @NotNull(groups = {SummaryTO.TestAdd.class,SummaryTO.TestEdit.class} , message = "参会人数不能为空")
    private Double personNum;

    /**
     * 参会人
     */
    private String[] peoples;
    /**
     * 自我批评的内容
     */
    private String selfCritic;

    /**
     * 自我总结
     */
    private String selfsummery;

    /**
     * 建议表数组
     */
    private List<AdviceTableTO> adviceTableTOList;



    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMeetNumber() {
        return meetNumber;
    }

    public void setMeetNumber(String meetNumber) {
        this.meetNumber = meetNumber;
    }

    public String getMeetTitleId() {
        return meetTitleId;
    }

    public void setMeetTitleId(String meetTitleId) {
        this.meetTitleId = meetTitleId;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String[] getActualPersons() {
        return actualPersons;
    }

    public void setActualPersons(String[] actualPersons) {
        this.actualPersons = actualPersons;
    }

    public String[] getNewPersons() {
        return newPersons;
    }

    public void setNewPersons(String[] newPersons) {
        this.newPersons = newPersons;
    }

    public String[] getNonePersons() {
        return nonePersons;
    }

    public void setNonePersons(String[] nonePersons) {
        this.nonePersons = nonePersons;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public Double getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Double personNum) {
        this.personNum = personNum;
    }

    public String getSelfCritic() {
        return selfCritic;
    }

    public void setSelfCritic(String selfCritic) {
        this.selfCritic = selfCritic;
    }

    public String getSelfsummery() {
        return selfsummery;
    }

    public void setSelfsummery(String selfsummery) {
        this.selfsummery = selfsummery;
    }

    public List<AdviceTableTO> getAdviceTableTOList() {
        return adviceTableTOList;
    }

    public void setAdviceTableTOList(List<AdviceTableTO> adviceTableTOList) {
        this.adviceTableTOList = adviceTableTOList;
    }

    public String[] getPeoples() {
        return peoples;
    }

    public void setPeoples(String[] peoples) {
        this.peoples = peoples;
    }
}