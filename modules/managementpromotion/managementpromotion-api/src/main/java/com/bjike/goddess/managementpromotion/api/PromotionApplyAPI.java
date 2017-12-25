package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.PromotionApplyBO;
import com.bjike.goddess.managementpromotion.dto.PromotionApplyDTO;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.PromotionApplyTO;

import java.util.List;

/**
 * 管理等级晋升申请业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 02:04 ]
 * @Description: [ 管理等级晋升申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PromotionApplyAPI {
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
     * 添加
     *
     * @param to 管理等级晋升申请信息
     * @return class PromotionApplyBO
     * @throws SerException
     */
    default PromotionApplyBO save(PromotionApplyTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 管理等级晋升申请id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 编辑
     *
     * @param to 管理等级晋升申请信息
     * @throws SerException
     */
    default void edit(PromotionApplyTO to) throws SerException {
    }

    /**
     * 管理等级晋升申请列表
     *
     * @param dto 管理等级晋升申请dto
     * @return class PromotionApplyBO
     * @throws SerException
     */
    default List<PromotionApplyBO> find(PromotionApplyDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 管理等级晋升申请id
     * @return class PromotionApplyBO
     * @throws SerException
     */
    default PromotionApplyBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 规划填写是否符合晋升条件
     *
     * @param to 管理等级晋升申请信息
     * @throws SerException
     */
    void conform(PromotionApplyTO to) throws SerException;

    /**
     * 综合素养模块填写晋升标准达标数
     *
     * @param to 管理等级晋升申请信息
     * @throws SerException
     */
    void writePromotionCriteria(PromotionApplyTO to) throws SerException;

    /**
     * 项目经理审核
     *
     * @param to 管理等级晋升申请信息
     * @throws SerException
     */
    void writeProjectManager(PromotionApplyTO to) throws SerException;

    /**
     * 综合资源部规划模块审核
     *
     * @param to 管理等级晋升申请信息
     * @throws SerException
     */
    void writeResourceDepartment(PromotionApplyTO to) throws SerException;

    /**
     * 运营商务部审核
     *
     * @param to 管理等级晋升申请信息
     * @throws SerException
     */
    void writeCommerceDepartment(PromotionApplyTO to) throws SerException;

    /**
     * 模块负责人审核
     *
     * @param to 管理等级晋升申请信息
     * @throws SerException
     */
    void writeModuler(PromotionApplyTO to) throws SerException;

    /**
     * 总经办审核和填写本次晋升等级获得时间
     *
     * @param to
     * @throws SerException
     */
    void writeManager(PromotionApplyTO to) throws SerException;

    /**
     * 查看晋升申请排名
     *
     * @return class PromotionApplyBO
     * @throws SerException
     */
    List<PromotionApplyBO> rank() throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    Long count(PromotionApplyDTO dto) throws SerException;

    /**
     * 定时发送邮件给总经办
     *
     * @throws SerException
     */
    void send() throws SerException;
}