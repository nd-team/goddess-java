package com.bjike.goddess.rotation.enums;

/**
 * 岗位轮换明细汇总类型
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-03-27 16:14:41 ]
 * @Description: [ 岗位轮换明细汇总类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public enum CollectDetailsType {
	/**
	 * 管理层
	 */
	MANAGER(1),

	/**
	 * 执行层
	 */
	DECISION(2)
	;

	private int value;

	private CollectDetailsType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
