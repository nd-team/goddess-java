package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;

import java.util.List;

/**
 * 进度表表头对应值的行标记业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadRowSignBO extends BaseBO {

    /**
     * 进度表表头信息List
     */
    private List<TableHeadValueBO> boList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TableHeadValueBO> getBoList() {
        return boList;
    }

    public void setBoList(List<TableHeadValueBO> boList) {
        this.boList = boList;
    }
}