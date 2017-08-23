package com.bjike.goddess.dispatchcar.vo;

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
public class AuditDetailVO {

    /**
     * 出车信息
     */
    private DispatchInfo info;

    /**
     * 审核详情
     */
    private List<AuditResult> list;

    public DispatchInfo getInfo() {
        return info;
    }

    public void setInfo(DispatchInfo info) {
        this.info = info;
    }

    public List<AuditResult> getList() {
        return list;
    }

    public void setList(List<AuditResult> list) {
        this.list = list;
    }
}
