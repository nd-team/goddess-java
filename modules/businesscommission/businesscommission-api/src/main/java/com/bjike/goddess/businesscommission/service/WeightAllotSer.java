//package com.bjike.goddess.businesscommission.service;
//
//import com.bjike.goddess.businesscommission.bo.WeightAllotBO;
//import com.bjike.goddess.businesscommission.dto.WeightAllotDTO;
//import com.bjike.goddess.businesscommission.entity.WeightAllot;
//import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
//import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
//import com.bjike.goddess.businesscommission.to.WeightAllotTO;
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.api.service.Ser;
//
//import java.util.List;
//
///**
// * 业务提成分配比例表业务接口
// *
// * @Author: [ zhuangkaiqin ]
// * @Date: [ 2017-06-29 04:34 ]
// * @Description: [ 业务提成分配比例表业务接口 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//public interface WeightAllotSer extends Ser<WeightAllot, WeightAllotDTO> {
//
//    /**
//     * 务提成权重分配列表总条数
//     *
//     */
//    default Long countWeightAllot(WeightAllotDTO weightAllotDTO) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据id获取务提成权重分配列表
//     * @return class WeightAllotBO
//     */
//    default WeightAllotBO getOneById(String id) throws SerException {return null;}
//
//
//    /**
//     * 务提成权重分配信息列表
//     *
//     * @return class WeightAllotBO
//     */
//    default List<WeightAllotBO> listWeightAllot(WeightAllotDTO weightAllotDTO) throws SerException {
//        return null;
//    }
//
//    /**
//     * 添加
//     *
//     * @param weightAllotTO 务提成权重分配信息
//     * @return class WeightAllotBO
//     */
//    default WeightAllotBO addWeightAllot(WeightAllotTO weightAllotTO) throws SerException {
//        return null;
//    }
//
//    /**
//     * 编辑
//     *
//     * @param weightAllotTO 务提成权重分配信息
//     * @return class WeightAllotBO
//     */
//    default WeightAllotBO editWeightAllot(WeightAllotTO weightAllotTO) throws SerException {
//        return null;
//    }
//
//    /**
//     * 删除
//     *
//     * @param id id
//     */
//    default void deleteWeightAllot(String id) throws SerException {
//        return;
//    }
//
//    /**
//     *  导入
//     * @param weightAllotTO 务提成权重分配信息
//     * @return class WeightAllotBO
//     */
//    default WeightAllotBO importExcel(List<WeightAllotTO> weightAllotTO) throws SerException { return null;}
//
//    /**
//     * 导出Excel
//     * @param dto
//     * @throws SerException
//     */
//    byte[] exportExcel(WeightAllotDTO dto ) throws SerException;
//
//    /**
//     * 导出Excel
//     * @throws SerException
//     */
//    byte[] templateExport(  ) throws SerException;
//
//    /**
//     * 下拉导航权限
//     */
//    default List<SonPermissionObject> sonPermission() throws SerException {
//        return null;
//    }
//
//    /**
//     * 工能导航权限
//     */
//    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return null;
//    }
//}