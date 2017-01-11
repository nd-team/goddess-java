package com.bjike.goddess.card.entity;

import com.bjike.goddess.dbs.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "demo_card")
public class Card extends BaseEntity {

	private String account;
	private String password;
	private Long money;


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
}
