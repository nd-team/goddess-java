package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-11-06 13:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseCountMailBO extends BaseBO{
    private String depart;
    private List<NormalAttendBO> normalAttendances;
    private List<NormalAttendBO> lates;
    private List<NormalAttendBO> vacateAttendances;
    private List<NormalAttendBO> absenteeisms;
    private List<NormalAttendBO> normalRests;
    private List<NormalAttendBO> outWorks;
    private List<NormalAttendBO> festivals;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<NormalAttendBO> getNormalAttendances() {
        return normalAttendances;
    }

    public void setNormalAttendances(List<NormalAttendBO> normalAttendances) {
        this.normalAttendances = normalAttendances;
    }

    public List<NormalAttendBO> getLates() {
        return lates;
    }

    public void setLates(List<NormalAttendBO> lates) {
        this.lates = lates;
    }

    public List<NormalAttendBO> getVacateAttendances() {
        return vacateAttendances;
    }

    public void setVacateAttendances(List<NormalAttendBO> vacateAttendances) {
        this.vacateAttendances = vacateAttendances;
    }

    public List<NormalAttendBO> getAbsenteeisms() {
        return absenteeisms;
    }

    public void setAbsenteeisms(List<NormalAttendBO> absenteeisms) {
        this.absenteeisms = absenteeisms;
    }

    public List<NormalAttendBO> getNormalRests() {
        return normalRests;
    }

    public void setNormalRests(List<NormalAttendBO> normalRests) {
        this.normalRests = normalRests;
    }

    public List<NormalAttendBO> getOutWorks() {
        return outWorks;
    }

    public void setOutWorks(List<NormalAttendBO> outWorks) {
        this.outWorks = outWorks;
    }

    public List<NormalAttendBO> getFestivals() {
        return festivals;
    }

    public void setFestivals(List<NormalAttendBO> festivals) {
        this.festivals = festivals;
    }
}
