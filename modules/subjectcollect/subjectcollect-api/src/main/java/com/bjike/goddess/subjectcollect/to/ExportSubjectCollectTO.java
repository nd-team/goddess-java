package com.bjike.goddess.subjectcollect.to;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-26 15:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportSubjectCollectTO {
    /**
     * 导出开始时间
     */
    private String startTime;

    /**
     * 导出结束时间
     */
    private String endTime;

    /**
     * 会计科目
     */
    private String firstSubject;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }
}
