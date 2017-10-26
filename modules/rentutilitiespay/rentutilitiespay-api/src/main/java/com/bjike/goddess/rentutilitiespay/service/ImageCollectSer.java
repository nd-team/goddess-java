package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentutilitiespay.bo.OptionBO;
import com.bjike.goddess.rentutilitiespay.dto.ImageCollectDTO;
import com.bjike.goddess.rentutilitiespay.entity.ImageCollect;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-23 11:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ImageCollectSer extends Ser<ImageCollect,ImageCollectDTO> {
    /**
     * 图形化月汇总
     */
    OptionBO firgureShowMonth(Integer year, Integer month) throws SerException;

    /**
     * 图形化年汇总
     */
    OptionBO figrueShowYear(Integer year) throws SerException;

    /**
     * 图形化累计汇总
     */
    OptionBO figureShowTotal(String endDate) throws SerException;
}
