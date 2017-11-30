package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 结算进度进度汇总的数据
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 结算进度进度汇总的数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonalTasksSummBO extends BaseBO {

    /**
     * 表头数据
     */
    private List<String> headerData;

    /**
     * 数据
     */
    private List<PeoperDataBO> peoperDataBOList;

    public List<String> getHeaderData() {
        return headerData;
    }

    public void setHeaderData(List<String> headerData) {
        this.headerData = headerData;
    }

    public List<PeoperDataBO> getPeoperDataBOList() {
        return peoperDataBOList;
    }

    public void setPeoperDataBOList(List<PeoperDataBO> peoperDataBOList) {
        this.peoperDataBOList = peoperDataBOList;
    }
}