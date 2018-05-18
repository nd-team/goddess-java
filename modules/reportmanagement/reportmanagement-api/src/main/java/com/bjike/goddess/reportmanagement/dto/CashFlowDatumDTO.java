package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
/**
* 补充资料数据传输对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-22 11:54 ]
* @Description:	[ 补充资料数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CashFlowDatumDTO extends BaseDTO {
    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 资料
     */
    private String data;

    /**
     * 行次
     */
    private Integer num;

    /**
     * 金额
     */
    private Double money;

 public String getStartTime() {
  return startTime;
 }

 public void setStartTime(String startTime) {
  this.startTime = startTime;
 }

 public String getEndTime() {
  return endTime;
 }

 public void setEndTime(String endTime) {
  this.endTime = endTime;
 }

 public String getData() {
  return data;
 }

 public void setData(String data) {
  this.data = data;
 }

 public Integer getNum() {
  return num;
 }

 public void setNum(Integer num) {
  this.num = num;
 }

 public Double getMoney() {
  return money;
 }

 public void setMoney(Double money) {
  this.money = money;
 }

 @Override
 public String toString() {
  return "CashFlowDatumDTO{" +
          "startTime='" + startTime + '\'' +
          ", endTime='" + endTime + '\'' +
          ", data='" + data + '\'' +
          ", num=" + num +
          ", money=" + money +
          '}';
 }
}