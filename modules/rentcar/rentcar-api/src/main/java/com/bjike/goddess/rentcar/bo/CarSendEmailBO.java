package com.bjike.goddess.rentcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 测试业务传输对象
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 测试业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarSendEmailBO extends BaseBO {
    /**
     * 商务人员和项目经理的id
     */
    private String[] positionNameId;
    /**
     * 部门id
     */
    private String projectManagerId;

    public String[] getPositionNameId() {
        return positionNameId;
    }

    public void setPositionNameId(String[] positionNameId) {
        this.positionNameId = positionNameId;
    }

    public String getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(String projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
}