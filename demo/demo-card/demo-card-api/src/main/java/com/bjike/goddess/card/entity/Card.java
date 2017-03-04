package com.bjike.goddess.card.entity;


import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 实体对象
 */
@Entity
@Table(name = "demo_card")
public class Card extends BaseEntity {

    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空")
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 余额
     */
    private Long money;
    /**
     * 卡创建时间
     */
    private LocalDateTime createTime;

    public Card() {
    }

    public Card(String account, String password, Long money) {
        this.account = account;
        this.password = password;
        this.money = money;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public Long getMoney() {
        return money;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
