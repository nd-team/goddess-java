package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.taskallotment.enums.TaskType;

/**
 * 分配及确认汇总最底层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 17:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ConfirmLastBO extends BaseBO {
    /**
     * 任务类型
     */
    private TaskType taskType;
    /**
     * 已分配
     */
    private Long haveInitiate;
    /**
     * 未分配
     */
    private Long noInitiate;
    /**
     * 确认接收量
     */
    private Long confirm;
    /**
     * 未接收任务量
     */
    private Long unConfirm;
    /**
     * 待确认接收量
     */
    private Long toConfirm;

    public ConfirmLastBO() {
    }

    public ConfirmLastBO(Long haveInitiate, Long noInitiate, Long confirm, Long unConfirm, Long toConfirm) {
        this.haveInitiate = haveInitiate;
        this.noInitiate = noInitiate;
        this.confirm = confirm;
        this.unConfirm = unConfirm;
        this.toConfirm = toConfirm;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Long getHaveInitiate() {
        return haveInitiate;
    }

    public void setHaveInitiate(Long haveInitiate) {
        this.haveInitiate = haveInitiate;
    }

    public Long getNoInitiate() {
        return noInitiate;
    }

    public void setNoInitiate(Long noInitiate) {
        this.noInitiate = noInitiate;
    }

    public Long getConfirm() {
        return confirm;
    }

    public void setConfirm(Long confirm) {
        this.confirm = confirm;
    }

    public Long getUnConfirm() {
        return unConfirm;
    }

    public void setUnConfirm(Long unConfirm) {
        this.unConfirm = unConfirm;
    }

    public Long getToConfirm() {
        return toConfirm;
    }

    public void setToConfirm(Long toConfirm) {
        this.toConfirm = toConfirm;
    }
}
