package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;

import java.util.List;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ReportAddressInforAPI {
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
     * 根据id查询报道地址信息
     *
     * @param id 报道地址信息唯一标识
     * @return class ReportAddressInforBO
     * @throws SerException
     */
    ReportAddressInforBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 报道地址信息dto
     * @throws SerException
     */
    Long count(ReportAddressInforDTO dto) throws SerException;

    /**
     * 分页查询报道地址信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ReportAddressInforBO> list(ReportAddressInforDTO dto) throws SerException;

    /**
     * 保存报道地址信息
     *
     * @param reportAddressInforTO
     * @return
     * @throws SerException
     */
    ReportAddressInforBO save(ReportAddressInforTO reportAddressInforTO) throws SerException;

    /**
     * 根据id删除报道地址信息
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新报道地址信息
     *
     * @param reportAddressInforTO
     * @throws SerException
     */
    void update(ReportAddressInforTO reportAddressInforTO) throws SerException;
}
