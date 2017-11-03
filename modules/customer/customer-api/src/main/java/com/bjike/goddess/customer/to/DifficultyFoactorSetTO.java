package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 难易度因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:18 ]
 * @Description: [ 难易度因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DifficultyFoactorSetTO extends BaseTO {

    /**
     * 困难困难比
     */
    private Double difficDiffic;

    /**
     * 困难一般比
     */
    private Double difficGeneral;

    /**
     * 困难容易比
     */
    private Double difficEasy;

    /**
     * 一般困难比
     */
    private Double generalDiffic;

    /**
     * 一般一般比
     */
    private Double generalGeneral;

    /**
     * 一般容易比
     */
    private Double generalEasy;

    /**
     * 容易困难比
     */
    private Double easyDiffic;

    /**
     * 容易一般比
     */
    private Double easyGeneral;

    /**
     * 容易容易比
     */
    private Double easyEasy;


    public Double getDifficDiffic() {
        return difficDiffic;
    }

    public void setDifficDiffic(Double difficDiffic) {
        this.difficDiffic = difficDiffic;
    }

    public Double getDifficGeneral() {
        return difficGeneral;
    }

    public void setDifficGeneral(Double difficGeneral) {
        this.difficGeneral = difficGeneral;
    }

    public Double getDifficEasy() {
        return difficEasy;
    }

    public void setDifficEasy(Double difficEasy) {
        this.difficEasy = difficEasy;
    }

    public Double getGeneralDiffic() {
        return generalDiffic;
    }

    public void setGeneralDiffic(Double generalDiffic) {
        this.generalDiffic = generalDiffic;
    }

    public Double getGeneralGeneral() {
        return generalGeneral;
    }

    public void setGeneralGeneral(Double generalGeneral) {
        this.generalGeneral = generalGeneral;
    }

    public Double getGeneralEasy() {
        return generalEasy;
    }

    public void setGeneralEasy(Double generalEasy) {
        this.generalEasy = generalEasy;
    }

    public Double getEasyDiffic() {
        return easyDiffic;
    }

    public void setEasyDiffic(Double easyDiffic) {
        this.easyDiffic = easyDiffic;
    }

    public Double getEasyGeneral() {
        return easyGeneral;
    }

    public void setEasyGeneral(Double easyGeneral) {
        this.easyGeneral = easyGeneral;
    }

    public Double getEasyEasy() {
        return easyEasy;
    }

    public void setEasyEasy(Double easyEasy) {
        this.easyEasy = easyEasy;
    }
}