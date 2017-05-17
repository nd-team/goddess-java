package com.bjike.goddess.materialbuy.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 设备类型
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 03:39 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DeviceTypeTO extends BaseTO {

    public interface DeviceTypeAdd{}
    public interface DeviceTypeEdit{}

    /**
     * 类型
     */
    @NotBlank(groups = {DeviceTypeAdd.class, DeviceTypeEdit.class}, message = "设备类型不能为空")
    private String type;

    /**
     * 科目
     */
    private String subject;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}