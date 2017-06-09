package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.FacilityBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.FacilityDTO;
import com.bjike.goddess.projectroyalty.to.FacilityTO;

import java.util.List;

/**
 * 难易度业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:47 ]
 * @Description: [ 难易度业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FacilityAPI {

    /**
     * 保存
     *
     * @param to 难易度传输对象
     * @return
     * @throws SerException
     */
    default FacilityBO save(FacilityTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 难易度传输对象
     * @return
     * @throws SerException
     */
    default FacilityBO update(FacilityTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 难易度数据id
     * @return
     * @throws SerException
     */
    default FacilityBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取难易度数据
     *
     * @param id 难易度数据id
     * @return
     * @throws SerException
     */
    default FacilityBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 难易度数据传输对象
     * @return
     * @throws SerException
     */
    default List<FacilityBO> maps(FacilityDTO dto) throws SerException {
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
     * 获取难易度选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findOpinion() throws SerException {
        return null;
    }

}