package com.bjike.goddess.common.consumer.interceptor.idem;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by lake on 17-4-17.
 */
public class Info {
    public enum Status{
        PRE,AFTER
    }
    private Status status;

    @NotBlank
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
