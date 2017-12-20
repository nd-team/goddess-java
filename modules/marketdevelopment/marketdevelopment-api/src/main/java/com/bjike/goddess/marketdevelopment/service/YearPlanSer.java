//package com.bjike.goddess.marketdevelopment.service;
//
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.api.service.Ser;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanChoiceBO;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanCollectBO;
//import com.bjike.goddess.marketdevelopment.dto.YearPlanDTO;
//import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
//import com.bjike.goddess.marketdevelopment.entity.YearPlan;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.YearPlanTO;
//
//import java.util.List;
//
///**
// * 年计划业务接口
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 05:57 ]
// * @Description: [ 年计划业务接口 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//public interface YearPlanSer extends Ser<YearPlan, YearPlanDTO> {
//
//    /**
//     * 保存年计划数据
//     *
//     * @param to 年计划传输对象
//     * @return
//     * @throws SerException
//     */
//    default YearPlanBO save(YearPlanTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 修改年计划数据
//     *
//     * @param to 年计划传输对象
//     * @return
//     * @throws SerException
//     */
//    default YearPlanBO update(YearPlanTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 删除年计划对象
//     *
//     * @param to 年计划传输对象
//     * @return
//     * @throws SerException
//     */
//    default YearPlanBO delete(YearPlanTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 查询本年年计划数据
//     *
//     * @return
//     * @throws SerException
//     */
//    default List<YearPlanBO> findThisYear() throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据年份查询年计划数据
//     *
//     * @param year 年份
//     * @return
//     * @throws SerException
//     */
//    default List<YearPlanBO> findByYear(Integer year) throws SerException {
//        return null;
//    }
//
//    /**
//     * 获取年计划选择对象
//     *
//     * @return
//     * @throws SerException
//     */
//    default List<YearPlanChoiceBO> getChoice() throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据id获取年计划
//     *
//     * @param id 年计划数据id
//     * @return
//     * @throws SerException
//     */
//    default YearPlanBO getById(String id) throws SerException {
//        return null;
//    }
//
//    /**
//     * 列表
//     *
//     * @param dto 年计划数据传输对象
//     * @return
//     * @throws SerException
//     */
//    default List<YearPlanBO> maps(YearPlanDTO dto) throws SerException {
//        return null;
//    }
//
//    /**
//     * 获取总条数
//     *
//     * @return
//     * @throws SerException
//     */
//    default Integer getTotal() throws SerException {
//        return null;
//    }
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
//     * 根据业务类型查询月计划
//     *
//     * @param type 业务类型
//     * @return
//     * @throws SerException
//     */
//    default List<YearPlanBO> findByType(String type) throws SerException {
//        return null;
//    }
//
//    /**
//     * 导航权限
//     */
//    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return null;
//    }
//
//    /**
//     * 下拉导航权限
//     */
//    default List<SonPermissionObject> sonPermission() throws SerException {
//        return null;
//    }
//
//    /**
//     * 年计划汇总
//     * @return
//     * @throws SerException
//     */
//    default List<YearPlanCollectBO> collect() throws SerException{
//        return null;
//    }
//}