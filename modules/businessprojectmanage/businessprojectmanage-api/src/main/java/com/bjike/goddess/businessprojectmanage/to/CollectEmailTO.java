package com.bjike.goddess.businessprojectmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-13 16:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectEmailTO extends BaseTO {

    /** 内容　*/
    private String content;

    /** 邮箱帐号　*/
    private  String account;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
