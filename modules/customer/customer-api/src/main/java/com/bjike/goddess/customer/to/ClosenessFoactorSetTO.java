package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 亲密度因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:01 ]
 * @Description: [ 亲密度因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ClosenessFoactorSetTO extends BaseTO {

    /**
     * 一般一般比
     */
    @NotNull(message = "一般一般比不能为空",groups = {ADD.class,EDIT.class})
    private Double generalGeneral;

    /**
     * 一般亲密比
     */
    @NotNull(message = "一般亲密比不能为空",groups = {ADD.class,EDIT.class})
    private Double generalClose;

    /**
     * 一般十分亲密比
     */
    @NotNull(message = "一般十分亲密比不能为空",groups = {ADD.class,EDIT.class})
    private Double generalVeryClose;

    /**
     * 亲密一般比
     */
    @NotNull(message = "亲密一般比不能为空",groups = {ADD.class,EDIT.class})
    private Double closeGeneral;

    /**
     * 亲密亲密比
     */
    @NotNull(message = "亲密亲密比不能为空",groups = {ADD.class,EDIT.class})
    private Double closeClose;

    /**
     * 亲密十分亲密比
     */
    @NotNull(message = "亲密十分亲密比不能为空",groups = {ADD.class,EDIT.class})
    private Double closeVeryClose;

    /**
     * 十分亲密一般比
     */
    @NotNull(message = "十分亲密一般比不能为空",groups = {ADD.class,EDIT.class})
    private Double veryCloseGeneral;

    /**
     * 十分亲密亲密比
     */
    @NotNull(message = "十分亲密亲密比不能为空",groups = {ADD.class,EDIT.class})
    private Double veryCloseClose;

    /**
     * 十分亲密十分亲密比
     */
    @NotNull(message = "十分亲密十分亲密比不能为空",groups = {ADD.class,EDIT.class})
    private Double veryCloseVeryClose;


    public Double getGeneralGeneral() {
        return generalGeneral;
    }

    public void setGeneralGeneral(Double generalGeneral) {
        this.generalGeneral = generalGeneral;
    }

    public Double getGeneralClose() {
        return generalClose;
    }

    public void setGeneralClose(Double generalClose) {
        this.generalClose = generalClose;
    }

    public Double getGeneralVeryClose() {
        return generalVeryClose;
    }

    public void setGeneralVeryClose(Double generalVeryClose) {
        this.generalVeryClose = generalVeryClose;
    }

    public Double getCloseGeneral() {
        return closeGeneral;
    }

    public void setCloseGeneral(Double closeGeneral) {
        this.closeGeneral = closeGeneral;
    }

    public Double getCloseClose() {
        return closeClose;
    }

    public void setCloseClose(Double closeClose) {
        this.closeClose = closeClose;
    }

    public Double getCloseVeryClose() {
        return closeVeryClose;
    }

    public void setCloseVeryClose(Double closeVeryClose) {
        this.closeVeryClose = closeVeryClose;
    }

    public Double getVeryCloseGeneral() {
        return veryCloseGeneral;
    }

    public void setVeryCloseGeneral(Double veryCloseGeneral) {
        this.veryCloseGeneral = veryCloseGeneral;
    }

    public Double getVeryCloseClose() {
        return veryCloseClose;
    }

    public void setVeryCloseClose(Double veryCloseClose) {
        this.veryCloseClose = veryCloseClose;
    }

    public Double getVeryCloseVeryClose() {
        return veryCloseVeryClose;
    }

    public void setVeryCloseVeryClose(Double veryCloseVeryClose) {
        this.veryCloseVeryClose = veryCloseVeryClose;
    }
}