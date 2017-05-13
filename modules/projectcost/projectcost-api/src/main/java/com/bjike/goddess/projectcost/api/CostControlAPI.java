package com.bjike.goddess.projectcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcost.bo.CostControlBO;
import com.bjike.goddess.projectcost.bo.HistogramBO;
import com.bjike.goddess.projectcost.dto.CostControlDTO;
import com.bjike.goddess.projectcost.to.CostControlTO;
import com.bjike.goddess.projectcost.to.FindTO;

import java.util.List;

/**
 * 项目成本控制业务接口
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目成本控制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostControlAPI {

    /**
     * 保存
     *
     * @param to 项目成本控制传输对象
     * @return
     * @throws SerException
     */
    default CostControlBO save(CostControlTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 项目成本控制传输对象
     * @return
     * @throws SerException
     */
    default CostControlBO update(CostControlTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 项目成本控制数据id
     * @return
     * @throws SerException
     */
    default CostControlBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 修改实际收入
     *
     * @param to 项目成本控制传输对象
     * @return
     * @throws SerException
     */
    default CostControlBO updateActual(CostControlTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 项目成本控制数据传输对象
     * @return
     * @throws SerException
     */
    default List<CostControlBO> maps(CostControlDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id查询项目成本控制数据
     *
     * @param id 项目成本控制数据id
     * @return
     * @throws SerException
     */
    default CostControlBO getById(String id) throws SerException {
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
     * 柱状图汇总数据
     *
     * @param to
     * @return
     * @throws SerException
     */
    default List<HistogramBO> histogramCollect(FindTO to) throws SerException {
        return null;
    }

    /**
     * 根据查询条件查询成本控制数据
     *
     * @param to 查询条件传输对象
     * @return
     * @throws SerException
     */
    default List<CostControlBO> findByTo(FindTO to) throws SerException {
        return null;
    }

}