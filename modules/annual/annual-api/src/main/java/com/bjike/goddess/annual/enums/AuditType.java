package com.bjike.goddess.annual.enums;

/**
 * 审核类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 16:14:41 ]
 * @Description: [ 审核类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public enum AuditType {
	NONE(0),//未处理
	ALLOWED(1)//通过
	, DENIED(2)//拒绝
	;

	private int value;

	private AuditType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
