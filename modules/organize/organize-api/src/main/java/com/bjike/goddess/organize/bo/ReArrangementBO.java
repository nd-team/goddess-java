package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * Created by ike on 17-9-7.
 */
public class ReArrangementBO extends BaseBO{
    /**
     * 岗位层级
     */
    private String arrangement;
    /**
     * 岗位详情
     */
    private List<RePositionBO> positionS;

    public List<RePositionBO> getPositionS() {
        return positionS;
    }

    public void setPositionS(List<RePositionBO> positionS) {
        this.positionS = positionS;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }
}
