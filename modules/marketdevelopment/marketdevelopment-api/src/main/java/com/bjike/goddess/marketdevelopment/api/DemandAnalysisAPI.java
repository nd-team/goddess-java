package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.DemandAnalysisBO;
import com.bjike.goddess.marketdevelopment.to.DemandAnalysisTO;

import java.util.List;

/**
 * 市场需求分析业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:10 ]
 * @Description: [ 市场需求分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemandAnalysisAPI {

    /**
     * 保存市场需求分析数据
     *
     * @param to 市场需求分析传输对象
     * @return
     * @throws SerException
     */
    default DemandAnalysisBO save(DemandAnalysisTO to) throws SerException {
        return null;
    }

    /**
     * 修改市场需求分析数据
     *
     * @param to 市场需求分析传输对象
     * @return
     * @throws SerException
     */
    default DemandAnalysisBO update(DemandAnalysisTO to) throws SerException {
        return null;
    }

    /**
     * 删除市场需求分析数据
     *
     * @param to 市场需求分析传输对象
     * @return
     * @throws SerException
     */
    default DemandAnalysisBO delete(DemandAnalysisTO to) throws SerException {
        return null;
    }

    /**
     * 根据业务类型查询市场需求分析数据
     *
     * @param type 业务类型
     * @return
     * @throws SerException
     */
    default List<DemandAnalysisBO> findByType(String type) throws SerException {
        return null;
    }

    /**
     * 根据业务方向科目查询市场需求分析数据
     *
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<DemandAnalysisBO> findByCourse(String course) throws SerException {
        return null;
    }

    /**
     * 根据业务类型和方向科目查询市场需求分析数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<DemandAnalysisBO> findByCourseType(String type, String course) throws SerException {
        return null;
    }

    /**
     * 根据id获取市场需求分析数据
     *
     * @param id 市场需求分析数据id
     * @return
     * @throws SerException
     */
    default DemandAnalysisBO getById(String id) throws SerException {
        return null;
    }
}