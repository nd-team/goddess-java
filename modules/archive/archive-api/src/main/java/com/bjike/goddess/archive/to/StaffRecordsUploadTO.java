package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 员工档案附件上传
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案附件上传 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRecordsUploadTO implements Serializable {

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 附件类型
     */
    @NotNull(message = "附件类型不能为空", groups = {ADD.class, EDIT.class})
    private String enclosure;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }
}