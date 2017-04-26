package com.bjike.goddess.materialbuy.vo;

/**
 * 设备类型表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 03:39 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DeviceTypeVO {

    /**
     * id
     */
    private String id;
    /**
     * 类型
     */
    private String type;

    /**
     * 科目
     */
    private String subject;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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