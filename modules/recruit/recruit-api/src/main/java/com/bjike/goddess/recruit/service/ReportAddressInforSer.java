package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.entity.ReportAddressInfor;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;

import java.util.List;
import java.util.Set;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ReportAddressInforSer extends Ser<ReportAddressInfor, ReportAddressInforDTO> {
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

    /**
     * 获取所有入职报道地址
     *
     * @return
     * @throws SerException
     */
    Set<String> allAddress() throws SerException;
}
