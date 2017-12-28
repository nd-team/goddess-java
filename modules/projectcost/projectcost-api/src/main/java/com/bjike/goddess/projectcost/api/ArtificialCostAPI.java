package com.bjike.goddess.projectcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcost.bo.ArtificialCostBO;
import com.bjike.goddess.projectcost.dto.ArtificialCostDTO;
import com.bjike.goddess.projectcost.excel.SonPermissionObject;
import com.bjike.goddess.projectcost.to.ArtificialCostTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.to.GuidePermissionTO;

import java.util.List;

/**
 * 人工费用业务接口
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:19 ]
 * @Description: [ 人工费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArtificialCostAPI {

    /**
     * 保存
     *
     * @param to 人工费用传输对象
     * @return
     * @throws SerException
     */
    default ArtificialCostBO save(ArtificialCostTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 人工费用传输对象
     * @return
     * @throws SerException
     */
    default ArtificialCostBO update(ArtificialCostTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 人工费用数据id
     * @return
     * @throws SerException
     */
    default ArtificialCostBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 人工费用数据传输对象
     * @return
     * @throws SerException
     */
    default List<ArtificialCostBO> maps(ArtificialCostDTO dto) throws SerException {
        return null;
    }

    /**
     * 修改实际人工工时
     *
     * @param to 人工费用传输对象
     * @return
     * @throws SerException
     */
    default ArtificialCostBO updateActual(ArtificialCostTO to) throws SerException {
        return null;
    }

    /**
     * 根据id查询数据
     *
     * @param id 人工费用数据id
     * @return
     * @throws SerException
     */
    default ArtificialCostBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据查询条件查询数据
     *
     * @param to 查询条件传输对象
     * @return
     * @throws SerException
     */
    default List<ArtificialCostBO> findByTO(FindTO to) throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 通过时间和项目组查找
     *
     * @param year
     * @param month
     * @param project
     * @return
     * @throws SerException
     */
    List<ArtificialCostBO> find(Integer year, Integer month, String project) throws SerException;
}