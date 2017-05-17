package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.CoverRotationBO;
import com.bjike.goddess.rotation.bo.CoverRotationOpinionBO;
import com.bjike.goddess.rotation.dto.CoverRotationDTO;
import com.bjike.goddess.rotation.to.CoverRotationOpinionTO;
import com.bjike.goddess.rotation.to.CoverRotationTO;

import java.util.List;

/**
 * 岗位轮换自荐业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CoverRotationAPI {

    /**
     * 保存
     *
     * @param to 岗位轮换自荐传输对象
     * @return
     * @throws SerException
     */
    default CoverRotationBO save(CoverRotationTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 岗位轮换自荐传输对象
     * @return
     * @throws SerException
     */
    default CoverRotationBO update(CoverRotationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 岗位轮换自荐数据id
     * @return
     * @throws SerException
     */
    default CoverRotationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询岗位轮换自荐数据
     *
     * @param id 岗位轮换自荐数据id
     * @return
     * @throws SerException
     */
    default CoverRotationBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 意见
     *
     * @param to 岗位轮换自荐意见传输对象
     * @return
     * @throws SerException
     */
    default CoverRotationOpinionBO opinion(CoverRotationOpinionTO to) throws SerException {
        return null;
    }

    /**
     * 总经办意见
     *
     * @param to 岗位轮换自荐传输对象
     * @return
     * @throws SerException
     */
    default CoverRotationBO generalOpinion(CoverRotationTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位轮换自荐数据传输对象
     * @return
     * @throws SerException
     */
    default List<CoverRotationBO> maps(CoverRotationDTO dto) throws SerException {
        return null;
    }

    /**
     * 总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

}