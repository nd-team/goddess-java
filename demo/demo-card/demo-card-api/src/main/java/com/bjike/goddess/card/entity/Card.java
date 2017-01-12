package com.bjike.goddess.card.entity;


import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "demo_card")
public class Card extends BaseEntity {

	private String account;
	private String password;
	private Long money;

	public Card(){}
	public Card(String account,String password,Long money){
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
}
