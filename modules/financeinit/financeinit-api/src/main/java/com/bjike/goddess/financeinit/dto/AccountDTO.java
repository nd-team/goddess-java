package com.bjike.goddess.financeinit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 账户来源数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountDTO extends BaseDTO {

    public interface TESTFirst {
    }

    public interface TESTSearchSecond {
    }

    /**
     * 一级科目名
     */
    @NotBlank(groups = {AccountDTO.TESTFirst.class, AccountDTO.TESTSearchSecond.class}, message = "一级科目名不能为空")
    private String firstSubject;

    /**
     * 二级科目名
     */
    @NotBlank(groups = AccountDTO.TESTSearchSecond.class, message = "二级科目不能为空")
    private String secondSubject;

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }
}