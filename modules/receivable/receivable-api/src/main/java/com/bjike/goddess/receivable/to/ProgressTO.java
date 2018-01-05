package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-6-7.
 */
public class ProgressTO extends BaseTO {
    /**
     * 分组
     */
    private String group;
    /**
     * 结算进度
     */
    private String progress;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
