package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.DimissionCollectBO;
import com.bjike.goddess.dimission.bo.OptionBO;
import com.bjike.goddess.dimission.bo.SituationBO;
import com.bjike.goddess.dimission.dto.SituationDTO;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.SituationTO;

import java.util.List;

/**
 * 离职办理节点情况业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SituationAPI {

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
     * 保存
     *
     * @param to
     * @throws SerException
     */
    default SituationBO save(SituationTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to
     * @return
     * @throws SerException
     */
    default SituationBO update(SituationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws SerException
     */
    default SituationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<SituationBO> list(SituationDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default SituationBO findById(String id) throws SerException {
        return null;
    }

    /**
     * 离职自离表里的姓名
     *
     * @return
     * @throws SerException
     */
    default List<String> getName() throws SerException {
        return null;
    }

    /**
     * 判断是否是自离
     *
     * @param name
     * @return
     * @throws SerException
     */
    default Boolean isSelf(String name) throws SerException {
        return null;
    }

    /**
     * 离职管理汇总表
     *
     * @return
     * @throws SerException
     */
    default List<DimissionCollectBO> collect(String startTime, String endTime) throws SerException {
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
     * 离职管理月汇总图形化
     *
     * @param month
     * @return
     * @throws SerException
     */
    default OptionBO figureShowMonth(String month) throws SerException {
        return null;
    }

    /**
     * 离职管理日汇总柱状图
     *
     * @param day
     * @return
     * @throws SerException
     */
    default OptionBO figureShowDay(String day) throws SerException {
        return null;
    }

    /**
     * 离职管理周汇总柱状图
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    default OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 离职管理累计汇总柱状图
     *
     * @return
     * @throws SerException
     */
    default OptionBO figureShowAll() throws SerException {
        return null;
    }
}