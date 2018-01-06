package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.BNCollectEchartBO;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationBO;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationCollectBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationCollectDTO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.enums.CollectTimeType;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * 商务洽谈汇总业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessNegotiationCollectAPI {
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