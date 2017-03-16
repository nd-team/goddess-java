package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 岗位说明书
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:38]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Entity
@Table(name = "organize_position_instruction")
public class PositionInstruction extends BaseEntity {

    /**
     * 岗位
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", columnDefinition = "VARCHAR(36) COMMENT '岗位'")
    private PositionDetail position;

    /**
     * 编号
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '编号'")
    private String serialNumber;

    /**
     * 角度
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "angle_id", columnDefinition = "VARCHAR(36) COMMENT '角度'")
    private Angle angle;

    /**
     * 维度
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "dimension_id", columnDefinition = "VARCHAR(36) COMMENT '维度'")
    private Dimension dimension;

    /**
     * 分类
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "classify_id", columnDefinition = "VARCHAR(36) COMMENT '分类'")
    private InstructionClassify classify;

    /**
     * 操作类型
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "organize_position_instruction_operate",
            joinColumns = {@JoinColumn(name = "instruction_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '岗位说明书'")},
            inverseJoinColumns = {@JoinColumn(nullable = false, name = "operate_id", columnDefinition = "VARCHAR(36) COMMENT '操作类型'")})
    private Set<Operate> operates = new HashSet<>(0);

    /**
     * 体现类别
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "organize_position_instruction_reflect",
            joinColumns = {@JoinColumn(name = "instruction_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '岗位说明书'")},
            inverseJoinColumns = {@JoinColumn(nullable = false, name = "reflect_id", columnDefinition = "VARCHAR(36) COMMENT '体现类别'")})
    private Set<Reflect> reflects = new HashSet<>(0);

    /**
     * 工作描述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '工作描述'")
    private String description;

    /**
     * 对应功能
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '对应功能'")
    private String function;

    /**
     * 工作时间/频率
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作时间/频率'")
    private String frequency;

    /**
     * 工作时间节点
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作时间节点'")
    private String timeNode;

    /**
     * 输出结果
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '输出结构'")
    private String outcome;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "datetime", nullable = false)
    private LocalDateTime createTime;

    public PositionDetail getPosition() {
        return position;
    }

    public void setPosition(PositionDetail position) {
        this.position = position;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Set<Operate> getOperates() {
        return operates;
    }

    public void setOperates(Set<Operate> operates) {
        this.operates = operates;
    }

    public Set<Reflect> getReflects() {
        return reflects;
    }

    public void setReflects(Set<Reflect> reflects) {
        this.reflects = reflects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public InstructionClassify getClassify() {
        return classify;
    }

    public void setClassify(InstructionClassify classify) {
        this.classify = classify;
    }
}
