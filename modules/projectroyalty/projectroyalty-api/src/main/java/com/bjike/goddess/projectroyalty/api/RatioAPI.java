package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.RatioBO;
import com.bjike.goddess.projectroyalty.dto.RatioDTO;
import com.bjike.goddess.projectroyalty.to.RatioTO;

import java.util.List;

/**
 * 毛利率业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:48 ]
 * @Description: [ 毛利率业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RatioAPI {

    /**
     * 保存
     *
     * @param to 毛利率传输对象
     * @return
     * @throws SerException
     */
    default RatioBO save(RatioTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 毛利率传输对象
     * @return
     * @throws SerException
     */
    default RatioBO update(RatioTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 毛利率数据id
     * @return
     * @throws SerException
     */
    default RatioBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取毛利率数据
     *
     * @param id 毛利率数据id
     * @return
     * @throws SerException
     */
    default RatioBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 毛利率数据传输对象
     * @return
     * @throws SerException
     */
    default List<RatioBO> maps(RatioDTO dto) throws SerException {
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
     * 获取毛利率选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findOpinion() throws SerException {
        return null;
    }

}