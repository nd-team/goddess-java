package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.BNCollectEchartBO;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationCollectBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationCollectDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.enums.CollectTimeType;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-02 16:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface BusinessNegotiationCollectSer extends Ser<BusinessNegotiation, BusinessNegotiationCollectDTO> {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 商务洽谈汇总总条数
     */
    Long count(BusinessNegotiationCollectDTO dto) throws SerException;

    /**
     * 商务洽谈汇总列表
     * params
     */
    List<BusinessNegotiationCollectBO> listBusinessNegotiationCollect(BusinessNegotiationCollectDTO dto, CollectTimeType type, String... params) throws SerException;

    /**
     * 商务洽谈汇总图表展示
     *
     * @param
     * @return class
     * @version v1
     */
    BNCollectEchartBO listCollectEchart(CollectTimeType type, String... params) throws SerException;

    /**
     * 商务洽谈按照业务类型汇总图表展示
     *
     * @param
     * @return class
     * @version v1
     */
    BNCollectEchartBO listCollectEchartByType(String businessType, CollectTimeType type, String... params) throws SerException;

    /**
     * 获取商务类型
     *
     * @param
     * @return class
     * @version v1
     */
    Set<String> listBusinessType() throws SerException;

}
