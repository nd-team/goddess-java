package com.bjike.goddess.royalty.vo;

/**
 * 体系间对赌表D表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:56 ]
 * @Description: [ 体系间对赌表D表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetDVO {

    /**
     * id
     */
    private String id;
    /**
     * 未达标分配体系
     */
    private String unmetAllocationSystem;

    /**
     * 未达标分配
     */
    private Double unmetAllocation;
    /**
     * 体系间对赌表B
     */
    private SystemBetCVO systemBetCVO;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnmetAllocationSystem() {
        return unmetAllocationSystem;
    }

    public void setUnmetAllocationSystem(String unmetAllocationSystem) {
        this.unmetAllocationSystem = unmetAllocationSystem;
    }

    public Double getUnmetAllocation() {
        return unmetAllocation;
    }

    public void setUnmetAllocation(Double unmetAllocation) {
        this.unmetAllocation = unmetAllocation;
    }

    public SystemBetCVO getSystemBetCVO() {
        return systemBetCVO;
    }

    public void setSystemBetCVO(SystemBetCVO systemBetCVO) {
        this.systemBetCVO = systemBetCVO;
    }
}