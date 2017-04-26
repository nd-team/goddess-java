package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 岗位工作详细展示对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PositionWorkDetailTO extends BaseTO {

    /**
     * 岗位说明书ID
     */
    @NotNull(message = "岗位说明书ID不能为空", groups = {ADD.class, EDIT.class})
    private String instructionId;

    /**
     * 是否有模板
     */
    @NotNull(message = "是否有模板不能为空", groups = {ADD.class, EDIT.class})
    private Boolean hasMould;

    /**
     * 模板存储位置
     */
    private String mouldStorage;

    /**
     * 工作文件存储位置
     */
    private String paperStorage;

    /**
     * 经验总结
     */
    private String summarize;

    /**
     * 公示对象&联系方式
     */
    private String contact;

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public Boolean getHasMould() {
        return hasMould;
    }

    public void setHasMould(Boolean hasMould) {
        this.hasMould = hasMould;
    }

    public String getMouldStorage() {
        return mouldStorage;
    }

    public void setMouldStorage(String mouldStorage) {
        this.mouldStorage = mouldStorage;
    }

    public String getPaperStorage() {
        return paperStorage;
    }

    public void setPaperStorage(String paperStorage) {
        this.paperStorage = paperStorage;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
