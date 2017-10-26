package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.dispatchcar.bo.OptionBO;
import com.bjike.goddess.dispatchcar.dto.DispatchcarRecordCollectDTO;
import com.bjike.goddess.dispatchcar.entity.DispatchcarRecordCollect;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-19 11:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ImageCollectSer extends Ser<DispatchcarRecordCollect, DispatchcarRecordCollectDTO> {


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
     * 图形化出车管理日汇总
     */
    OptionBO figureShowDay(String day) throws SerException;

    /**
     * 图形化出车管理周汇总
     */
    OptionBO figureShowWeek(Integer year,Integer month,Integer week) throws SerException;

    /**
     * 图形化出车管理月汇总
     */
    OptionBO figureShowMonth(Integer year,Integer month) throws SerException;

    /**
     * 图形化出车管理累计汇总
     */
    OptionBO figureShowTotal(String day) throws SerException;
}
