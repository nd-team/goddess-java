package com.bjike.goddess.bidding.enums;

/**
 * 招投标类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-3-20]
 * @Description: [招投标类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum BiddingType {
    /**
     * 邀请招标
     */
    INVITEDTENDERING(0),
    /**
     * 公开招标
     */
    OPENTENDERING(1);

    private int code;

    BiddingType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static BiddingType getEnumConvert(int code) {
        BiddingType biddingType = BiddingType.INVITEDTENDERING;
        if (code == BiddingType.INVITEDTENDERING.getCode()) {
            biddingType = BiddingType.INVITEDTENDERING;
        }
        if (code == BiddingType.OPENTENDERING.getCode()) {
            biddingType = BiddingType.OPENTENDERING;
        }
        return biddingType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == BiddingType.INVITEDTENDERING.getCode()) {
            name = "邀请招标";
        }
        if (code == BiddingType.OPENTENDERING.getCode()) {
            name = "公开招标";
        }
        return name;
    }
}
