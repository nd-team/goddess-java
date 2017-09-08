package com.bjike.goddess.organize.vo;

import java.util.List;

/**
 * Created by ike on 17-9-7.
 */
public class ReArrangementVO {
    /**
     * id
     */
    private String id;
    /**
     * 岗位层级
     */
    private String arrangement;
    /**
     * 岗位详情
     */
    private List<RePositionVO> positionS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RePositionVO> getPositionS() {
        return positionS;
    }

    public void setPositionS(List<RePositionVO> positionS) {
        this.positionS = positionS;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }
}
