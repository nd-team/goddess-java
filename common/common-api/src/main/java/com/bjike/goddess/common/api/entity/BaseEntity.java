package com.bjike.goddess.common.api.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础实体类，所有entity继承该类]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable{
    /**
     * 数据行id
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false,length = 36,updatable = false,insertable = false)
    protected String id;


    /**
     * 数据创建时间
     */
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间'", insertable = false,nullable = false)
    protected LocalDateTime createTime;

    /**
     * 上次修改时间
     */
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间' ",insertable = false,nullable = false)
    protected LocalDateTime modifyTime ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
