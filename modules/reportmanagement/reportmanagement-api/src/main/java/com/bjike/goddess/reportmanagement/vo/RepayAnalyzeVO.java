package com.bjike.goddess.reportmanagement.vo;

/**
 * 偿还能力分析
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-26 16:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RepayAnalyzeVO {
    /**
     * 项目
     */
    private String project;
    /**
     * 比例
     */
    private String scale;
    /**
     * 最佳比例
     */
    private String bestScale;
    /**
     * 说明
     */
    private String explain;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getBestScale() {
        return bestScale;
    }

    public void setBestScale(String bestScale) {
        this.bestScale = bestScale;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
