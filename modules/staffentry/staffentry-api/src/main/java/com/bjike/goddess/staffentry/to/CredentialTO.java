package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;

import java.util.List;

/**
 * 证书情况业务传输对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 14:31]
 * @Description: [证书情况业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CredentialTO extends BaseTO {


    /**
     * 证书名称
     */
    private String name;

    /**
     * 获得证书时间
     */
    private String obtainTime;



    /**
     * 证书名称集合
     */
    private List<String> nameses;
    /**
     * 获得证书时间集合
     */
    private List<String> obtainTimes;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(String obtainTime) {
        this.obtainTime = obtainTime;
    }


    public List<String> getNameses() {
        return nameses;
    }

    public void setNameses(List<String> nameses) {
        this.nameses = nameses;
    }

    public List<String> getObtainTimes() {
        return obtainTimes;
    }

    public void setObtainTimes(List<String> obtainTimes) {
        this.obtainTimes = obtainTimes;
    }
}
