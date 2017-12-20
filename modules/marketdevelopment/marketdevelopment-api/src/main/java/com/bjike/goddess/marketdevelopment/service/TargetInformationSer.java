//package com.bjike.goddess.marketdevelopment.service;
//
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.api.service.Ser;
//import com.bjike.goddess.marketdevelopment.bo.TargetInformationBO;
//import com.bjike.goddess.marketdevelopment.dto.TargetInformationDTO;
//import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
//import com.bjike.goddess.marketdevelopment.entity.TargetInformation;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.TargetInformationTO;
//
//import java.util.List;
//
///**
// * 确定目标信息业务接口
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:12 ]
// * @Description: [ 确定目标信息业务接口 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//public interface TargetInformationSer extends Ser<TargetInformation, TargetInformationDTO> {
//
//    /**
//     * 保存确定目标信息数据
//     *
//     * @param to 确定目标信息传输对象
//     * @return
//     * @throws SerException
//     */
//    default TargetInformationBO save(TargetInformationTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 修改确定目标信息数据
//     *
//     * @param to 确定目标信息传输对象
//     * @return
//     * @throws SerException
//     */
//    default TargetInformationBO update(TargetInformationTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 删除确定目标信息数据
//     *
//     * @param to 确定目标信息传输对象
//     * @return
//     * @throws SerException
//     */
//    default TargetInformationBO delete(TargetInformationTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据业务类型查询确定目标信息数据
//     *
//     * @param type 业务类型
//     * @return
//     * @throws SerException
//     */
//    default List<TargetInformationBO> findByType(String type) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据业务方向科目查询确定目标信息数据
//     *
//     * @param course 业务方向科目
//     * @return
//     * @throws SerException
//     */
//    default List<TargetInformationBO> findByCourse(String course) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据业务类型和方向科目查询确定目标信息数据
//     *
//     * @param type   业务类型
//     * @param course 业务方向科目
//     * @return
//     * @throws SerException
//     */
//    default List<TargetInformationBO> findByCourseType(String type, String course) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据地区查询确定目标数据
//     *
//     * @param area 地区
//     * @return
//     * @throws SerException
//     */
//    default List<TargetInformationBO> findByArea(String area) throws SerException {
//        return null;
//    }
//
//
//    /**
//     * 导出
//     *
//     * @param to 导出查询条件传输对象
//     * @return
//     * @throws SerException
//     */
//    default byte[] exportExcel(CollectTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 下拉导航权限
//     */
//    default Boolean sonPermission() throws SerException {
//        return null;
//    }
//
//    /**
//     * 导航权限
//     */
//    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return null;
//    }
//}