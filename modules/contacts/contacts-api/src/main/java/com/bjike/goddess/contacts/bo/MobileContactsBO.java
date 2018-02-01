package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contacts.enums.ContactsStatus;
import com.bjike.goddess.user.enums.SexType;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-04 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MobileContactsBO extends BaseBO {
    /**
     * 部门
     */
    private String department;
    /**
     * 人数
     */
    private Integer peopleCount;
    /**
     * 手机端内部通讯录
     */
    private List<MobileInternalContactsBO> mobileInternalContactsBOS;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public List<MobileInternalContactsBO> getMobileInternalContactsBOS() {
        return mobileInternalContactsBOS;
    }

    public void setMobileInternalContactsBOS(List<MobileInternalContactsBO> mobileInternalContactsBOS) {
        this.mobileInternalContactsBOS = mobileInternalContactsBOS;
    }
}
