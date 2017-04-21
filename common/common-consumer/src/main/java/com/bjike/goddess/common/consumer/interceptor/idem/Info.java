package com.bjike.goddess.common.consumer.interceptor.idem;

/**
 * Created by lake on 17-4-17.
 */
public class Info {
    public enum Status{
        PRE,AFTER
    }
    private Status status;

    private String url;

    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
