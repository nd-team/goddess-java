package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.DetailBO;
import com.bjike.goddess.rotation.bo.RotationStatisticsBO;
import com.bjike.goddess.rotation.dto.RotationStatisticsDTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RotationStatisticsTO;

import java.util.List;

/**
 * 岗位轮换统计业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RotationStatisticsAPI {

    /**
     * 保存
     *
     * @param to 岗位轮换统计传输对象
     * @return
     * @throws SerException
     */
    default RotationStatisticsBO save(RotationStatisticsTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 岗位轮换统计传输对象
     * @return
     * @throws SerException
     */
    default RotationStatisticsBO update(RotationStatisticsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 岗位轮换统计数据id
     * @return
     * @throws SerException
     */
    default RotationStatisticsBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询岗位轮换统计数据
     *
     * @param id 岗位轮换统计数据id
     * @return
     * @throws SerException
     */
    default RotationStatisticsBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位轮换统计数据传输对象
     * @return
     * @throws SerException
     */
    default List<RotationStatisticsBO> maps(RotationStatisticsDTO dto) throws SerException {
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

    default List<DetailBO> getDetail() throws SerException {
        return null;
    }
}