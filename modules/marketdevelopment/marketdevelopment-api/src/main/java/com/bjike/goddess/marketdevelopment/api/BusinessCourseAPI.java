package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.BusinessCourseBO;
import com.bjike.goddess.marketdevelopment.bo.MarketMeasureCollectBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessCourseDTO;
import com.bjike.goddess.marketdevelopment.to.BusinessCourseTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;

import java.util.List;

/**
 * 业务方向科目业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessCourseAPI {

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 保存业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return
     * @throws SerException
     */
    default BusinessCourseBO save(BusinessCourseTO to) throws SerException {
        return null;
    }

    /**
     * 修改业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return
     * @throws SerException
     */
    default BusinessCourseBO update(BusinessCourseTO to) throws SerException {
        return null;
    }

    /**
     * 冻结业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return
     * @throws SerException
     */
    default BusinessCourseBO congeal(BusinessCourseTO to) throws SerException {
        return null;
    }

    /**
     * 解冻业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return
     * @throws SerException
     */
    default BusinessCourseBO thaw(BusinessCourseTO to) throws SerException {
        return null;
    }

    /**
     * 删除业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return
     * @throws SerException
     */
    default BusinessCourseBO delete(BusinessCourseTO to) throws SerException {
        return null;
    }

    /**
     * 查询对应业务类型的业务方向科目数据
     *
     * @param type_id 业务类型ID
     * @return
     * @throws SerException
     */
    default List<BusinessCourseBO> findByType(String type_id) throws SerException {
        return null;
    }

    /**
     * 查询正常数据的业务方向科目数据
     *
     * @return
     * @throws SerException
     */
    default List<BusinessCourseBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 根据id获取业务方向科目
     *
     * @param id 业务方向科目数据id
     * @return
     * @throws SerException
     */
    default BusinessCourseBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 业务方向科目数据传输对象
     * @return
     * @throws SerException
     */
    default List<BusinessCourseBO> maps(BusinessCourseDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Integer getTotal() throws SerException {
        return null;
    }
}