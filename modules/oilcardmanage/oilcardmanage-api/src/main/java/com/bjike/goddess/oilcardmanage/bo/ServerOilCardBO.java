package com.bjike.goddess.oilcardmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
/**
* 旧服务器上的油卡信息业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 09:24 ]
* @Description:	[ 旧服务器上的油卡信息业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ServerOilCardBO extends BaseBO { 

/**
* 编号
*/
 private String  number; 

/**
* 卡号
*/
 private String  cardNum; 

/**
* 主卡/副卡
*/
 private String  hostCard; 

/**
* 所属主卡
*/
 private String  mainCard; 

/**
* 办理人
*/
 private String  checker; 

/**
* 办理日期
*/
 private String  checkDate; 

/**
* 密码
*/
 private String  password; 

/**
* 期初金额
*/
 private Double  money; 

/**
* 余额
*/
 private Double  balance; 

/**
* 地区
*/
 private String  area; 

/**
* 项目名称
*/
 private String  project; 

/**
* 使用状态更新时间
*/
 private String  editDate; 

/**
* 状态
*/
 private Status state;



 public String getNumber () { 
 return number;
 } 
 public void setNumber (String number ) { 
 this.number = number ; 
 } 
 public String getCardNum () { 
 return cardNum;
 } 
 public void setCardNum (String cardNum ) { 
 this.cardNum = cardNum ; 
 } 
 public String getHostCard () { 
 return hostCard;
 } 
 public void setHostCard (String hostCard ) { 
 this.hostCard = hostCard ; 
 } 
 public String getMainCard () { 
 return mainCard;
 } 
 public void setMainCard (String mainCard ) { 
 this.mainCard = mainCard ; 
 } 
 public String getChecker () { 
 return checker;
 } 
 public void setChecker (String checker ) { 
 this.checker = checker ; 
 } 
 public String getCheckDate () { 
 return checkDate;
 } 
 public void setCheckDate (String checkDate ) { 
 this.checkDate = checkDate ; 
 } 
 public String getPassword () { 
 return password;
 } 
 public void setPassword (String password ) { 
 this.password = password ; 
 } 
 public Double getMoney () { 
 return money;
 } 
 public void setMoney (Double money ) { 
 this.money = money ; 
 } 
 public Double getBalance () { 
 return balance;
 } 
 public void setBalance (Double balance ) { 
 this.balance = balance ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getProject () { 
 return project;
 } 
 public void setProject (String project ) { 
 this.project = project ; 
 } 
 public String getEditDate () { 
 return editDate;
 } 
 public void setEditDate (String editDate ) { 
 this.editDate = editDate ; 
 } 
 public Status getState () { 
 return state;
 } 
 public void setState (Status state ) { 
 this.state = state ; 
 } 
 }