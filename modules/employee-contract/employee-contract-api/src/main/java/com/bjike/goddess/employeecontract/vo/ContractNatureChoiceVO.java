package com.bjike.goddess.employeecontract.vo;

/**
 * 合同性质选项表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质选项表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractNatureChoiceVO {

    /**
     * id
     */
    private String id;
    /**
     * 性质
     */
    private String nature;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

}