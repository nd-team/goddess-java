package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.dispatchcar.bean.AuditResult;
import com.bjike.goddess.dispatchcar.bean.DispatchInfo;

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
public class AuditDetailBO extends BaseBO {

    /**
     * 出车信息
     */
    private DispatchCarInfoBO dispatchCarInfo;

//    private DispatchInfo info;

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
