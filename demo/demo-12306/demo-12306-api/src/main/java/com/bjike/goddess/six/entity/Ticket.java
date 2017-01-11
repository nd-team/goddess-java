package com.bjike.goddess.six.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bjike.goddess.dbs.common.entity.BaseEntity;

@Entity
@Table(name = "demo_ticket")
public class Ticket extends BaseEntity {

	/**
	 * 身份证
	 */
	private String owner;//动车票所属者
	/**
	 * 座位号 D3608-2-2D
	 */
	private String position;
	/**
	 * 出发时间
	 */
	private LocalDateTime offTime;
	/**
	 * 购票金额
	 */
	private Integer money;
	/**
	 * 购票转帐卡号
	 */
	private String card;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public LocalDateTime getOffTime() {
		return offTime;
	}
	public void setOffTime(LocalDateTime offTime) {
		this.offTime = offTime;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	
	
	
	
}
