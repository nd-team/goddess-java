package com.bjike.goddess.costdetail.vo;

/**
 * 明细分类表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DetailTypeVO {

    /**
     * id
     */
    private String id;
    /**
     * 父节点
     */
    private String parNode;

    /**
     * 分类名
     */
    private String typeName;

    /**
     * 描述
     */
    private String descs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParNode() {
        return parNode;
    }

    public void setParNode(String parNode) {
        this.parNode = parNode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
}