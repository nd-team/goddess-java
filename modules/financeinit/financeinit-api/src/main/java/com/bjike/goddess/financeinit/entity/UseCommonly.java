package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.UseComm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 常用摘要
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_usecommonly")
public class UseCommonly extends BaseEntity {

    /**
     * 常用摘要
     */
    @Column(name = "useComm", nullable = false,unique = true, columnDefinition = "TINYINT(2)   COMMENT '常用摘要'")
    private UseComm useComm;


    public UseComm getUseComm() {
        return useComm;
    }

    public void setUseComm(UseComm useComm) {
        this.useComm = useComm;
    }
}