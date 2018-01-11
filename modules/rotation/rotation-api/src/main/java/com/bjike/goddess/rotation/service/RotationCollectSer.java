package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RotationCollectBO;
import com.bjike.goddess.rotation.bo.RotationCollectEchartBO;
import com.bjike.goddess.rotation.bo.RotationDetailsCollectBO;
import com.bjike.goddess.rotation.dto.RotationCollectEchartDTO;
import com.bjike.goddess.rotation.dto.RotationDetailsCollectDTO;
import com.bjike.goddess.rotation.enums.CollectTimeType;
import com.bjike.goddess.rotation.to.GuidePermissionTO;

import java.util.List;

/**
 * 岗位轮换汇总
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 15:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RotationCollectSer {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 公能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }


    /**
     * 获取轮岗详细汇总
     *
     * @param
     * @return class
     * @version v1
     */
    List<RotationDetailsCollectBO> listDetailsCollect(RotationDetailsCollectDTO dto) throws SerException;

    /**
     * 获取岗位轮换管理汇总
     *
     * @param collectTimeType 汇总类型
     * @param params 参数类表
     * @return class
     * @version v1
     */
    List<RotationCollectBO> listCollect(CollectTimeType collectTimeType, String... params) throws SerException;


    /**
     * 获取岗位轮换明细图形展示
     *
     * @param
     * @return class
     * @version v1
     */
    RotationCollectEchartBO collectDetailsEchart(RotationCollectEchartDTO dto) throws SerException;

    /**
     * 获取岗位轮换管理图形汇总
     *
     * @param
     * @return class
     * @version v1
     */
    RotationCollectEchartBO collectEchart(RotationCollectEchartDTO dto) throws SerException;


}
