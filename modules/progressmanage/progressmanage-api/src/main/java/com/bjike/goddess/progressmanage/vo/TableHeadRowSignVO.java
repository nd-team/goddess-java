package com.bjike.goddess.progressmanage.vo;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 进度表表头对应值的行标记表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadRowSignVO {

    /**
     * Id
     */
    private String id;

    /**
     * 进度表表头信息List
     */
    private List<TableHeadValueTO> toList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TableHeadValueTO> getToList() {
        return toList;
    }

    public void setToList(List<TableHeadValueTO> toList) {
        this.toList = toList;
    }

}