package com.bjike.goddess.businessevaluate.enums;

/**
 * 错误情况
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午2:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ErrorCase {

    /**
     * 资料违约
     */
    DATUMRENEGE(0),
    /**
     * 工程质量违检违约
     */
    PROJECTQUALITY(1),
    /**
     * 人员违约
     */
    MANRENEGE(2),
    /**
     * 其他
     */
    ANOTHER(3);

    private int code;

    ErrorCase(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
