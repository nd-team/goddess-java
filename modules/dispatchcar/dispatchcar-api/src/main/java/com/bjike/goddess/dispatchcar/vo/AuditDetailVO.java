package com.bjike.goddess.dispatchcar.vo;

import com.bjike.goddess.dispatchcar.bean.AuditResult;
import com.bjike.goddess.dispatchcar.bean.DispatchInfo;
import com.bjike.goddess.dispatchcar.bo.DispatchCarInfoBO;

import java.util.List;

/**
 * 出车记录审核详情
 *
 * @Author: [Jason]
 * @Date: [17-4-14 上午9:24]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuditDetailVO {

    /**
     * 出车信息
     */
    private DispatchCarInfoBO dispatchCarInfo;

    /**
     * 审核详情
     */
    private List<AuditResult> list;

    public DispatchCarInfoBO getDispatchCarInfo() {
        return dispatchCarInfo;
    }

    public void setDispatchCarInfo(DispatchCarInfoBO dispatchCarInfo) {
        this.dispatchCarInfo = dispatchCarInfo;
    }

    public List<AuditResult> getList() {
        return list;
    }

    public void setList(List<AuditResult> list) {
        this.list = list;
    }
}
